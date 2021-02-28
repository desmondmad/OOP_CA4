package dkit.oop;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    Student s1 = new Student(11111111,"1999-10-01","sdkljfgh","ljkfha@asdfg.com");
    Course c1 = new Course("DK111","8","Something","Someplace");
    StudentManager s = new StudentManager();
    CourseManager c = new CourseManager();
    CourseChoicesManager mgr = new CourseChoicesManager(s,c);
    Map<Integer,List<String>> sChoiceMaps;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testSClone(){
        Student s2 = new Student(s1);
        assert(s1 != s2);
        assert(!s1.equals(s2));
        s2.setCaoNumber(22222222);
        assert(!s1.equals(s2));
    }

    @Test
    public void testCClone(){
        Course c2 = new Course(c1);
        assert(c1 != c2);
        assert(!c1.equals(c2));
        c2.setCourseId("DK222");
        assert(!c1.equals(c2));
    }
    @Test
    public void testStudentDetails(){
        s.addStudent(s1);
        Student result = mgr.getStudentDetails(s1.getCaoNumber());
        Assert.assertEquals(s.getStudent(s1.getCaoNumber()),result);
    }

    @Test
    public void testCourseDetails(){
        c.addCourse(c1);
        Course result = mgr.getCourseDetails(c1.getCourseId());
        Assert.assertEquals(c.getCourse(c1.getCourseId()),result);
    }

}
