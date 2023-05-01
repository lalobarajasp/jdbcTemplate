package com.example.jdbcTemplate;

import com.example.jdbcTemplate.dao.DAO;
import com.example.jdbcTemplate.model.Course;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class JdbcTemplateApplication {

	private static DAO<Course> dao;

	public JdbcTemplateApplication(DAO<Course> dao) {
		this.dao =dao;
	}
	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateApplication.class, args);
		System.out.println("\n Create Course----------------------------- \n");
		Course mathematics = new Course("Mathematics","Basics","www.math.com");
		dao.create(mathematics);

		System.out.println("\n One Course----------------------------- \n");
		Optional<Course> firstCourse = dao.get(1);
		System.out.println(firstCourse.get());

		System.out.println("\n Update Course----------------------------- \n");
		mathematics.setDescription("Advanced");
		dao.update(mathematics,5);

	//	System.out.println("\n Delete Course----------------------------- \n");
	//	dao.delete(4);

		System.out.println("\n All Courses----------------------------- \n");
		List<Course> courses = dao.list();
		courses.forEach(System.out::println);


	}


}
