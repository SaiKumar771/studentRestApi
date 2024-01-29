package com.learn.springboot.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.learn.springboot.entity.Student;
import com.learn.springboot.repository.IStudentRepository;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private IStudentRepository studentRepo;

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		Pageable students = PageRequest.of(0, 3);
		Page<Student> studentsInPage = studentRepo.findAll(students);
		List<Student> StudentList = studentsInPage.stream().collect(Collectors.toList());
		return StudentList;

	}

	public List<Student> getStudentsCustomisedPage(int page, int noOfrecordsOnPage) {
		// TODO Auto-generated method stub
		Pageable students = PageRequest.of(page, noOfrecordsOnPage);
		Page<Student> studentsInPage = studentRepo.findAll(students);
		List<Student> StudentList = studentsInPage.stream().collect(Collectors.toList());
		return StudentList;

	}

	public List<Student> getStudentsOnLatestOrder() {
		return studentRepo.findAll(Sort.by(Sort.Direction.DESC, "studentId"));
	}

	public List<Student> getStudentsSortById(Direction direction) {
		return studentRepo.findAll(Sort.by(direction, "studentId"));
	}

	public List<Student> getStudentsSortByName(Direction direction) {
		return studentRepo.findAll(Sort.by(direction, "name"));
	}

	public List<Student> getStudentsWithEmptyEmail() {
		Student studentsWithNoIdAndName = new Student();
		studentsWithNoIdAndName.setEmail("");
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("studentId", "name");
		Example<Student> example = Example.of(studentsWithNoIdAndName, exampleMatcher);
		return studentRepo.findAll(example);
	}

	public List<Student> getStudentsWithMatchingEmailAndSortedByName(String email) {
		Student studentsWithNoIdAndName = new Student();
		studentsWithNoIdAndName.setEmail(email);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("studentId", "name");
		Example<Student> example = Example.of(studentsWithNoIdAndName, exampleMatcher);
		Pageable students = PageRequest.of(0, 2, Sort.by("name"));
		return studentRepo.findAll(example, students).getContent();
	}

	public List<Student> getStudentsPagedAndSortedByName() {
		Pageable StudentPages = PageRequest.of(0, 2, Sort.by("name"));
		return studentRepo.findAll(StudentPages).getContent();
	}

	@Override
	public Student saveStudent(Student student) {

	    Student studentSaved=studentRepo.save(student);
	    studentRepo.flush();
	    return studentSaved;
	}

	public List<Student> getStudentsCustomPagedAndSortedWithDefaultOrderByNameAndWithTheseEmails(String email,
			int pages, int noOfRecords) {
		Student studentsWithNoIdAndName = new Student();
		studentsWithNoIdAndName.setEmail(email);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("studentId", "name");
		Example<Student> example = Example.of(studentsWithNoIdAndName, exampleMatcher);
		Pageable students = PageRequest.of(pages, noOfRecords, Sort.by("name"));
		return studentRepo.findAll(example, students).getContent();
	}

	public List<Student> getStudentSortedByNameWithTheseEmails(String email) {
		Student studentsWithNoIdAndName = new Student();
		studentsWithNoIdAndName.setEmail(email);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("studentId", "name");
		Example<Student> example = Example.of(studentsWithNoIdAndName, exampleMatcher);
		return studentRepo.findAll(example, Sort.by("name"));
	}

	public List<Student> getStudentsByIds(List<Integer> ids) {
		return studentRepo.findAllById(ids);
	}

	public Student getStudentWithTheseEmail(String email) {
		Student studentsWithNoIdAndName = new Student();
		studentsWithNoIdAndName.setEmail(email);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("studentId", "name");
		Example<Student> example = Example.of(studentsWithNoIdAndName, exampleMatcher);
		return studentRepo.findOne(example).get();

	}

	public long getStudentsCount() {
		return studentRepo.count();
	}

	public long getStudentsCountWithTheseEmail(String email) {
		Student studentsWithEmail = new Student();
		studentsWithEmail.setEmail(email);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("studentId", "name");
		Example<Student> example = Example.of(studentsWithEmail, exampleMatcher);
		return studentRepo.count(example);
	}

	public boolean getStudentsExists(Integer id) {
		return studentRepo.existsById(id);
	}

	public boolean getStudentsExistsWithMatchingEmail(String email) {
		Student studentsWithEmail = new Student();
		studentsWithEmail.setEmail(email);
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("email", ExampleMatcher.GenericPropertyMatchers.exact())
				.withIgnorePaths("studentId", "name");
		Example<Student> example = Example.of(studentsWithEmail, exampleMatcher);
		return studentRepo.exists(example);
	}
	
	
}
