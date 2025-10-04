package com.example.fsanpra.detectar_beacon_2;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.MediaType;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BluetoothDataSender {
    private String nombre;
    private String direccion;
    private int rssi;
    private String bytesHex;
    private String prefijo;
    private String uuidHex;
    private String uuidString;
    private int major;
    private int minor;
    private int txPower;
    private String advFlags;
    private String advHeader;
    private String companyID;
    private int iBeaconType;
    private int iBeaconLength;

    // URL del servidor en Plesk
    private static final String URL_GUARDAR_MEDICION = "http://localhost/Sprint_0_Ferran_Sansaloni_Prats/src/servidor/api/api.php";

    // Constructor
    public BluetoothDataSender(String nombre, String direccion, int rssi, String bytesHex, String prefijo, String uuidHex, String uuidString,
                               int major, int minor, int txPower, String advFlags, String advHeader, String companyID, int iBeaconType, int iBeaconLength) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.rssi = rssi;
        this.bytesHex = bytesHex;
        this.prefijo = prefijo;
        this.uuidHex = uuidHex;
        this.uuidString = uuidString;
        this.major = major;
        this.minor = minor;
        this.txPower = txPower;
        this.advFlags = advFlags;
        this.advHeader = advHeader;
        this.companyID = companyID;
        this.iBeaconType = iBeaconType;
        this.iBeaconLength = iBeaconLength;
    }

    // Metodo toString()


    @Override
    public String toString() {
        return "BluetoothDataSender{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", rssi=" + rssi +
                ", bytesHex='" + bytesHex + '\'' +
                ", prefijo='" + prefijo + '\'' +
                ", uuidHex='" + uuidHex + '\'' +
                ", uuidString='" + uuidString + '\'' +
                ", major=" + major +
                ", minor=" + minor +
                ", txPower=" + txPower +
                ", advFlags='" + advFlags + '\'' +
                ", advHeader='" + advHeader + '\'' +
                ", companyID='" + companyID + '\'' +
                ", iBeaconType=" + iBeaconType +
                ", iBeaconLength=" + iBeaconLength +
                '}';
    }

    // Metodo para enviar la medida tomada a Plesk
    public void guardarMedida() {

        OkHttpClient client = new OkHttpClient();
        String sensor = "CO2";

        Log.d(">>>>>>", "Enviando medida con minor: " + this.minor);

        try {
            // Construir JSON para enviar
            JSONObject json = new JSONObject();
            json.put("valor", this.minor);
            json.put("sensor", sensor);

            // Cuerpo de la petición
            RequestBody body = RequestBody.create(
                    json.toString(),
                    MediaType.parse("application/json; charset=utf-8")
            );

            // Petición HTTP POST
            Request request = new Request.Builder()
                    .url(URL_GUARDAR_MEDICION)
                    .post(body)
                    .build();

            // Ejecutar petición de forma asíncrona
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(">>>>>>", "Error al enviar medida: " + e.getMessage(), e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        Log.e(">>>>>>", "Respuesta error: " + response.body().string());
                    } else {
                        Log.d(">>>>>>", "Medida enviada correctamente: " + response.body().string());
                    }
                }
            });

        } catch (Exception e) {
            Log.e(">>>>>>", "Excepción al construir/enviar medida: " + e.getMessage(), e);
        }
    }
}

