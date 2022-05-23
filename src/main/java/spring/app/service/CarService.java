package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.app.models.Car;
import spring.app.repositories.SpringDataCarRepository;
import spring.app.repositories.SpringJdbcRepository;
import spring.app.repositories.SpringORMrepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {


    private SpringJdbcRepository carRepository;

    @Autowired
    public CarService(SpringJdbcRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> findByName(String name) {
        return carRepository.findByName(name);
    }

    public void deleteId(int id) {
        carRepository.deleteById(id);

    }

    public void save(Car car) {
         carRepository.save(car);
    }


    public Optional<Car> getCar(int id) {
        return carRepository.findById(id);

    }

    public List<Car> moreThan(int price){
        return carRepository.moreThan(price);
    }

    public List<Car> lessThan(int price){
        return carRepository.lessThan(price);
    }

}
