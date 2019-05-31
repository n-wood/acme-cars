package com.ddct.acmecars.controllers;

import java.util.Collections;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest
{
	@Autowired
	private MockMvc mvc;

	@MockBean
	private CarRepo carRepo;

	@Test
	public void testSaveCar() throws Exception
	{
		when( this.carRepo.save( eq( getCar() ) ) ).thenReturn( getCar() );

		ResultActions resultActions = mvc.perform( MockMvcRequestBuilders.post( "/cars" )
			.contentType( MediaType.APPLICATION_JSON )
			.content( getCarsDtoJson() ) );

		resultActions.andExpect( status().isOk() );

		verify(carRepo, times(1)).save(eq(getCar()));
	}

	private Car getCar()
	{
		return
			new Car(
				UUID.randomUUID().toString(),
				"Tesla",
				"3",
				getElectricEngine()
			);
	}

	private String getCarsDtoJson() throws Exception {
		CarsDto carsDto = new CarsDto(
			Collections.singletonList(
				new Car(null, "Tesla", "3", getElectricEngine())
			));
		return new ObjectMapper().writeValueAsString(carsDto);
	}

	private Engine getElectricEngine() {
		return new Engine(
			EngineType.ELECTRIC.name(),
			0
		);
	}

}
