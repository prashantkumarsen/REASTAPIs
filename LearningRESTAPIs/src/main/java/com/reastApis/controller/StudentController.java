package com.reastApis.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.reastApis.dto.AddStudentRequestSDto;
import com.reastApis.dto.StudentDto;
import com.reastApis.services.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController 
{
	@Autowired
	private StudentService studentService;
	
	@GetMapping
	public ResponseEntity<List<StudentDto>> getStudent()
	{
		List<StudentDto> student = studentService.getAllStudent();
		return ResponseEntity.ok(student);
	}	
	@PostMapping("/create")
	public ResponseEntity<StudentDto>createStudent(@Valid @RequestBody AddStudentRequestSDto addStudentRequestSDto)
	{
		StudentDto student = studentService.createStudent(addStudentRequestSDto);
		return new ResponseEntity<StudentDto>(student,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<StudentDto>updateStudent(@Valid @RequestBody AddStudentRequestSDto addStudentRequestSDto,@PathVariable Long id)
	{
		StudentDto student = studentService.updateStudent(addStudentRequestSDto, id);
		return new ResponseEntity<StudentDto>(student,HttpStatus.CREATED);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<StudentDto> getSingleStd(@Valid @PathVariable Long id)
	{
		StudentDto studentDto = studentService.getSingle(id);
		return ResponseEntity.ok(studentDto);
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity <Void> deleteStudent(@PathVariable Long id)
	{
	  	studentService.deleteStudent(id);
	  	return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<StudentDto>updatePaticalStudent(@Valid @PathVariable Long id, @RequestBody Map<String, Object> updates)
	{
	StudentDto studentDto = studentService.updateParticalStudent(id, updates);
		return new ResponseEntity<StudentDto>(studentDto,HttpStatus.CREATED);
	}
}
