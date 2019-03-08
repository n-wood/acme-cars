package com.ddct.acmecars.repos;

import java.util.List;

import com.ddct.acmecars.models.Car;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CarRepo
 */
public interface CarRepo extends MongoRepository<Car, String>{
    List<Car> findByMake(String make);
    
}