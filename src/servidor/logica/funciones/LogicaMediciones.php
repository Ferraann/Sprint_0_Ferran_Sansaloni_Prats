<?php
// ------------------------------------------------------------------
// Fichero: LogicaMediciones.php
// Autor: Ferran Sansaloni Prats
// Fecha: 05/10/2025
// ------------------------------------------------------------------
// Descripción:
//   Clase encargada de la lógica de mediciones en la base de datos.
//   Permite insertar nuevas mediciones y recuperar mediciones.
// ------------------------------------------------------------------
class LogicaMediciones {
    // Atributo de la clase
    private $conn;

    // --------------------------------------------------------------
    // Constructor
    // --------------------------------------------------------------
    // Descripción:
    //   Inicializa la clase con la conexión a la base de datos.
    // --------------------------------------------------------------
    public function __construct($conn) {
        $this->conn = $conn;
    }

    // --------------------------------------------------------------
    // Método: guardarMedicion
    // Descripción:
    //   Inserta una nueva medición en la base de datos.
    // Diseño:
    //   string: nombre, string: uuid, int: rssi, int: major, int: minor,
    //   int: latitud, int: longitud, int: co2, int: id_sensor --> guardarMedicion()
    //                                                                       int:insert_id <-- (ID de medición insertada)
    // --------------------------------------------------------------
    public function guardarMedicion($nombre, $uuid, $rssi, $major, $minor, $latitud, $longitud, $co2, $id_sensor = 1) {
        $stmt = $this->conn->prepare("
            INSERT INTO mediciones (id_sensor, nombre, uuid, rssi, major, minor, medicionCo2, latitud, longitud, timestamp)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())
        ");
        $stmt->bind_param("issiiiddd", $id_sensor, $nombre, $uuid, $rssi, $major, $minor, $co2, $latitud, $longitud);
        $stmt->execute();

        // Comprobar errores
        if ($stmt->error) {
            throw new Exception("Error al guardar: " . $stmt->error);
        }
        return $stmt->insert_id;
    } // ()

    // --------------------------------------------------------------
    // Método: recogerMediciones
    // Descripción:
    //   Recupera todas las mediciones de la base de datos o filtradas
    //   por un ID de sensor específico.
    // Diseño:
    //   int|null: id_sensor --> recogerMediciones()
    //                                     <-- [array asociativo con las mediciones]
    // --------------------------------------------------------------
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
    } // ()

} // class LogicaMediciones
// ------------------------------------------------------------------
// ------------------------------------------------------------------
// ------------------------------------------------------------------
// ------------------------------------------------------------------
?>
