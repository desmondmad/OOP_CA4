package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects


import java.util.HashMap;
import java.util.Map;

public class StudentManager {

    // Store all students in data structure

    private Map<Integer, Student> studentMap = new HashMap<>();
    public StudentManager() {
        // Hardcode some values to get started
        // later, load from text file "students.dat" and populate studentsMap
        studentMap = new HashMap<>();
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
        studentMap.remove(caoNumber);
    }
//    isRegistered( caoNumber)
//        students.isValid()
}
