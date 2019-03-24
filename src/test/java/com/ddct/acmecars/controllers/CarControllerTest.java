package com.ddct.acmecars.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.ddct.acmecars.repos.CarRepo;


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
				                                   .content( SUCCESSFUL_INPUT ) );
		
		resultActions.andExpect( status().isOk() );
	}
	
	private Car getCar()
	{
		Car car = new Car();
		
		car.setMake( "Ford" );
		car.setModel( "Focus" );
		
		return car;
	}

}
