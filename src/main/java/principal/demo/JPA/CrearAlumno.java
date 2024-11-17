package principal.demo.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import principal.demo.model.Alumno;

import java.util.Date;

public class CrearAlumno {
    public static void main(String[] args) {
        // Crear una instancia de EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();

        // Crear una instancia de Alumno
        Alumno alumno = new Alumno();
        alumno.setNombre("nuevo13");
        alumno.setApellido("123456");
        alumno.setFechaNacimiento(new Date()); // Usar la fecha actual

        // Iniciar una transacción, persistir el objeto y confirmar la transacción
        em.getTransaction().begin();
        em.persist(alumno);
        em.getTransaction().commit();

        // Cerrar el EntityManager y el EntityManagerFactory
        em.close();
        emf.close();

        System.out.println("Alumno guardado en la base de datos");
    }
}
