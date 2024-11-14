package javapro.spring._1.controllers;

import javapro.spring._1.models.Car;
import javapro.spring._1.repositories.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    private final CarRepository repository;
    public CarController(CarRepository repository) {
        this.repository = repository;
    }
    @GetMapping("/cars")
    public Iterable<Car> getCars() {
        return repository.findAll();
    }
}