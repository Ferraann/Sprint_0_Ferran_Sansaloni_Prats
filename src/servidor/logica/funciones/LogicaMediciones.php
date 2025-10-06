<?php
class LogicaMediciones {
    private $conn;

    public function __construct($conn) {
        $this->conn = $conn;
    }

    // ------------------------------------------
    // Guardar una medición en la base de datos
    // ------------------------------------------
    public function guardarMedicion($nombre, $uuid, $rssi, $major, $minor, $latitud, $longitud, $co2, $id_sensor = 1) {
        $stmt = $this->conn->prepare("
            INSERT INTO mediciones (id_sensor, nombre, uuid, rssi, major, minor, medicionCo2, latitud, longitud, timestamp)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())
        ");
        $stmt->bind_param("issiiiddd", $id_sensor, $nombre, $uuid, $rssi, $major, $minor, $co2, $latitud, $longitud);
        $stmt->execute();

        if ($stmt->error) {
            throw new Exception("Error al guardar: " . $stmt->error);
        }
        return $stmt->insert_id;
    }

    // ------------------------------------------
    // Obtener todas las mediciones (o por sensor)
    // ------------------------------------------
    public function recogerMediciones($id_sensor = null) {
        if ($id_sensor) {
            $stmt = $this->conn->prepare("SELECT * FROM mediciones WHERE id_sensor = ?");
            $stmt->bind_param("i", $id_sensor);
        } else {
            $stmt = $this->conn->prepare("SELECT * FROM mediciones");
        }

        $stmt->execute();
        $result = $stmt->get_result();
        return $result->fetch_all(MYSQLI_ASSOC);
    }
}
?>
