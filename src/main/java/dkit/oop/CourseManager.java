//Desmond Madden D00154375
package dkit.oop;


import java.io.File;
import java.io.FileNotFoundException;
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
    File inputFile = new File("courses.txt");
    public CourseManager() {
        courseMap = new HashMap<>();
        // Hardcode some values to get started
//        courseMap.put("DK821",new Course("DK821","8","BSc in Computing in Software Development","Dundalk Institute of Technology"));
//        courseMap.put("DK740",new Course("DK740","7","BSc in Art","Dundalk Institute of Technology"));
//        courseMap.put("DT250",new Course("TUD250","8","BSc in History","Technology Institute of Dublin"));
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
        if(getCourse(courseId) == null){
            System.out.println("No Course with that courseId");
        } else {
            this.courseMap.remove(courseId);
        }
    }

    public void loadCoursesFromFile() {
        System.out.println("Reading course DB from file...");

        try {
            Scanner sc = new Scanner(inputFile);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] data = line.split(",");
                String courseId = data[0];
                String level = data[1];
                String title = data[2];
                String institution = data[3];
                courseMap.put(courseId,new Course(courseId,level,title,institution));
            }
            System.out.println("All courses loaded");
            sc.close();

        } catch ( FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught." + exception);
        } catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchexception caught." + exception);
        }
    }
    // editCourse(courseId);       // not required for this iteration

}







