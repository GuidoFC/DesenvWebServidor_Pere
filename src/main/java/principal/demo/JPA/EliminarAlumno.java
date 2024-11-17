package principal.demo.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import principal.demo.model.Alumno;

public class EliminarAlumno {
    public static void main(String[] args) {
    // Crear una instancia de EntityManagerFactory
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
    EntityManager em = emf.createEntityManager();

    // Iniciar una transacción
        em.getTransaction().begin();

    // Buscar el Alumno que deseas eliminar (en este ejemplo, lo buscamos por id)
    // Asegúrate de reemplazar '1' con el id correcto del Alumno que deseas eliminar
    Alumno alumno = em.find(Alumno.class, 1);

        if (alumno != null) { // Verificar si el alumno fue encontrado
        // Eliminar el alumno
        em.remove(alumno);

        // Confirmar la transacción
        em.getTransaction().commit();
        System.out.println("Alumno eliminado de la base de datos");
    } else {
        System.out.println("Alumno no encontrado");
        em.getTransaction().rollback();
    }

    // Cerrar el EntityManager y el EntityManagerFactory
        em.close();
        emf.close();
}
}
