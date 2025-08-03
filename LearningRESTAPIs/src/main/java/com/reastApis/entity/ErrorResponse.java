package com.reastApis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse 
{
	private String timestamp; // ⬅️ now it's a formatted string
    private int status;
    private String message;
}
