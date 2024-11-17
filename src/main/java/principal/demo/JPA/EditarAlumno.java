package principal.demo.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import principal.demo.model.Alumno;

public class EditarAlumno {

    public static void main(String[] args) {
        // Crear una instancia de EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Iniciar una transacción
        em.getTransaction().begin();

        // Buscar el Alumno que deseas modificar (en este ejemplo, lo buscamos por id)
        // Asegúrate de reemplazar '1' con el id correcto del Alumno que deseas actualizar
        Alumno alumno = em.find(Alumno.class, 2);

        if (alumno != null) { // Verificar si el alumno fue encontrado
            // Modificar el apellido
            alumno.setApellido("Castro");

            // La entidad se actualizará automáticamente cuando confirmes la transacción
            em.getTransaction().commit();
            System.out.println("Apellido actualizado a Castro");
        } else {
            System.out.println("Alumno no encontrado");
            em.getTransaction().rollback();
        }

        // Cerrar el EntityManager y el EntityManagerFactory
        em.close();
        emf.close();
    }
}

