package com.ddct.acmecars.controllers;

import java.util.Arrays;

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
import com.ddct.acmecars.repos.CarRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest
{
	private static final String SUCCESSFUL_INPUT = "{" +
                                                         "\"make\" : \"Ford\"," +
                                                         "\"model\" : \"Focus\"" +
                                                    "}";
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

		//verify(carRepo, Mockito.times(1)).save(eq(getCar()));
	}
	
	private Car getCar()
	{
		Car car = new Car();
		
		car.setMake( "Ford" );
		car.setModel( "Focus" );
		
		return car;
	}

	private String getCarsDtoJson() throws Exception {
		CarsDto carsDto = new CarsDto(
			Arrays.asList(
				new Car(null, "Ford", "Focus")
			));
		return new ObjectMapper().writeValueAsString(carsDto);
	}

}
