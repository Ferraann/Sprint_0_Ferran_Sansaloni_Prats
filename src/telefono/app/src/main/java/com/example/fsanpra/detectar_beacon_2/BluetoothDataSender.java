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
    private String uuidString;
    private int rssi;
    private int major;
    private int minor;

    // URL del servidor en Plesk
    private static final String URL_GUARDAR_MEDICION = "http://192.168.1.48/Sprint_0_Ferran_Sansaloni_Prats/src/servidor/api/api.php";

    // Constructor
    public BluetoothDataSender(String uuidString, int rssi, int major, int minor) {
        this.uuidString = uuidString;
        this.rssi = rssi;
        this.major = major;
        this.minor = minor;
    }

    // toString
    @Override
    public String toString() {
        return "BluetoothDataSender{" +
                "uuidString='" + uuidString + '\'' +
                ", rssi=" + rssi +
                ", major=" + major +
                ", minor=" + minor +
                '}';
    }

    // Metodo para enviar la medida tomada a Plesk
    public void guardarMedida() {

        // Creamos el cliente OkHttp
        OkHttpClient client = new OkHttpClient();

        try {
            // Construir JSON para enviar
            JSONObject json = new JSONObject();
            json.put("id_sensor", 1);
            json.put("uuid", uuidString);
            json.put("rssi", rssi);
            json.put("major", major);
            json.put("minor", minor);
            json.put("latitud", 40.123456);
            json.put("longitud", -3.123456);
            json.put("medicionCo2", 1234);

            RequestBody body = RequestBody.create(
                    json.toString(),
                    MediaType.parse("application/json; charset=utf-8")
            );

            Request request = new Request.Builder()
                    .url(URL_GUARDAR_MEDICION)
                    .post(body)
                    .build();

            // Mensajes:
            client.newCall(request).enqueue(new Callback() {
                // Se ejecuta si va mal
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e(">>>>>>", "Error al enviar medida: " + e.getMessage(), e);
                }

                // Se ejecuta si va bien
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
            // Error
            Log.e(">>>>>>", "Excepción al construir/enviar medida: " + e.getMessage(), e);
        }
    }
}

