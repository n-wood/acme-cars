package com.ddct.acmecars.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ddct.acmecars.models.Car;
import com.ddct.acmecars.models.Engine;
import com.ddct.acmecars.models.EngineType;
import com.ddct.acmecars.repos.CarRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarAsyncControllerTest
{

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private CarRepo carRepo;
	
	@Test
	public void testSaveCars() throws Exception
	{
		CarsDto carsDto = getCarsDto();
		List<Map<String, Object>> expectedCarsMapList = new ArrayList<>();

		for (Car car : carsDto.getCars()) {
			when(this.carRepo.save(eq(car))).thenReturn(car);
			expectedCarsMapList.add(
				objectMapper.convertValue(car, new TypeReference<Map<String, Object>>() {
				})
			);
		}

		ResultActions resultActions = mvc.perform( MockMvcRequestBuilders.post( "/async/cars" )
				                                   .contentType( MediaType.APPLICATION_JSON )
				                                   .content( toJson(carsDto) ) );

		resultActions.andExpect(status().isOk());

		mvc.perform(asyncDispatch(resultActions.andReturn()))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", Matchers.hasSize(carsDto.getCars().size())))
			.andExpect(jsonPath("$", Matchers.containsInAnyOrder(expectedCarsMapList.toArray())));
		
		verify(carRepo, Mockito.times(carsDto.getCars().size())).save(any(Car.class));
	}

	private CarsDto getCarsDto() {
		return new CarsDto(
			Arrays.asList(
				new Car(null, "Ford", "Focus", getPetrolEngine(1600)),
				new Car(null, "BMW", "X5", getPetrolEngine(2000)),
				new Car(null, "Audi", "A4", getPetrolEngine(2000)),
				new Car(null, "Audi", "A6", getPetrolEngine(3000))
			));
	}

	private String toJson(Object obj) throws Exception {
		return objectMapper.writeValueAsString(obj);
	}

	private Engine getPetrolEngine(Integer capacity) {
		return new Engine(
			EngineType.PETROL.name(),
			capacity
		);
	}
}
