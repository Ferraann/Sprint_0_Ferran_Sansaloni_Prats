<?php

include '../../api/conexion.php';
include '../funciones/LogicaMediciones.php';

// Crear conexión
$logica = new LogicaMediciones($conn);

// =========================
// TEST 1: Insertar medición
// =========================
echo "Test 1: Guardar medición --> \t";

try {
    // Le pasamos datos datos fijos como parámetro a la funcion guadarMedicion()
    $id = $logica->guardarMedicion(
        "TestSensor",
        "UUID-TEST-999",
        -55,
        1,
        2,
        40.1111,
        -3.2222,
        480,
        2
    );
    // Si se ha podido enviar aparece este mensaje:
    echo "Medición insertada correctamente. ID: $id \n\n";
} catch (Exception $e) {
    // Error
    echo "Error al insertar la medición: " . $e->getMessage() . "\n\n";
}

// =========================
// TEST 2: Leer mediciones
// =========================
echo "Test 2: Recoger mediciones --> \t";
// Le pasamos a la funcion recogerMediciones() el id que acabamos de enviar para que compruebe si tiene mediciones
$mediciones = $logica->recogerMediciones(2);
if (count($mediciones) > 0) {
    echo "Se han recuperado " . count($mediciones) . " mediciones. \n\n";
} else {
    echo "No se han recuperado mediciones. \n\n";
}

// =========================
// TEST 3: Mostrar contenido
// =========================
echo "Test 3: Mostrar datos --> \n";
echo json_encode($mediciones, JSON_PRETTY_PRINT);

// =========================
// TEST 4: Limpieza
// =========================
// Si hay alguna medicion ligada a ese id la borramos
if (!empty($id)) {
    $conn->query("DELETE FROM mediciones WHERE id_medicion = $id");
    echo "\n\nMedición de prueba eliminada.";
}

$conn->close();

?>