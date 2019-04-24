package com.ddct.acmecars.controllers;

import java.util.List;

import javax.validation.Valid;

import com.ddct.acmecars.models.Car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarsDto {
    @Valid
    private List<Car> cars;
}
