package com.example.fsanpra.detectar_beacon_2;
// ------------------------------------------------------------------
// ------------------------------------------------------------------

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// ------------------------------------------------------------------
// ------------------------------------------------------------------

public class MainActivity extends AppCompatActivity {

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private static final String ETIQUETA_LOG = ">>>>";

    private static final int CODIGO_PETICION_PERMISOS = 11223344;

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private BluetoothLeScanner elEscanner;

    private ScanCallback callbackDelEscaneo = null;

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private void buscarTodosLosDispositivosBTLE() {
        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): empieza ");

        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): instalamos scan callback ");

        this.callbackDelEscaneo = new ScanCallback() {
            // Se ejecuta cuando el escáner detecta un único dispositivo.
            // Muestra un mensaje en el log y envia el resultado del escaneo al metodo mostrarInformacionDispositivosBTLE
            @Override
            public void onScanResult( int callbackType, ScanResult resultado ) {
                super.onScanResult(callbackType, resultado);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onScanResult() ");

                mostrarInformacionDispositivoBTLE( resultado );
            }

            // Se deberia usar para cuando recibe una lista de resultados en lote. Pero por ahora solo escribe en el log, no procesa la lista results
            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onBatchScanResults() ");

            }

            // Se ejecuta si falla el escaneo. Muestra un mensaje en el log diciendo que ha fallado el escaneo.
            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): onScanFailed() ");

            }
        };

        // Mensaje que avisa que se va a iniciar el escaneo de todos los dispositivos
        Log.d(ETIQUETA_LOG, " buscarTodosLosDispositivosBTL(): empezamos a escanear ");

        // Si tiene los permisos ...
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
                == PackageManager.PERMISSION_GRANTED) {
            // ... inicio el escaneo, ...
            this.elEscanner.startScan(this.callbackDelEscaneo);
        } else {
            // ... , si no, envío un mensaje de error
            Log.e(ETIQUETA_LOG, "No hay permiso para hacer startScan()");
        }


    } // ()

    // --------------------------------------------------------------
    // Metodo que monta toda la información que se recibe y la muestra en el log.
    // --------------------------------------------------------------
    private void mostrarInformacionDispositivoBTLE( ScanResult resultado ) {

        BluetoothDevice bluetoothDevice = resultado.getDevice();
        byte[] bytes = resultado.getScanRecord().getBytes();
        int rssi = resultado.getRssi();

        Log.d(ETIQUETA_LOG, " ****************************************************");
        Log.d(ETIQUETA_LOG, " ****** DISPOSITIVO DETECTADO BTLE ****************** ");
        Log.d(ETIQUETA_LOG, " ****************************************************");

        // Declaramos el nombre del dispositivo y su dirección
        String nombre = "Desconocido";
        String direccion = bluetoothDevice.getAddress();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT)
                == PackageManager.PERMISSION_GRANTED) {
            // Cuando compruebe que tiene los permisos, cambiará el nombre de "Desconocido" por su nombre real.
            nombre = bluetoothDevice.getName();
            Log.d(ETIQUETA_LOG, "nombre = " + nombre);
        } else {
            Log.d(ETIQUETA_LOG, "Permiso BLUETOOTH_CONNECT no concedido");
        }

        Log.d(ETIQUETA_LOG, " toString = " + bluetoothDevice.toString());

        /*
        ParcelUuid[] puuids = bluetoothDevice.getUuids();
        if ( puuids.length >= 1 ) {
            //Log.d(ETIQUETA_LOG, " uuid = " + puuids[0].getUuid());
           // Log.d(ETIQUETA_LOG, " uuid = " + puuids[0].toString());
        }*/

        Log.d(ETIQUETA_LOG, " dirección = " + bluetoothDevice.getAddress());
        Log.d(ETIQUETA_LOG, " rssi = " + rssi );

        Log.d(ETIQUETA_LOG, " bytes = " + new String(bytes));
        Log.d(ETIQUETA_LOG, " bytes (" + bytes.length + ") = " + Utilidades.bytesToHexString(bytes));


        // Creamos la trama de bytes
        TramaIBeacon tib = new TramaIBeacon(bytes);

        // Variables con los datos de la trama
        String prefijo = Utilidades.bytesToHexString(tib.getPrefijo());
        String advFlags = Utilidades.bytesToHexString(tib.getAdvFlags());
        String advHeader = Utilidades.bytesToHexString(tib.getAdvHeader());
        String companyID = Utilidades.bytesToHexString(tib.getCompanyID());
        String iBeaconType = Integer.toHexString(tib.getiBeaconType());
        int iBeaconLength = tib.getiBeaconLength();
        String iBeaconLengthHex = Integer.toHexString(iBeaconLength);
        String uuidHex = Utilidades.bytesToHexString(tib.getUUID());
        String uuidString = Utilidades.bytesToString(tib.getUUID());
        String majorHex = Utilidades.bytesToHexString(tib.getMajor());
        int majorInt = Utilidades.bytesToInt(tib.getMajor());
        String minorHex = Utilidades.bytesToHexString(tib.getMinor());
        int minorInt = Utilidades.bytesToInt(tib.getMinor());
        String txPowerHex = Integer.toHexString(tib.getTxPower());
        int txPower = tib.getTxPower();
        String bytesHex = Utilidades.bytesToHexString(bytes);

        // Enviados los datos de la trama por el logcat
        Log.d(ETIQUETA_LOG, " ----------------------------------------------------");
        Log.d(ETIQUETA_LOG, " prefijo  = " + prefijo);
        Log.d(ETIQUETA_LOG, "          advFlags = " + advFlags);
        Log.d(ETIQUETA_LOG, "          advHeader = " + advHeader);
        Log.d(ETIQUETA_LOG, "          companyID = " + companyID);
        Log.d(ETIQUETA_LOG, "          iBeacon type = " + iBeaconType);
        Log.d(ETIQUETA_LOG, "          iBeacon length 0x = " + iBeaconLengthHex + " ( "
                + iBeaconLength + " ) ");
        Log.d(ETIQUETA_LOG, " uuid  = " + uuidHex);
        Log.d(ETIQUETA_LOG, " uuid  = " + uuidString);
        Log.d(ETIQUETA_LOG, " major  = " + majorHex + "( "
                + majorInt + " ) ");
        Log.d(ETIQUETA_LOG, " minor  = " + minorHex + "( "
                + minorInt + " ) ");
        Log.d(ETIQUETA_LOG, " txPower  = " + txPowerHex + " ( " + txPower + " )");
        Log.d(ETIQUETA_LOG, " ****************************************************");


        // Guardar datos en bbdd
        Log.d(">>>>>>", "Dispositivo detectado: " + nombre + " - RSSI: " + rssi);

        // Crear objeto con los datos que vamos a guardar
        BluetoothDataSender dataSender = new BluetoothDataSender(
                uuidString,
                rssi,
                majorInt,
                minorInt
        );

        dataSender.guardarMedida();

    } // ()

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private void buscarEsteDispositivoBTLE(final String dispositivoBuscado ) {
        Log.d(ETIQUETA_LOG, " buscarEsteDispositivoBTLE(): empieza ");

        Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): instalamos scan callback ");


        // super.onScanResult(ScanSettings.SCAN_MODE_LOW_LATENCY, result); para ahorro de energía

        this.callbackDelEscaneo = new ScanCallback() {

            // Cuando detecta un dispositivo le pasa el resultado del escaneo al metodo que muestra la información
            @Override
            public void onScanResult( int callbackType, ScanResult resultado ) {
                super.onScanResult(callbackType, resultado);
                Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onScanResult() ");

                mostrarInformacionDispositivoBTLE( resultado );
            }

            // Metodo para cuando lleguen varios dispositivos a la vez. No hace nada de momento.
            @Override
            public void onBatchScanResults(List<ScanResult> results) {
                super.onBatchScanResults(results);
                Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onBatchScanResults() ");

            }

            // Si falla el escaneo muestra por el log que ha habido un error en el ecaneo.
            @Override
            public void onScanFailed(int errorCode) {
                super.onScanFailed(errorCode);
                Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): onScanFailed() ");

            }
        };

        ScanFilter sf = new ScanFilter.Builder().setDeviceName( dispositivoBuscado ).build();

        Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): empezamos a escanear buscando: " + dispositivoBuscado );
        //Log.d(ETIQUETA_LOG, "  buscarEsteDispositivoBTLE(): empezamos a escanear buscando: " + dispositivoBuscado
        //      + " -> " + Utilidades.stringToUUID( dispositivoBuscado ) );

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
                == PackageManager.PERMISSION_GRANTED) {

            List<ScanFilter> filtros = new ArrayList<>();
            filtros.add(new ScanFilter.Builder().setDeviceName(dispositivoBuscado).build());

            ScanSettings settings = new ScanSettings.Builder()
                    .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY) // escaneo rápido
                    .build();

            this.elEscanner.startScan(filtros, settings, this.callbackDelEscaneo);

        } else {
            Log.e(ETIQUETA_LOG, "No hay permiso para hacer startScan()");
        }

    } // ()

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private void detenerBusquedaDispositivosBTLE() {

        // Si no recibe calback del escaneo no hace nada.
        if ( this.callbackDelEscaneo == null ) {
            return;
        }

        // Compruebo que tenga los permisos necesarios para que funcione el bluetooth
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_SCAN)
                == PackageManager.PERMISSION_GRANTED) {
            // Si tengo los permisos llamo a "stopScan()"
            this.elEscanner.stopScan(this.callbackDelEscaneo);
        } else {
            // Si no tengo los permisos mando un error
            Log.e(ETIQUETA_LOG, "No hay permiso para hacer startScan()");
        }

        this.callbackDelEscaneo = null;

    } // ()

    // --------------------------------------------------------------
    // v: View -->
    //              botonBuscarDispositivosBTLEPulsado()
    //                  <--
    //
    // Avisa que le hemos dado al botón y llama a buscar todos los buscar todos los dispositivos btle.
    // --------------------------------------------------------------
    public void botonBuscarDispositivosBTLEPulsado( View v ) {
        Log.d(ETIQUETA_LOG, " boton buscar dispositivos BTLE Pulsado" );
        this.buscarTodosLosDispositivosBTLE();
    } // ()

    // --------------------------------------------------------------
    // v: View -->
    //              botonBuscarNuestroDispositivoBTLEPulsado()
    //                  <--
    //
    // Avisa que le hemos dado al botón y llama a buscar este dispositivo y le pasa el nombre que queremos buscar.
    // --------------------------------------------------------------
    public void botonBuscarNuestroDispositivoBTLEPulsado( View v ) {
        Log.d(ETIQUETA_LOG, " boton nuestro dispositivo BTLE Pulsado" );
        //this.buscarEsteDispositivoBTLE( Utilidades.stringToUUID( "EPSG-GTI-PROY-3A" ) );

        //this.buscarEsteDispositivoBTLE( "EPSG-GTI-PROY-3A" );
        this.buscarEsteDispositivoBTLE( "Ferran" );

    } // ()

    // --------------------------------------------------------------
    // v: View -->
    //              botonDetenerBusquedaDispositivosBTLEPulsado()
    //                  <--
    //
    // Avisa que le hemos dado al botón y llama a detener la busqueda de dispositivos btle.
    // --------------------------------------------------------------
    public void botonDetenerBusquedaDispositivosBTLEPulsado( View v ) {
        Log.d(ETIQUETA_LOG, " boton detener busqueda dispositivos BTLE Pulsado" );
        this.detenerBusquedaDispositivosBTLE();
    } // ()

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    private void inicializarBlueTooth() {
        // Crea un adaptador bluetooth
        BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();

        // Si el adaptador es es nulo manda un error.
        if (bta == null) {
            Log.e(ETIQUETA_LOG, "Este dispositivo no soporta Bluetooth");
            return;
        }

        // Pedir permisos según la versión
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // API 31+
            String[] permisos = {
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };

            boolean todosConcedidos = true;
            for (String p : permisos) {
                if (ContextCompat.checkSelfPermission(this, p) != PackageManager.PERMISSION_GRANTED) {
                    todosConcedidos = false;
                    break;
                }
            }

            if (!todosConcedidos) {
                ActivityCompat.requestPermissions(this, permisos, CODIGO_PETICION_PERMISOS);
            } else {
                if (!bta.isEnabled()) {
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, 1);
                }
            }
        } else {
            // Android 11 o menor
            if (!bta.isEnabled()) {
                bta.enable();
            }

            // Pedir ubicación si falta
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        CODIGO_PETICION_PERMISOS
                );
            }
        }

        // Inicializar escáner BTLE
        this.elEscanner = bta.getBluetoothLeScanner();
        if (this.elEscanner == null) {
            Log.e(ETIQUETA_LOG, "No se ha podido obtener el escáner BTLE");
        }
    }

    // --------------------------------------------------------------
    // --------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Avisamos que se va a iniciar el onCreate y llamamos a iniciar blutooth y despues avisamos que ha terminado de ejecutarse el onCreate
        Log.d(ETIQUETA_LOG, " onCreate(): empieza ");

        inicializarBlueTooth();

        Log.d(ETIQUETA_LOG, " onCreate(): termina ");

    } // onCreate()

    // --------------------------------------------------------------
    // Metodo para comprobar si se han dado los permisos.
    // --------------------------------------------------------------
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CODIGO_PETICION_PERMISOS) {
            boolean todosConcedidos = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    todosConcedidos = false;
                    break;
                }
            }

            if (todosConcedidos) {
                Log.d(ETIQUETA_LOG, "Permisos concedidos");
                inicializarBlueTooth(); // ahora que hay permisos, inicializamos
            } else {
                Log.d(ETIQUETA_LOG, "Permisos NO concedidos");
            }
        }
    }



} // class
// --------------------------------------------------------------
// --------------------------------------------------------------
// --------------------------------------------------------------
// --------------------------------------------------------------