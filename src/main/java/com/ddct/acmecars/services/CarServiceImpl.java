package com.ddct.acmecars.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ddct.acmecars.models.Car;
import com.ddct.acmecars.repos.CarRepo;

import lombok.extern.slf4j.Slf4j;

/**
 * CarServiceImpl
 */
@Service
@Slf4j
public class CarServiceImpl implements CarService {

    private final List<String> excludeddMakes;

    @Autowired
    private CarRepo repo;

    @Autowired
    private TaskExecutor taskExecutor;

    public CarServiceImpl() {
        this.excludeddMakes = Arrays.asList("fiat", "mg");
    }

    @Override
    public List<Car> getAllCars() {
        log.info("getAllCars - entered");
        return repo.findAll();
    }

    @Async
    @Override
    public CompletableFuture<List<Car>> getAllCarsAsync() {
        log.info("getAllCarsAsync - entered");
        return CompletableFuture.completedFuture(getAllCars());
    }

    @Override
    public Car saveCar(Car car) {
        log.info("saveCar - entered: {}", car);
        if (excludeddMakes.contains(car.getMake().toLowerCase())) {
            throw new RuntimeException("Excluded car cannot be stored: " + car.toString());
        }
        //sleep(200); //to better demonstrate async vs non-async performance.
        return repo.save(car);
    }

    @Override
    public List<Car> saveCars(List<Car> cars) {
        log.info("saveCars - entered");
        Assert.notEmpty(cars, "Cars are required");
        List<Car> carIds = new ArrayList<>();
        for (Car car : cars) {
            carIds.add(saveCar(car));
        }
        return carIds;
    }

    @Async
    @Override
    public CompletableFuture<List<Car>> saveCarsAsync(List<Car> cars) {
        log.info("saveCarsAsync - entered");
        Assert.notEmpty(cars, "Cars are required");
        List<Car> savedCars = Collections.synchronizedList(new ArrayList<>());
        CompletableFuture[] futures = new CompletableFuture[cars.size()];
        int i = 0;
        for (Car car : cars) {
            futures[i++] = CompletableFuture.supplyAsync(
                () -> saveCar(car), taskExecutor
            ).thenAccept(savedCar -> {
                log.info("Populating result with car: {}", savedCar);
                savedCars.add(savedCar);
            }).exceptionally(ex -> {
                log.error(ex.getMessage(), ex);
                return null;
            });
        }
        CompletableFuture.allOf(futures).join();
        return CompletableFuture.completedFuture(savedCars);
    }

    @Override
    public List<Car> getSpecificCar(String make) {
        return repo.findByMake(make);
    }

    private void sleep(long milliseconds) {
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}