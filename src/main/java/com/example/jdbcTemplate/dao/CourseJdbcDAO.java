package com.example.jdbcTemplate.dao;

import com.example.jdbcTemplate.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public class CourseJdbcDAO implements DAO<Course>{

    private static final Logger logger = LoggerFactory.getLogger(CourseJdbcDAO.class);
    private JdbcTemplate jdbcTemplate;

    public CourseJdbcDAO(JdbcTemplate jdbcTemplate){

        this.jdbcTemplate = jdbcTemplate;
    }
    RowMapper<Course> rowMapper = (result,rowNumber) -> {
        Course course = new Course();
        course.setCourseId(result.getInt("courseId"));
        course.setTittle(result.getString("tittle"));
        course.setDescription(result.getString("description"));
        course.setLink(result.getString("link"));
        return course;

    };
    @Override
    public List<Course> list() {
        String sql = "SELECT courseId, tittle, description, link FROM Course";
        //Map a colum from the result set into an instance variable in our Course Object
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void create(Course course) {
        String sql = "INSERT INTO Course(tittle,description,link) VALUES(?,?,?)";
        int insert = jdbcTemplate.update(sql,course.getTittle(), course.getDescription(), course.getLink());
        if(insert == 1){
            logger.info("New course created" + course.getTittle());
        }
    }

    //We use optional because this prevented us to present Exceptions
    //in case the user input some not valid ID
    //DataAccessException for when we couldn't find that particular course
    @Override
    public Optional<Course> get(int id) {
        String sql = "SELECT courseId, tittle, description, link FROM Course WHERE courseId = ? ";
        Course course = null;
        //queryForObject for just one query
        try{
            course = jdbcTemplate.queryForObject(sql, new Object[]{id},rowMapper);
        }catch (DataAccessException ex){
            logger.info("Course not found: " + id);
        }
        return Optional.ofNullable(course);
    }

    @Override
    public void update(Course course, int id) {
        String sql = "UPDATE Course SET tittle=?, description=?, link=? WHERE courseId = ?";
        int update = jdbcTemplate.update(sql, course.getTittle(), course.getDescription(), course.getLink());
        if(update == 1){
            logger.info("Course updated: " + course.getTittle());
        }
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Course WHERE courseID = ?",id);
    }
}
