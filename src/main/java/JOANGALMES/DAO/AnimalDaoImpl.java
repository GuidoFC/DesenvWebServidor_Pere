package JOANGALMES.DAO;

import JOANGALMES.model.Animal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AnimalDaoImpl implements AnimalDAO{
    EntityManager entityManager;
    public AnimalDaoImpl() {
     EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
     entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Animal> findAll() {
        String sql = "select a from Animal a";
        List<Animal> animals = this.entityManager.createQuery(sql, Animal.class).getResultList();
        return animals;
    }

    @Override
    public Animal findById(Long aLong) {
        return null;
    }

    @Override
    public void save(Animal animal) {

    }

    @Override
    public void delete(Animal animal) {

    }
}
