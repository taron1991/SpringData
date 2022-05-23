package spring.app.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.app.models.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataCarRepository extends JpaRepository<Car, Integer> {

    @Query(value = "select * from cardb where name =:name",nativeQuery = true)
    public List<Car> findByName(@Param("name") String name);

    @Query(value = "select * from cardb where price >:price",nativeQuery = true)
    public List<Car> moreThan(@Param("price") int price);


    @Query(value = "select * from cardb where price <:price",nativeQuery = true)
    public List<Car> lessThan(@Param("price") int price);

}
