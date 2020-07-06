package pl.jarczi.carapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.jarczi.carapp.repo.CarRepo;
import pl.jarczi.carapp.dao.entity.Car;
import pl.jarczi.carapp.model.Color;

import java.util.Optional;

@Service
public class CarService {

    private CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Iterable<Car> findAll() {
        return carRepo.findAll();
    }

    public Optional<Car> findById(Long id) {
        return carRepo.findById(id);
    }

    public Car save(Car car) {
        if(car.getMark() == null || car.getModel() == null || car.getColor() == null) {
            throw new IllegalArgumentException();
        } else {
            return carRepo.save(car);
        }
    }

    public void delete(Car car) {
        carRepo.delete(car);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fillDB() {
        save(new Car("Honda", "Civic", Color.BLACK));
        save(new Car("BMW", "x5", Color.WHITE));
    }
}
