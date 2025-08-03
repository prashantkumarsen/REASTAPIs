package com.reastApis.exception;
public class ResourceNotFoundException extends RuntimeException
{
	public ResourceNotFoundException(String message) 
	{
        super(message);
    }
}
