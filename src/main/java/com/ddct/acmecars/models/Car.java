package com.ddct.acmecars.models;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Car
 */
@ToString
@EqualsAndHashCode(of = {"make", "model"})
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {
    @Id
    @Getter
    @Setter
    @JsonIgnore
    private String id;
    private String make;
    private String model;

    /**
     * @return the make
     */
    @NotNull( message = "The make of the car must be defined" )
    public String getMake() {
        return make;
    }

    /**
     * @return the model
     */
    @NotNull( message = "The model of the car must be defined" )
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

}