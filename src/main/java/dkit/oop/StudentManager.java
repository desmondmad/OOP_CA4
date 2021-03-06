//Desmond Madden D00154375
package dkit.oop;
// StudentManager encapsulates the storage and ability
// to manipulate student objects


import java.awt.*;
import java.io.*;
import java.util.*;

public class StudentManager {

    // Store all students in data structure

    private Map<Integer, Student> studentMap = new HashMap<>();
    File inputFile = new File("students.txt");
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
            System.out.println(

            );
            sc.close();

        } catch ( FileNotFoundException exception)
        {
            System.out.println("FileNotFoundException caught." + exception);
        } catch (InputMismatchException exception)
        {
            System.out.println("InputMismatchexception caught." + exception);
        }
    }

    public void saveStudentsToFile(){
        try (BufferedWriter studentsFile = new BufferedWriter(new FileWriter("students.txt")))
        {
            Set<Integer> keySet = studentMap.keySet();
            for(Integer key : keySet) {
                studentsFile.write(studentMap.get(key).getCaoNumber()+","+studentMap.get(key).getDayOfBirth()+","+studentMap.get(key).getPassword()+","+studentMap.get(key).getEmail());
                studentsFile.write("\n");
            }
        }
        catch(IOException ioe){
            System.out.println(Color.pink + "COULD NOT SAVE STUDENTS" + Color.pink);
        }
    }

}
