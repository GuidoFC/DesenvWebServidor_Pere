package principal.demo.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import principal.demo.model.Alumno;

public class FindAlumno {
    public static void main(String[] args) {
        // Crear una instancia de EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Iniciar una transacción (no es necesaria para lecturas, pero la incluimos como precaución)
        em.getTransaction().begin();

        // Buscar el Alumno que deseas obtener (en este ejemplo, lo buscamos por id)
        // Asegúrate de reemplazar '1' con el id correcto del Alumno que deseas obtener
        Alumno alumno = em.find(Alumno.class, 2);

        if (alumno != null) { // Verificar si el alumno fue encontrado
            // Obtener la información del alumno y mostrarla
            System.out.println("ID: " + alumno.getId());
            System.out.println("Nombre: " + alumno.getNombre());
            System.out.println("Apellido: " + alumno.getApellido());
            System.out.println("Fecha de Nacimiento: " + alumno.getFechaNacimiento());
        } else {
            System.out.println("Alumno no encontrado");
        }

        // Confirmar la transacción (si no se hicieron cambios, no es necesario)
        em.getTransaction().commit();

        // Cerrar el EntityManager y el EntityManagerFactory
        em.close();
        emf.close();
    }
}
