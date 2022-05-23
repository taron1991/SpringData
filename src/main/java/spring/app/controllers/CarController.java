package spring.app.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spring.app.models.Car;
import spring.app.service.CarService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/car")
@Slf4j
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/allCars")
    public String allCars(Model model) {
        List<Car> list = carService.findAll();
        model.addAttribute("save", list);
        log.info("allCars");
        return "all_cars";
    }

    @GetMapping("/byName")
    public String findByName(@RequestParam("req") String name, Model model) {
        List<Car> byNames = carService.findByName(name);
        model.addAttribute("names", byNames);
        log.info("byname");
        return "view_names";
    }


    @GetMapping("/deleteName")
    public String deleteByName(@RequestParam("req") Integer id, Model model) {
        carService.deleteId(id);
        model.addAttribute("save", carService.findAll());
        return "all_cars";
    }

    @GetMapping("/save")
    public String saveCar(Model model) {
        model.addAttribute("save", new Car());
        return "asker";
    }

    @GetMapping("/results")
    public String getAll(@ModelAttribute Car car) {
        carService.save(car);
        return "redirect:/car/allCars";
    }

    @GetMapping("/update")
    public String updateCar(@RequestParam("req") Integer id,
                            Model model) {
        Car car = carService.getCar(id).get();
        model.addAttribute("save", car);

        return "asker";
    }

    @GetMapping("/byId")
    public String getById(@RequestParam("req") Integer id, Model model) {
        Car optionalCar = carService.getCar(id).get();
        model.addAttribute("carId", optionalCar);
        return "view_car";
    }

    @GetMapping("/price")
    public String getPricesMoreOrLess(@RequestParam("price") Integer id,
                                      @RequestParam("MoreOrLess") String str,
                                      Model model) {
        List<Car> cars;

        switch (str) {
            case "<" -> cars = carService.lessThan(id);
            case ">" -> cars = carService.moreThan(id);
            default -> throw new IllegalStateException();
        }

        model.addAttribute("priceval", cars);
        return "prices";
    }

}
