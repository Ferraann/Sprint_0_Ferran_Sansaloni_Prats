<?php
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');

include 'conexion.php'; // Usamos la conexión centralizada

$sql = "INSERT";
$result = $conn->query($sql);

$datos = [];
while($row = $result->fetch_assoc()) {
    $datos[] = $row;
}

echo json_encode($datos);

$conn->close();
?>
