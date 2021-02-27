package dkit.oop;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
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
        Student s1 = new Student(11111111,"1999-10-01","sdkljfgh","ljkfha@asdfg.com");
        Student s2 = new Student(s1);
        assert(s1 != s2);
        assert(!s1.equals(s2));
        s2.setCaoNumber(22222222);
        assert(!s1.equals(s2));
    }

    @Test
    public void testCClone(){
        Course c1 = new Course("DK111","8","Something","Someplace");
        Course c2 = new Course(c1);
        assert(c1 != c2);
        assert(!c1.equals(c2));
        c2.setCourseId("DK222");
        assert(!c1.equals(c2));
    }
}
