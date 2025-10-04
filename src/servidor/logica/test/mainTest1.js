
// --------------------------------------------------------------------------------
// mainTest1.js
// --------------------------------------------------------------------------------
const logica = require( "../logica.js" )

var assert = require ('assert')

// --------------------------------------------------------------------------------
// main ()
// --------------------------------------------------------------------------------
describe( "Pruebas de mediciones", function() {

    let laLogica;
    let idMedicion;

    //
    // 0. Inicializa la lógica y conecta con la bbdd SQlite
    //
    before(async function() {

        laLogica = await logica('../../bd/datos.bd')

    }); // before

    //
    // 1.1 Guarda una medición
    // Inserta una nueva medición en la tabla 'mediciones'
    //
    // 1.2 Recoger la medicion insertada
    // Consulta las mediciones del sensor con id_sensor = 1 y verifia que la medicion insertada existe y tiene los valores correctos
    //
    it('Guardar y recoger una medicion', async function() {

        //
        // 1.1 Guarda una medición
        //

        // Datos que vamos a insertar
        const datos = {
            id_sensor: 1,
            latitud: 40.123456,
            longitud: -3.123456,
            medicionCo2: 450,
            temperatura: 22.5,
            humedad: 50.3
        };

        // Llamamos al metodo pasandole los datos para que los guarde
        const res = await laLogica.llamar('guardarMedicion.js', datos);
        assert.ok(res.id_medicion, 'No se insertó la medición');
        idMedicion = res.id_medicion;


        //
        // 1.2 Recoger la medicion insertada
        //

        // Llamamos al metodo recogerMedicion y le pasamos el id del sensor 1
        const resultado = await laLogica.llamar('recogerMedicion.js', { id_sensor: 1 });
        assert.ok(resultado.length > 0, 'No se recuperaron mediciones');

        // buscamos nuestra medición por id_medicion
        const medicion = resultado.find(m => m.id_medicion === idMedicion);
        assert.ok(medicion, 'No se encontró la medición guardada');
        assert.equal(medicion.medicionCo2, 450, 'El valor de CO2 no coincide');
    });

    //
    // 2. limpieza
    // Bora la medicion insertada para que el test no aparezca en la lista de mediciones y no altere las mediciones de la base de datos
    //
    after(async function() {
        // Si encuentra la medicion la elimina. "DELETE FROM mediciones"
        if (idMedicion) {
            await laLogica.llamar('borrarMedicion.js', { id_medicion: idMedicion });
        }
    }); // after

}) // describe

// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
// --------------------------------------------------------------------------------
