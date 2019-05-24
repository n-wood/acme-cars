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

import lombok.extern.slf4j.Slf4j;

/**
 * CarController
 */
@RestController
@Slf4j
public class CarController {

    @Autowired
    private CarService service;

    @GetMapping("/cars")
    public List<Car> getCars()
    {
        log.info("getCars - entered");
        return service.getAllCars();
    }

    @PostMapping("/cars")
    public List<Car> saveCar( @Valid @RequestBody CarsDto cars)
    {
        return service.saveCars(cars.getCars());
    }

    @GetMapping("/cars/makes/{make}")
    public List<Car> getCar(@PathVariable String make)
    {
        return service.getSpecificCar(make);
    }
}