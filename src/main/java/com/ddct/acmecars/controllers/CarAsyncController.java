package com.ddct.acmecars.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddct.acmecars.models.Car;
import com.ddct.acmecars.services.CarService;
import com.ddct.acmecars.validation.CarsDtoValidator;

import lombok.extern.slf4j.Slf4j;

/**
 * CarController
 */
@RestController
@RequestMapping("/async")
@Slf4j
public class CarAsyncController {

    private final CarService service;
    private final CarsDtoValidator carsDtoValidator;

    @Autowired
    public CarAsyncController(CarService service, CarsDtoValidator carsDtoValidator) {
        this.service = service;
        this.carsDtoValidator = carsDtoValidator;
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.addValidators(
            carsDtoValidator);
    }

    @PostMapping("/cars")
    public CompletableFuture<List<Car>> saveCars( @Valid @RequestBody CarsDto cars)
    {
        log.info("saveCars - entered");
        return service.saveCarsAsync(cars.getCars());
    }
}