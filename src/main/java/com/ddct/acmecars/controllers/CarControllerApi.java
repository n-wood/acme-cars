package com.ddct.acmecars.controllers;

import com.ddct.acmecars.models.Car;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Cars", description = "The api for setting and retrieving car data")
public interface CarControllerApi {


    @ApiOperation(value = "Gets a list of cars in the database", nickname = "getCars", notes = "", tags = {"cars"})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success")
    })
    @RequestMapping(value = "/cars", method = RequestMethod.GET)
    List<Car> getCars();


    @ApiOperation(value = "Stores a cars in the database", nickname = "saveCar", notes = "", tags = {"cars"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    @RequestMapping(value = "/car", method = RequestMethod.POST)
    Car saveCar(@RequestBody Car car);;

    @ApiOperation(value = "Gets a list of cars matching the given model", nickname = "getCar", notes = "", tags = {"cars"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success")
    })
    @RequestMapping(value = "/cars/makes/{make}", method = RequestMethod.GET)
    List<Car> getCar(@PathVariable String make);;
}
