<?php
// ============================================================
//
// En este fichero recibiremos els json enviado desde el android studio, comprobaremos que los datos son válidos y en
// ese caso, enviaremos los datos al método de guardarMedicion() de la clase Logica. Si no recibimos bien los datos
// mandarmos un error.
//
// ============================================================
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');

include 'conexion.php';
include '../logica/LogicaMediciones.php';

// Leer el cuerpo del JSON
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
?>
