<?php
// ------------------------------------------------------------------
// Fichero: api_post.php
// Autor: Ferran Sansaloni Prats
// Fecha: 04/10/2025
// ------------------------------------------------------------------
// Descripción:
//   Este fichero recibe un JSON enviado desde Android Studio,
//   valida los datos recibidos y, en caso de ser correctos,
//   los envía al método guardarMedicion() de la clase LogicaMediciones.
//   Si falta algún dato, devuelve un error.
// ------------------------------------------------------------------

// Configuración de cabeceras HTTP
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');

// Incluimos los ficheros necesarios
include 'conexion.php';
include '../logica/funciones/LogicaMediciones.php';

// Convierte el cuerpo recibido en un array asociativo de php
$input = json_decode(file_get_contents("php://input"), true);

// Si no recibo los siguientes datos...
if (!isset($input['uuid']) || !isset($input['rssi']) || !isset($input['medicionCo2'])) {
    // ... mando un mensaje diciendo que faltan datos
    echo json_encode(["success" => false, "mensaje" => "Faltan datos"]);
    exit;
}

try {
    // Creamos un objeto de LogicaMediciones
    $logica = new LogicaMediciones($conn);
    // Llamamos a guardarMedicion() y le pasamos los datos recibidos como parámetro
    $id = $logica->guardarMedicion(
        $input['nombre'],
        $input['uuid'],
        intval($input['rssi']),
        intval(isset($input['major']) ? $input['major'] : 0),
        intval(isset($input['minor']) ? $input['minor'] : 0),
        floatval(isset($input['latitud']) ? $input['latitud'] : 0.0),
        floatval(isset($input['longitud']) ? $input['longitud'] : 0.0),
        intval($input['medicionCo2']),
        intval(isset($input['id_sensor']) ? $input['id_sensor'] : 1)
    );

    // Mensaje para avisar que la medida se ha guardado correctamente y mostramos el id de la medida
    echo json_encode(["success" => true, "mensaje" => "Medida guardada correctamente", "id" => $id]);

} catch (Exception $e) {
    // Error
    echo json_encode(["success" => false, "mensaje" => $e->getMessage()]);
}
// ------------------------------------------------------------------
// ------------------------------------------------------------------
// ------------------------------------------------------------------
// ------------------------------------------------------------------
?>
