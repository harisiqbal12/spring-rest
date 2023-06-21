package com.rest.rest.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rest.rest.entity.Student;
import com.rest.rest.entity.StudentErrorResponse;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class StudentRestController {

  private List<Student> students = new ArrayList<>();

  @PostConstruct
  public void loadData() {

    students.add(new Student("Haris", "Iqbal"));
    students.add(new Student("Kumail", "Shah"));
    students.add(new Student("Taimoor", "Malik"));
  }

  @GetMapping("/students")
  public ResponseEntity<List<Student>> getStudents(HttpServletRequest request) {

    String method = request.getMethod();

    System.out.println("Request method: " + method);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/json");

    return ResponseEntity.status(HttpStatus.OK).headers(headers).body(students);
  }

  @GetMapping("/students/{studentId}")
  public Student getStudent(@PathVariable int studentId) {

    System.out.println("Id: " + studentId);

    if ((studentId >= students.size()) || (studentId < 0)) {
      throw new StudentNotFoundException("Student id not found - " + studentId);
    }

    if (studentId == 1) {
      throw new StudentDisableException("Student of " + studentId + " is disabled.");
    }

    return students.get(studentId);
  }

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
    System.out.println("63");
    StudentErrorResponse error = new StudentErrorResponse();

    error.setStatus(HttpStatus.NOT_FOUND.value());
    error.setMessage(exc.getMessage());
    error.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleException(StudentDisableException exc) {

    System.out.println("80");
    StudentErrorResponse error = new StudentErrorResponse(HttpStatus.BAD_GATEWAY.value(), exc.getMessage(),
        System.currentTimeMillis());

    return new ResponseEntity<StudentErrorResponse>(error, HttpStatus.BAD_GATEWAY);
  }
}
