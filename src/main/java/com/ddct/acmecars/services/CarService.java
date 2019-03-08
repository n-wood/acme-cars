package com.ddct.acmecars.services;

import java.util.List;

import com.ddct.acmecars.models.Car;

/**
 * CarService
 */

public interface CarService {

    public List<Car> getAllCars();

    public Car saveCar(Car car);

    public List<Car> getSpecificCar(String make);
    
}