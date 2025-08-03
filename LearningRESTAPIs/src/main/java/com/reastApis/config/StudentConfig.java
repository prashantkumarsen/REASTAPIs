package com.reastApis.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig 
{
	@Bean
	ModelMapper modelMapper()
	{
		return new ModelMapper();		
	}
}
