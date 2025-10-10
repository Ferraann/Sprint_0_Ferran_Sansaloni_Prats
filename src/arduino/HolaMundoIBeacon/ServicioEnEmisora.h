// -- mode: c++ --
//
// Jordi Bataller i Mascarell
// 2019-07-17
//
#ifndef SERVICIO_EMISORA_H_INCLUIDO
#define SERVICIO_EMISORA_H_INCLUIDO

#include <vector>

// ----------------------------------------------------
// alReves() utilidad
// pone al revés el contenido de una array en el mismo array
// ----------------------------------------------------
template< typename T >
T *  alReves( T * p, int n ) {
  T aux;

  for( int i=0; i < n/2; i++ ) {
    aux = p[i];
    p[i] = p[n-i-1];
    p[n-i-1] = aux;
  }
  return p;
} // ()

// ------------------------------------------------------------------
// Función stringAUint8AlReves()
// ------------------------------------------------------------------
// Convierte un string a uint8_t y lo coloca al revés en el array
// ------------------------------------------------------------------
uint8_t * stringAUint8AlReves( const char * pString, uint8_t * pUint, int tamMax ) {

  int longitudString =  strlen( pString );
  int longitudCopiar = ( longitudString > tamMax ? tamMax : longitudString );
  // copio nombreServicio -> uuidServicio pero al revés
  for( int i=0; i<=longitudCopiar-1; i++ ) {
    pUint[ tamMax-i-1 ] = pString[ i ];
  } // for

  return pUint;
} // ()

// ----------------------------------------------------------
// Clase ServicioEnEmisora
// ----------------------------------------------------------
class ServicioEnEmisora {

public:

  // Callback para escritura de característica
  using CallbackCaracteristicaEscrita = void ( uint16_t conn_handle,
                                               BLECharacteristic * chr,
                                               uint8_t * data, uint16_t len); 

  // Clase interna Caracteristica
  class Caracteristica {
  private:
    uint8_t uuidCaracteristica[16] = { // el uuid se copia aquí (al revés) a partir de un string-c
      '0','1','2','3',
      '4','5','6','7',
      '8','9','A','B',
      'C','D','E','F'
    };

    BLECharacteristic laCaracteristica;

  public:

    // Constructor básico
    Caracteristica( const char * nombreCaracteristica_ )
      :
      laCaracteristica( stringAUint8AlReves( nombreCaracteristica_, &uuidCaracteristica[0], 16 ) )
    {
    } // ()

    // Constructor con propiedades y permisos
    Caracteristica( const char * nombreCaracteristica_ ,
                    uint8_t props,
                    SecureMode_t permisoRead,
                    SecureMode_t permisoWrite, 
                    uint8_t tam ) 
      :
      Caracteristica( nombreCaracteristica_ ) // llamada al otro constructor
    {
      (*this).asignarPropiedadesPermisosYTamanyoDatos( props, permisoRead, permisoWrite, tam );
    } // ()

  private:
    // Asignar propiedades
    void asignarPropiedades ( uint8_t props ) {
      (*this).laCaracteristica.setProperties( props );
    } // ()

    // Asignar permisos
    void asignarPermisos( SecureMode_t permisoRead, SecureMode_t permisoWrite ) {
      (*this).laCaracteristica.setPermission( permisoRead, permisoWrite );
    } // ()

    // Asignar el tamaño de los datos
    void asignarTamanyoDatos( uint8_t tam ) {
      (*this).laCaracteristica.setMaxLen( tam );
    } // ()

  public:
    // Asignacion de propiedades, permisos y tamaño de datos
    void asignarPropiedadesPermisosYTamanyoDatos( uint8_t props,
                                                 SecureMode_t permisoRead,
                                                 SecureMode_t permisoWrite, 
                                                 uint8_t tam ) {
      asignarPropiedades( props );
      asignarPermisos( permisoRead, permisoWrite );
      asignarTamanyoDatos( tam );
    } // ()
              
    // Escritura y notificacion de datos
    uint16_t escribirDatos( const char * str ) {
      uint16_t r = (*this).laCaracteristica.write( str );
      return r;
    } // ()

    // Notificacion de datos
    uint16_t notificarDatos( const char * str ) {
      uint16_t r = laCaracteristica.notify( &str[0] );
      return r;
    } //  ()

    // Instalacion de callback para escritura
    void instalarCallbackCaracteristicaEscrita( CallbackCaracteristicaEscrita cb ) {
      (*this).laCaracteristica.setWriteCallback( cb );
    } // ()

    void activar() {
      err_t error = (*this).laCaracteristica.begin();
      Globales::elPuerto.escribir(  " (*this).laCaracteristica.begin(); error = " );
      Globales::elPuerto.escribir(  error );
    } // ()

  }; // class Caracteristica
  
private:
  
  uint8_t uuidServicio[16] = {
    '0','1','2','3',
    '4','5','6','7',
    '8','9','A','B',
    'C','D','E','F'
  };

  BLEService elServicio;
  std::vector< Caracteristica * > lasCaracteristicas;

public:
  // Constructor
  ServicioEnEmisora( const char * nombreServicio_ )
    :
    elServicio( stringAUint8AlReves( nombreServicio_, &uuidServicio[0], 16 ) )
  {
  } // ()
  
  // Escribir UUID en Serial
  void escribeUUID() {
    Serial.println ( "" );
    for (int i=0; i<= 15; i++) {
      Serial.print( (char) uuidServicio[i] );
    }
    Serial.println ( "\n**" );
  } // ()

  // Añadir característica al servicio
  void anyadirCaracteristica( Caracteristica & car ) {
    (*this).lasCaracteristicas.push_back( & car );
  } // ()

  // Activar el servicio y sus características
  void activarServicio( ) {
    err_t error = (*this).elServicio.begin();
    Serial.print( " (*this).elServicio.begin(); error = " );
    Serial.println( error );

    for( auto pCar : (*this).lasCaracteristicas ) {
      (*pCar).activar();
    } // for
  } // ()

  operator BLEService&() {
    return elServicio;
  } // ()
	
}; // class

#endif