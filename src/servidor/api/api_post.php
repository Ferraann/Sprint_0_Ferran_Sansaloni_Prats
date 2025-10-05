<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: POST');

include 'conexion.php';

// Leer el cuerpo del JSON
$input = json_decode(file_get_contents("php://input"), true);

// Validar campos esenciales
if (!isset($input['nombre']) || !isset($input['uuid']) || !isset($input['rssi']) || !isset($input['medicionCo2'])) {
    echo json_encode(["success" => false, "mensaje" => "Faltan datos"]);
    exit;
}

// Extraer variables
$id_sensor = isset($input['id_sensor']) ? intval($input['id_sensor']) : 1;
$nombre = $input['nombre'];
$uuid = $input['uuid'];
$rssi = intval($input['rssi']);
$major = isset($input['major']) ? intval($input['major']) : 0;
$minor = isset($input['minor']) ? intval($input['minor']) : 0;
$latitud = isset($input['latitud']) ? floatval($input['latitud']) : 0.0;
$longitud = isset($input['longitud']) ? floatval($input['longitud']) : 0.0;
$co2 = intval($input['medicionCo2']);

// Preparar e insertar en la base de datos
$stmt = $conn->prepare("INSERT INTO mediciones 
    (id_sensor, nombre, uuid, rssi, major, minor, latitud, longitud, medicionCo2, timestamp) 
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())");

$stmt->bind_param("issiiiidd", $id_sensor, $nombre, $uuid, $rssi, $major, $minor, $latitud, $longitud, $co2);

// Ejecución y respuesta
if ($stmt->execute()) {
    echo json_encode(["success" => true, "mensaje" => "Medida guardada correctamente"]);
} else {
    echo json_encode(["success" => false, "mensaje" => "Error al guardar: " . $stmt->error]);
}

$stmt->close();
$conn->close();
?>
