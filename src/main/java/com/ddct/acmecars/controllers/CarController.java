package com.ddct.acmecars.controllers;

import java.util.List;

import javax.validation.Valid;

import com.ddct.acmecars.models.Car;
import com.ddct.acmecars.services.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CarController
 */
@RestController
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping("/cars")
    public List<Car> getCars()
    {
        return service.getAllCars();
    }

    @PostMapping("/cars")
    public Car saveCar( @Valid @RequestBody Car car)
    {
        return service.saveCar(car);
    }

    @GetMapping("/cars/makes/{make}")
    public List<Car> getCar(@PathVariable String make)
    {
        return service.getSpecificCar(make);
    }
}