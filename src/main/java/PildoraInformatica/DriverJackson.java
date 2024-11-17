package PildoraInformatica;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.JsonObject;

import java.io.File;

public class DriverJackson {
    public static void main(String[] args) {
        try {
            // crear un objeto mapper
            ObjectMapper miMapper = new ObjectMapper();

            // leer el archivo json y convertilo a POJO
                // ahora tengo toda la informacion de Json
            Empleado miEmpleado = miMapper.readValue(new File("src/main/java/principal/demo/DataExterna/datos_empleado.json"), Empleado.class);
            // ver informacion en consola
            System.out.println("Nombre: " + miEmpleado.getNombre());

            for (String idioma: miEmpleado.getIdiomas()) {
                System.out.println("Idioma que habla: " + idioma);
            }

            Datos_Empleado datosEmpleado = miEmpleado.getDatos_registro();

            System.out.println("Domicilio Empleado " + datosEmpleado.getDomicilio());

            System.out.println("Codigo Postal Empleado " + datosEmpleado.getCodigo_postal());

            // TODO seguir con este video: https://www.youtube.com/watch?v=yaPmwQASyu8&list=PLU8oAlHdN5Blq85GIxtKjIXdfHPksV_Hm&index=111&ab_channel=pildorasinformaticas

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
