package com.ddct.acmecars.services;

import java.util.List;

import com.ddct.acmecars.models.Car;
import com.ddct.acmecars.repos.CarRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * CarServiceImpl
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepo repo;

    @Override
    public List<Car> getAllCars() {
        return repo.findAll();
    }

    @Override
    public Car saveCar(Car car) {
        return repo.save(car);
    }

    @Override
    public List<Car> getSpecificCar(String make) {
        return repo.findByMake(make);
    }

    
}