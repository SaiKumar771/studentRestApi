package com.learn.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.springboot.entity.Student;
import com.learn.springboot.service.IStudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
	@Autowired
	private IStudentService studentService;

	@GetMapping("/api/student")
	public List<Student> retrieveAllStudents() {
		log.info("got all student list");
		return studentService.getAllStudents();
	}

	@PostMapping("/api/stud")
	public Student saveStudent(@RequestBody Student student) {
		log.info("saved one student data");
		return studentService.saveStudent(student);
	}

	@GetMapping("custom/student/{pageNo}/{noOfRecords}")
	public List<Student> getStudentsOnCustomizedPage(@PathVariable int pageNo, @PathVariable int noOfRecords) {
		log.info("got all student on page customizations");
		return studentService.getStudentsCustomisedPage(pageNo, noOfRecords);
	}

	@GetMapping("/sort/students")
	public List<Student> getStudentsOnOrderByLatest() {
		log.info("got all students by latest order");
		return studentService.getStudentsOnLatestOrder();
	}

	@GetMapping("/sorted/students/{direction}")
	public List<Student> getStudentsOnSortedById(@PathVariable Direction direction) {
		log.info("got all students by soreted order");
		return studentService.getStudentsSortById(direction);
	}

	@GetMapping("/sorted/student/name/{direction}")
	public List<Student> getStudentsOnSortedByName(@PathVariable Direction direction) {
		log.info("got all students by soreted order");
		return studentService.getStudentsSortByName(direction);
	}

	@GetMapping("/students/email")
	public List<Student> getStudentsWithNoIdAndName() {
		log.info("no property student id and name");
		return studentService.getStudentsWithEmptyEmail();
	}

	@GetMapping("/students/email/{email}")
	public List<Student> getStudentsWithMatchingEmail(@PathVariable String email) {
		log.info("no property student id and name");
		return studentService.getStudentsWithMatchingEmailAndSortedByName(email);
	}

	@GetMapping("/student/sorted/name")
	public List<Student> getStudentsPagedAndSortedByName() {
		log.info("got student paged and sorted by name");
		return studentService.getStudentsPagedAndSortedByName();
	}

	@GetMapping("/students/custom/{email}/{pages}/{noOfRecords}")
	public List<Student> getStudentsCustomPagedAndSortedWithDefaultOrderByNameAndWithTheseEmails(
			@PathVariable String email, @PathVariable int pages, @PathVariable int noOfRecords) {

		log.info("got students with custom paged and sorted by name");
		return studentService.getStudentsCustomPagedAndSortedWithDefaultOrderByNameAndWithTheseEmails(email, pages, noOfRecords);
	}
	
	@GetMapping("/students/sort/name/{email}")
	public List<Student> getStudentSortedByNameWithTheseEmails(@PathVariable String email)
	{
		log.info("got students with sorted by name with matching email");
		return studentService.getStudentSortedByNameWithTheseEmails(email);
	}
	
	@GetMapping("/students/ids")
	public List<Student> getStudentsByIds(@RequestParam List<Integer> ids)
	{
		log.info("got students by ids");
		return studentService.getStudentsByIds(ids);
	}
	
	@GetMapping("/email/{email}")
	public Student getStudentWithTheseEmail(@PathVariable String email)
	{
		log.info("got student with matching email");
		return studentService.getStudentWithTheseEmail(email);
	}
	
	@GetMapping("/count")
	public long getStudentsCount()
	{
		log.info("got total students count");
		return studentService.getStudentsCount();
	}
	
	@GetMapping("/count/{email}")
	public long getStudentsCountWithTheseEmail(@PathVariable String email)
	{
		log.info("got count of student with: "+email);
	    return studentService.getStudentsCountWithTheseEmail(email);	
	}
	
	@GetMapping("/exists/id/{id}")
	public boolean getStudentsExists(@PathVariable Integer id)
	{
		log.info("Checking id:"+id+" exists or not");
		return studentService.getStudentsExists(id);
	}
	
	@GetMapping("/exists/email/{email}")
	public boolean getStudentsExistsWithMatchingEmail(@PathVariable String email)
	{
		log.info("Checking emial:"+email+" exists or not");
		return studentService.getStudentsExistsWithMatchingEmail(email);
	}
}

