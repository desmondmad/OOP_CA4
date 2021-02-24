//Desmond Madden D00154375
package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StudentManager {

    // Store all students in data structure

    private Map<Integer, Student> studentMap = new HashMap<>();
    File inputFile = new File("students.txt");
    public StudentManager() {
        // Hardcode some values to get started
        // later, load from text file "students.dat" and populate studentsMap
        studentMap = new HashMap<>();
//        studentMap.put(22224444,new Student(22224444,"1995-04-25","5720192Dm1","d00154375@dkit.student.ie"));
//        studentMap.put(90909090,new Student(90909090,"1996-06-26","e3uyszr1","d00133445@dkit.student.ie"));
//        studentMap.put(12345678,new Student(12345678,"2000-10-10","password","d00173746@dkit.student.ie"));
    }

    public void addStudent(Student student){
        if (student == null)
            throw new IllegalArgumentException();
        this.studentMap.put(student.getCaoNumber(), new Student(student));
    }

    public Student getStudent(Integer caoNumber) {
        Student s = studentMap.get(caoNumber);
        return s;
    }

    public void removeStudent(Integer caoNumber){
        if(getStudent(caoNumber) == null){
            System.out.println("No Student with that caoNumber");
        } else {
            this.studentMap.remove(caoNumber);
        }
    }
    public boolean isRegistered(Integer caoNumber){
        return studentValid(caoNumber);
    }

    public boolean studentValid(Integer caoNumber){
        if(getStudent(caoNumber) == null){
            return false;
        } else {
            return true;
        }
    }

    public void loadStudentsFromFile() {

        System.out.println("Reading student DB from file...");

        try {
            Scanner sc = new Scanner(inputFile);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] data = line.split(",");
                int caoNumber = Integer.parseInt(data[0]);
                String dateOfBirth = data[1];
                String password = data[2];
                String email = data[3];
                studentMap.put(caoNumber,new Student(caoNumber,dateOfBirth,password,email));
            }
            System.out.println("All students loaded");
            sc.close();

        } catch ( FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught." + exception);
        } catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchexception caught." + exception);
        }
    }

}
