package spring.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.app.models.Car;
import spring.app.repositories.CarRepository;

import java.io.OptionalDataException;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {


    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll() {
        return carRepository.findAll();
    }


    public Optional<Car> getId(int id) {
        Optional<Car> byId = carRepository.findById(id);
        return byId;
    }

    public List<Car> findByNames(String name) {
        return carRepository.findByName(name);
    }

    public void deleteId(int id) {
        carRepository.deleteById(id);

    }

    public Car save(Car car) {
        return carRepository.save(car);
    }


    public Optional<Car> getCar(int id) {
        return carRepository.findById(id);

    }

    public List<Car> moreThan(int price){
        return carRepository.morethan(price);
    }


    public List<Car> getCars(String color){
        return carRepository.getCarsByColor(color);
    }
}
