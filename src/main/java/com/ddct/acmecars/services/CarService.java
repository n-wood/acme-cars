package com.ddct.acmecars.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.ddct.acmecars.models.Car;

/**
 * CarService
 */

public interface CarService {

    List<Car> getAllCars();

    CompletableFuture<List<Car>> getAllCarsAsync();

    Car saveCar(Car car);

    List<Car> getSpecificCar(String make);

    List<Car> saveCars(List<Car> cars);

    CompletableFuture<List<Car>> saveCarsAsync(List<Car> cars);

}