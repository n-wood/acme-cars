package com.ddct.acmecars.models;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Car
 */
@ToString
@EqualsAndHashCode(of = {"make", "model"})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {
    @Id
    private String id;
    @NotNull( message = "The make of the car must be defined" )
    private String make;
    @NotNull( message = "The model of the car must be defined" )
    private String model;
    @NotNull
    @Valid
    private Engine engine;

}