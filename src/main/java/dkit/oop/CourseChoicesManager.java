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

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class CourseChoicesManager {

    // reference to constructor injected studentManager
    private StudentManager studentManager;

    // reference to constructor injected courseManager
    private CourseManager courseManager;

    // Store all the Course details -  fast access
    private Map<String, Course> courseMap;

    private Map<Integer,List<String>> sChoiceMaps;
    // caoNumber, course selection list - for fast access

    File inputFile = new File("choices.txt");
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

    public void loadChoicesFromFile() {

        System.out.println("Reading choices DB from file...");

        try {
            Scanner sc = new Scanner(inputFile);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] data = line.split(",");
                int caoNumber = Integer.parseInt(data[0]);
                ArrayList<String> listChoice = new ArrayList<>();
                for(int i = 1; i< data.length;i++){
                    listChoice.add(data[i]);
                }
                sChoiceMaps.put(caoNumber,listChoice);
            }
            System.out.println("All choices loaded");
            System.out.println();
            sc.close();

        } catch ( FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught." + exception);
        } catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchexception caught." + exception);
        }
    }

    public void saveChoicesToFile(){
        try (BufferedWriter choicesFile = new BufferedWriter(new FileWriter("choices.txt")))
        {
            Set<Integer> keySet = sChoiceMaps.keySet();
            for(Integer key : keySet) {
                choicesFile.write(String.valueOf(getStudentDetails(key).getCaoNumber()));
                for(int i = 0; i < getStudentChoices(key).size();i++)
                {
                    choicesFile.write("," + getStudentChoices(key).get(i));
                }
                choicesFile.write("\n");
            }
        }
        catch(IOException ioe){
            System.out.println(Color.pink + "COULD NOT SAVE STUDENTS" + Color.pink);
        }
    }
//
    public boolean login(Integer caoNumber,String dob,String password) {
        boolean valid = false;
        if(studentManager.getStudent(caoNumber) != null){
            if(studentManager.getStudent(caoNumber).getDayOfBirth().equals(dob)){
                if(studentManager.getStudent(caoNumber).getPassword().equals(password)){
                    valid = true;
                } else {
                    System.out.println("Incorrect password");
                }
            } else {
                System.out.println("Incorrect date of birth");
            }
        }else{
            System.out.println("No student with that caoNumber");
        }
        return valid;
    }

    public boolean login(Integer adminNo,String password) {
        boolean valid = false;
        if(adminNo == 00000000 && password.matches("admin")){
            valid = true;
        }
        return valid;
    }

}
