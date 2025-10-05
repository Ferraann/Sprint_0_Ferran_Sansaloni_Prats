<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET');

include 'conexion.php';

// Si se pasa ?id_sensor=, filtra por ese sensor
$id_sensor = isset($_GET['id_sensor']) ? intval($_GET['id_sensor']) : null;

if ($id_sensor) {
    $stmt = $conn->prepare("SELECT * FROM mediciones WHERE id_sensor = ? ORDER BY timestamp ASC");
    $stmt->bind_param("i", $id_sensor);
    $stmt->execute();
    $result = $stmt->get_result();
} else {
    $result = $conn->query("SELECT * FROM mediciones ORDER BY timestamp ASC");
}

// Convertir resultados a JSON
$mediciones = [];
if ($result) {
    while ($row = $result->fetch_assoc()) {
        $mediciones[] = $row;
    }
}

echo json_encode([
    "success" => true,
    "mediciones" => $mediciones
]);

$conn->close();
?>
