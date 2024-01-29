package com.learn.springboot.service;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.learn.springboot.entity.Student;

public interface IStudentService {

	public List<Student> getAllStudents();

	public Student saveStudent(Student student);

	public List<Student> getStudentsCustomisedPage(int page, int noOfrecordsOnPage);

	public List<Student> getStudentsOnLatestOrder();

	public List<Student> getStudentsSortById(Direction direction);

	public List<Student> getStudentsSortByName(Direction direction);

	public List<Student> getStudentsWithEmptyEmail();

	public List<Student> getStudentsWithMatchingEmailAndSortedByName(String email);

	public List<Student> getStudentsPagedAndSortedByName();

	public List<Student> getStudentsCustomPagedAndSortedWithDefaultOrderByNameAndWithTheseEmails(String email,
			int pages, int noOfRecords);

	public List<Student> getStudentSortedByNameWithTheseEmails(String email);

	public List<Student> getStudentsByIds(List<Integer> ids);

	public Student getStudentWithTheseEmail(String email);

	public long getStudentsCount();

	public long getStudentsCountWithTheseEmail(String email);

	public boolean getStudentsExists(Integer id);

	public boolean getStudentsExistsWithMatchingEmail(String email);
}
