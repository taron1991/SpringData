package spring.app.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.app.models.Car;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class SpringORMrepository {

    private SessionFactory sessionFactory;

    @Autowired
    public SpringORMrepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Car> findAll() {
        List list = null;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            list = session.createQuery("from Car").getResultList();
            session.getTransaction().commit();

        }
        return list;
    }


    public Optional<Car> findById(int id) {
        Optional<Car> optionalCar = null;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Car car = session.get(Car.class, id);
            optionalCar = Stream.of(car).findAny();
            session.getTransaction().commit();

        }
        return optionalCar;
    }


    public void deleteById(int id) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Car car = session.get(Car.class, id);
            session.delete(car);
            session.getTransaction().commit();
        }
    }


    public void save(Car car) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.save(car);
            session.getTransaction().commit();
        }
    }


    public List<Car> findByName(String name) {
        List list = null;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("from Car where name =:name ");
            query.setParameter("name", name);
            list = query.getResultList();
            session.getTransaction().commit();
        }
        return list;
    }


    public List<Car> moreThan(int price) {
        List list = null;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("from Car where price >:price");
            query.setParameter("price", price);
            list = query.getResultList();
            session.getTransaction().commit();
        }
        return list;
    }

    public List<Car> lessThan(int price) {
        List list = null;
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("from Car where price <:price");
            query.setParameter("price", price);
            list = query.getResultList();
            session.getTransaction().commit();
        }
        return list;
    }
}
