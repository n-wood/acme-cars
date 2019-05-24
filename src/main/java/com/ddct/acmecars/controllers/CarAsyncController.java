package com.ddct.acmecars.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddct.acmecars.models.Car;
import com.ddct.acmecars.services.CarService;

import lombok.extern.slf4j.Slf4j;

/**
 * CarController
 */
@RestController
@RequestMapping("/async")
@Slf4j
public class CarAsyncController {

    @Autowired
    private CarService service;

    @PostMapping("/cars")
    public CompletableFuture<List<Car>> saveCars( @Valid @RequestBody CarsDto cars)
    {
        log.info("saveCars - entered");
        return service.saveCarsAsync(cars.getCars());
    }
}