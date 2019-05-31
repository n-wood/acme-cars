package com.ddct.acmecars.models;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
    @NotBlank( message = "{car.make.missing}" )
    private String make;
    @NotBlank( message = "{car.model.missing}" )
    private String model;
    @NotNull
    @Valid
    private Engine engine;
}