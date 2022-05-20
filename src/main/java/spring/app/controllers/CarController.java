package spring.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@RequestMapping("/car")
public class CarController {

  private CarService carService;
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/allCars")
    public String allCars(Model model){
        List<Car> list = carService.findAll();
        model.addAttribute("save",list);
        return "all_cars";
    }

    @GetMapping("/byId")
    public String getById(@RequestParam("req") Integer id, Model model){
        Car optionalCar = carService.getId(id).get();
        model.addAttribute("carId",optionalCar);
        return "view_car";
    }

    @GetMapping("/byName")
    public String findByName(@RequestParam("req") String name,Model model){
        List<Car> byNames = carService.findByNames(name);
        model.addAttribute("names",byNames);
        return "view_names";
    }


    @GetMapping("/deleteName")
    public String deleteByName(@RequestParam("req") Integer id,Model model){
        carService.deleteId(id);
        model.addAttribute("allcars",carService.findAll());
        return "all_cars";
    }

    @GetMapping("/save")
    public String saveCar(Model model){
        model.addAttribute("save",new Car());
        return "asker";
    }

    @GetMapping("/results")
    public String getAll(@ModelAttribute("save") Car car){
        Car cars = carService.save(car);
        return "redirect:/car/allCars";
    }

    @GetMapping("/update")
    public String updateCar(@RequestParam("req") Integer id,
                            Model model){
        Car car = carService.getCar(id).get();
        model.addAttribute("save",car);

        return "asker";
    }


    @GetMapping("/price")
    public String getPricesMoreThan(@RequestParam("price") Integer id, Model model){
        List<Car> cars = carService.moreThan(id);
        model.addAttribute("priceval",cars);
        return "prices";
    }


    @GetMapping("/color")
    public String getCarsByColor(@RequestParam("color") String color,Model model){
        List<Car> cars = carService.getCars(color);
        model.addAttribute("val",cars);
        return "car_colors";
    }


}
