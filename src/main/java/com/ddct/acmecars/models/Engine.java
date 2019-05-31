package com.ddct.acmecars.models;

import javax.validation.constraints.Max;

import com.ddct.acmecars.validation.EnumValue;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Engine {

    @EnumValue(enumClass = EngineType.class)
    //@Pattern(regexp = "PETROL|DIESEL|ELECTRIC", message = "{validation.enum.message}")
    private String type;
    @Max(value = 3000, message = "{engine.capacity.max}")
    private Integer capacity;
}
