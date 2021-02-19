package dkit.oop;


import java.util.*;

/**
 * CoursesManager
 * This software component Encapsulates the storage and management of
 * all courses available through the CAO system.
 * Only administrators would typically be allowed to update this data,
 * but other users can get a COPY of all the courses by calling getAllCourses().
 * The Web Client would need this data to display the course codes,
 * course titles and institutions to the student.
 */

public class CourseManager {

    // Store all the Course details.
    // Requires fast access given courseId.
    private Map<String, Course> courseMap = new HashMap<>();

    public CourseManager() {
        courseMap = new HashMap<>();
        // Hardcode some values to get started
        // load from text file "courses.dat" and populate coursesMap
    }

    public Course getCourse(String courseId) {
        Course c = courseMap.get(courseId);
        return c;
    }

    public List<Course> getAllCourses(){
        ArrayList<Course> courseListCopy = new ArrayList<>();
        Set<String> keySet = courseMap.keySet();
        for(String key : keySet ){
            Course c = courseMap.get(key);
            courseListCopy.add(new Course(c));
        }
        return courseListCopy;
    }

    public void addCourse(Course course){
        if (course == null)
            throw new IllegalArgumentException();
        this.courseMap.put(course.getCourseId(), new Course(course));
    }

    public void removeCourse(String courseId){
        courseMap.remove(courseId);
    }

    // editCourse(courseId);       // not required for this iteration

}







