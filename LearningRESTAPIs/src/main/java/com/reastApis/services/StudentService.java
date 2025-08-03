package com.reastApis.services;

import java.util.List;
import java.util.Map;

import com.reastApis.dto.AddStudentRequestSDto;
import com.reastApis.dto.StudentDto;

public interface StudentService 
{
	public StudentDto createStudent(AddStudentRequestSDto addStudentRequestSDto);
	List<StudentDto> getAllStudent();
	public StudentDto updateStudent(AddStudentRequestSDto addStudentRequestSDto,Long id);
	public StudentDto getSingle(Long id);
	void deleteStudent(Long id);
	public StudentDto updateParticalStudent(Long id, Map<String, Object> updates);
	
}
