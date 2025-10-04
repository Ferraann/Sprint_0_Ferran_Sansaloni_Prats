// -*- javascript -*-

// --------------------------------------------------------------------------------
// guardarMedicion
// --------------------------------------------------------------------------------

// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------


// -----------------------------------------------------------------------------
// datos:{id_sensor:Int, latitud:Float, longitud:Float, medicionCo2:Int, temperatura:Float, humedad:Float}
//      -->
//          guardarMedicion()
// -----------------------------------------------------------------------------
module.exports = async function guardarMedicion(datos) {

    const textoSQL = `
        INSERT INTO mediciones (id_sensor, latitud, longitud, medicionCo2, temperatura, humedad)
        VALUES ( $id_sensor, $latitud, $longitud, $medicionCo2, $temperatura, $humedad )
    `;

    const valoresParaSQL = {
        $id_sensor: datos.id_sensor,
        $latitud: datos.latitud,
        $longitud: datos.longitud,
        $medicionCo2: datos.medicionCo2,
        $temperatura: datos.temperatura,
        $humedad: datos.humedad
    };

    return new Promise((resolver, rechazar) => {
        guardarMedicion.conexion.run(textoSQL, valoresParaSQL, function(err) {
            (err ? rechazar(err) : resolver({ id_medicion: this.lastID }));
        });
    });

} // ()

// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
