package com.ddct.acmecars.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Engine {
    @Pattern(regexp = "PETROL|DIESEL|ELECTRIC")
    private String type;
    @NotNull
    @Max(value = 3000)
    private Integer capacity;
}
