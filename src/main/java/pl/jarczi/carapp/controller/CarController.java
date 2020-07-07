package pl.jarczi.carapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.jarczi.carapp.dao.entity.Car;
import pl.jarczi.carapp.service.CarService;

import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    @ResponseBody
    public Iterable<Car> getAll() {
        return carService.findAll();
    }

    @GetMapping("/getById")
    @ResponseBody
    public Optional<Car> getById(@RequestParam Long index) {
        return carService.findById(index);
    }

    @GetMapping("/carService")
    public String showMethods() {
        return "car-service";
    }

    @PostMapping
    public String addCar(Car car) {
        carService.save(car);
        return "redirect:/cars/carService";
    }

    @PutMapping
    public String updateCar(Car car) {
        carService.save(car);
        return "redirect:/cars/carService";
    }

    @DeleteMapping
    public String delete(Car car) {
        carService.delete(car);
        return "redirect:/cars/carService";
    }
}
