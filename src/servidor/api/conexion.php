<?php
// ------------------------------------------------------------------
// Fichero: conexion.php
// Autor: Ferran Sansaloni Prats
// Fecha: 01/10/2025
// ------------------------------------------------------------------
// Descripción:
//   Establece la conexión a la base de datos MySQL y configura los
//   encabezados para respuestas JSON
// ------------------------------------------------------------------

// Configuración de cabeceras HTTP
header('Content-Type: application/json');
header('Access-Control-Allow-Origin: *');

// Datos para la conexión
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "mediciones_sprint0";

// Conexión a la base de datos
$conn = new mysqli($servername, $username, $password, $dbname);

// Comprobar errores de conexión
if ($conn->connect_error) {
    die(json_encode(["error" => "Conexión fallida: " . $conn->connect_error]));
}

// ------------------------------------------------------------------
// ------------------------------------------------------------------
// ------------------------------------------------------------------
// ------------------------------------------------------------------
?>