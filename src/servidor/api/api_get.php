<?php
// ------------------------------------------------------------------
// Fichero: api_get.php
// Autor: Ferran Sansaloni Prats
// Fecha: 04/10/2025
// ------------------------------------------------------------------
// Descripción:
//   Este fichero devuelve en formato JSON todas las mediciones
//   almacenadas en la base de datos. Permite filtrar por ID de sensor
//   usando el parámetro GET 'id_sensor'.
// ------------------------------------------------------------------

// Configuración de cabeceras HTTP
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET');

// Incluimos la conexión a la base de datos
include 'conexion.php';

// --------------------------------------------------------------
// Lectura del parámetro GET
// --------------------------------------------------------------
// Descripción:
//   - Si se pasa `id_sensor`, se filtrarán las mediciones de ese sensor
//   - Si no se pasa, se devuelven todas las mediciones
// --------------------------------------------------------------
$id_sensor = isset($_GET['id_sensor']) ? intval($_GET['id_sensor']) : null;

// Consulta a la base de datos
if ($id_sensor) {
    // Filtrando por sensor
    // Y ordenamos por la fecha en orden ascendetne
    $stmt = $conn->prepare("SELECT * FROM mediciones WHERE id_sensor = ? ORDER BY timestamp ASC");
    $stmt->bind_param("i", $id_sensor);
    $stmt->execute();
    $result = $stmt->get_result();
} else {
    // Sin filtro, todas las mediciones
    $result = $conn->query("SELECT * FROM mediciones ORDER BY timestamp ASC");
}

// Convertir resultados a JSON
$mediciones = [];
if ($result) {
    while ($row = $result->fetch_assoc()) {
        $mediciones[] = $row;
    }
}

// Respuesta JSON
echo json_encode([
    "success" => true,
    "mediciones" => $mediciones
]);

$conn->close();

// ------------------------------------------------------------------
// ------------------------------------------------------------------
// ------------------------------------------------------------------
// ------------------------------------------------------------------
?>
