package com.example.jdbcTemplate.model;

public class Course {
    //http://localhost:8080/h2-console/
    //jdbc:h2:mem:course_platform

    private int courseId;
    private String tittle;
    private String description;
    private String link;

    public Course() {
    }

    public Course(String tittle, String description, String link) {
        this.tittle = tittle;
        this.description = description;
        this.link = link;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", tittle='" + tittle + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

}
