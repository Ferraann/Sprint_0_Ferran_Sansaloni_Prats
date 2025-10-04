<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');

include '../../servidor/api/conexion.php';

$sql = "SELECT * FROM mediciones ORDER BY timestamp ASC";
$result = $conn->query($sql);

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
