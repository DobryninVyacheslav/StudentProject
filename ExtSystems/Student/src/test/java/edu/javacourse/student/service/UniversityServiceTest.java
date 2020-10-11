package edu.javacourse.student.service;

import edu.javacourse.student.domain.Faculty;
import edu.javacourse.student.domain.University;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:springContext.xml"})
public class UniversityServiceTest {

    @Autowired
    private UniversityService service;

    @Test
    public void findUniversities() {
        List<University> universities = service.findUniversities();
        System.out.println("OK");
        universities.forEach(u -> System.out.println(
                u.getUniversityId() + ":" + u.getUniversityName() + ":" + u.getFaculties().size()));
    }

    @Test
    public void findFullUniversities() {
        List<University> universities = service.findFullUniversities();
        System.out.println("OK");
        universities.forEach(u -> System.out.println(
                u.getUniversityId() + ":" + u.getUniversityName() + ":" + u.getFaculties().size()));
    }

    @Test
    public void findUniversity() {
        University university = service.findUniversity(1L);
        System.out.println(university.getUniversityName() + ":" + university.getFaculties().size());
    }

    @Test
    public void findFaculties() {
        List<Faculty> faculties = service.findFaculties();
        System.out.println("OK");
        faculties.forEach(f -> System.out.println(
                f.getFacultyId() + ":" + f.getUniversity().getUniversityName()));
    }

    @Test
    public void findFaculty() {
        Faculty faculty = service.findFaculty(1L);
        System.out.println(faculty.getUniversity().getUniversityName());
    }
}