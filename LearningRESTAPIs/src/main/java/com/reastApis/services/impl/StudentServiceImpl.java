package com.reastApis.services.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reastApis.dto.AddStudentRequestSDto;
import com.reastApis.dto.StudentDto;
import com.reastApis.entity.Student;
import com.reastApis.exception.ResourceNotFoundException;
import com.reastApis.repository.StudentRepository;
import com.reastApis.services.StudentService;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<StudentDto> getAllStudent() 
	{
		List<Student> students = studentRepository.findAll();
		return students.stream()
				.map(student -> modelMapper.map(student, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public StudentDto createStudent(AddStudentRequestSDto addStudentRequestSDto) {
		// Convert DTO to Entity
		Student student = modelMapper.map(addStudentRequestSDto, Student.class);
		
		 // Save Entity to DB
		Student savedStudent  = studentRepository.save(student);
		
		// Convert saved Entity back to DTO
		return modelMapper.map(savedStudent, StudentDto.class);
	}

	@Override
	public StudentDto updateStudent(AddStudentRequestSDto addStudentRequestSDto, Long id) {
		Student student = studentRepository
				.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with Id ❌: "+id));
		
		student.setName(addStudentRequestSDto.getName());
		student.setEmail(addStudentRequestSDto.getEmail());
		
		Student updatedStudent  = studentRepository.save(student);// ✅ save to DB
		return modelMapper.map(updatedStudent, StudentDto.class);
	}

	@Override
	public StudentDto getSingle(Long id) 
	{
		Student student = studentRepository
				.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with Id ❌: "+id));
		
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public void deleteStudent(Long id)
	{
		if (!studentRepository.existsById(id)) 
		{
			throw new IllegalArgumentException("Student does not extists by id: "+id);
		}
		studentRepository.deleteById(id);
	}

	@Override
	public StudentDto updateParticalStudent(Long id, Map<String, Object> updates) 
	{
		Student student = studentRepository
				.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student not found with Id ❌: "+id));
		
		updates.forEach((field, value) -> {
	        switch (field) {
	            case "name":
	                student.setName((String) value);
	                break;
	            case "email":
	                student.setEmail((String) value);
	                break;
	            default:
	                throw new RuntimeException("Invalid field: " + field);
	        }
	    });
		Student updatedStudent  = studentRepository.save(student);// ✅ save to DB
		return modelMapper.map(updatedStudent, StudentDto.class);
	}

}
