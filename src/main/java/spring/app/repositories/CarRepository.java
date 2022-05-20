package spring.app.repositories;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.app.models.Car;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    public List<Car> findByName(String name);

    @Query(value = "select * from cardb where price <:price",nativeQuery = true)
    List<Car> morethan(@Param("price") int price);

    @Query(value = "select * from cardb where color =:color ",nativeQuery = true)
    List<Car> getCarsByColor(@Param("color") String color);

}
