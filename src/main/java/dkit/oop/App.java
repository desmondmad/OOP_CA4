//Desmond Madden D00154375
package dkit.oop;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Notes:
 *  Synchronisation of multiple reads and writes to file is not considered here.
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "CAO Online - CA4" );
        new App() .start();
    }

    private void start() {

        // load students
        StudentManager studentManager = new StudentManager();
        studentManager.loadStudentsFromFile();

        // load courses
        CourseManager courseManager= new CourseManager();
        courseManager.loadCoursesFromFile();
        // load manager to provide functionality to allow a student
        // to login and add/update their course selections
        // This CourseChoicesManager component depends on the
        // StudentManager and the CourseManager,
        // so we 'inject' or pass-in these objects.
        //
        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);

        // display a menu to do things
        // manual testing of mgr public interface
        System.out.println(courseManager.getCourse("DN271"));
        System.out.println(mgr.getAllCourses());
        List<String> choice = mgr.getStudentChoices(22224444);
        System.out.println(choice);
        System.out.println(mgr.getStudentDetails(22224444));
//        if ( mgr.login(22224444, "xxxx","bbbb") )
//        {
//            Student student = mgr.getStudentDetails(22224444);
//
//            System.out.println("Student: " + student);
//        }
//        else
//            System.out.println("Not logged in - try again");


        //mgr.saveToFile();

    }
}
