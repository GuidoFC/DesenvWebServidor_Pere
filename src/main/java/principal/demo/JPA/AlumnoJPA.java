package principal.demo.JPA;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import principal.demo.model.Alumno;

import java.io.Serializable;

public class AlumnoJPA implements Serializable {
    private EntityManagerFactory emf = null;
    public AlumnoJPA(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Alumno alumno) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }



}
