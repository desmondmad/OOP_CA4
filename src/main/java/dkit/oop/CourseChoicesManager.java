//Desmond Madden D00154375
package dkit.oop;

// Stores all student CAO choices.
// Allows student to make course choices, save them and update them later.
//
// emphasis on speed of access when multiple users are accessing this at same time
//
// This component would interact with a Network component that would, in turn, send
// data over the internet to a web client.
//
// Clone all received and returned objects - encapsulation

import java.util.*;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access
    private Map<String, Course> courseMap;
    private Map<Integer,List<String>> sChoiceMaps;
    // caoNumber, course selection list - for fast access


    // CourseChoicesManager DEPENDS on both the StudentManager and CourseManager to access
    // student details and course details.  So, we receive a reference to each via
    // the constructor.
    // This is called "Dependency Injection", meaning that we
    // inject (or pass in) objects that this class requires to do its job.
    //
    CourseChoicesManager(StudentManager studentManager, CourseManager courseManager) {
        this.studentManager = studentManager;
        this.courseManager = courseManager;
        courseMap = new HashMap<>();
        sChoiceMaps = new HashMap<>();
        ArrayList<String> listChoice = new ArrayList<>();
        listChoice.add("DK821");
        listChoice.add("DK740");
        sChoiceMaps.put(22224444,listChoice);
    }

    public Student getStudentDetails(Integer caoNumber) {
        Student s = studentManager.getStudent(caoNumber);
        return s;
    }
//
    public Course getCourseDetails(String courseId) {
        Course c = courseManager.getCourse(courseId);
        return c;
    }
//
    public List<String> getStudentChoices(Integer caoNumber) {
        return sChoiceMaps.get(caoNumber);
    }
//
    public void updateChoices(int caoNumber, List<String> choiceList) {
        sChoiceMaps.put(caoNumber,choiceList);
    }
//
    public List<Course> getAllCourses() {
        return courseManager.getAllCourses();
    }
//
//    boolean login() {
//    }


}
