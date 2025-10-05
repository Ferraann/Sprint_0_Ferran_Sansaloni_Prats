document.addEventListener("DOMContentLoaded", () => {
    const tableBody = document.querySelector("#medicionesTable tbody");

    // URL del archivo PHP que recoge las medicones de la base de datos
    const apiURL = "http://192.168.1.48/Sprint_0_Ferran_Sansaloni_Prats/src/servidor/api/api_get.php";

    // Llama a la api que se encuentra en la url
    fetch(apiURL)
        // Transforma la respuesta en json
        .then(response => response.json())
        // Recoge los datos...
        .then(data => {
            // ... si encuentra datos...
            if (data.success && data.mediciones) {
                // ... recorre los datos y los mete a la tabla de HTML
                data.mediciones.forEach(medida => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td data-label="ID_medicion">${medida.id_medicion}</td>
                        <td data-label="ID_sensor">${medida.id_sensor}</td>
                        <td data-label="CO2">${medida.medicionCo2}</td>
                        <td data-label="Fecha">${medida.timestamp}</td>
                        <td data-label="UUID">${medida.uuid}</td>
                    `;
                    tableBody.appendChild(row);
                });
            } else {
                // Si no encuentra datos dice que NO HAY DATOS DISPONIBLES
                tableBody.innerHTML = `<tr><td colspan="10">No hay datos disponibles</td></tr>`;
            }
        })
        // Error
        .catch(err => {
            console.error("Error al cargar los datos:", err);
            tableBody.innerHTML = `<tr><td colspan="10">Error al cargar los datos</td></tr>`;
        });
});
