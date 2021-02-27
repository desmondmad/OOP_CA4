//Desmond Madden D00154375
package dkit.oop;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        CourseChoicesManager mgr = new CourseChoicesManager(studentManager, courseManager);
        //load choices
        mgr.loadChoicesFromFile();

        // display a menu to do things
        mainMenu(mgr,studentManager,courseManager);
        // manual testing of mgr public interface
    }

    public static void mainMenu(CourseChoicesManager mgr,StudentManager s,CourseManager c){
        Scanner keyboard = new Scanner(System.in);

        int caoNumber;
        String dob = null;
        String password = null;
        System.out.println("Please Log in: ");
        System.out.println("Please enter your caoNumber");
        caoNumber = keyboard.nextInt();
        while(validateCNumber(caoNumber) == false){
            System.out.println("Invalid caoNumber");
            System.out.println("Please enter your caoNumber");
            caoNumber = keyboard.nextInt();
        }
        keyboard.nextLine();
        if(caoNumber != 00000000){
            System.out.println("Please enter your date of birth in the format YYYY-MM-DD");
            dob = keyboard.next();
            while(validateDOB(dob) == false){
                System.out.println("Invalid Date of Birth");
                System.out.println("Please enter your date of birth in the format YYYY-MM-DD");
                dob = keyboard.next();
            }
        }
        System.out.println("Please enter your password");
        password = keyboard.next();
        while(validatePassword(password) == false){
            System.out.println("Invalid Password");
            System.out.println("Please enter your password");
            password = keyboard.next();
        }

        if(mgr.login(caoNumber,password)){
            printAdminMenu();
            adminMenu(mgr,s,c);
        } else {
            if (mgr.login(caoNumber,dob,password) )
            {
                printMainMenu();
                studentMenu(caoNumber,mgr);
            }
            else
                System.out.println("Not logged in - try again");
        }
        s.saveStudentsToFile();
        c.saveCoursesToFile();
        mgr.saveChoicesToFile();
        System.out.println("\nGoodbye");
    }

    //Print menu options
    public static void printMainMenu(){
        System.out.println("------------------------------------------------");
        System.out.println("1. DISPLAY A COURSE");
        System.out.println("2. DISPLAY ALL COURSES");
        System.out.println("3. DISPLAY ALL CURRENT CHOICES");
        System.out.println("4. UPDATE CHOICES");
        System.out.println("5. LOGOUT");
        System.out.println("------------------------------------------------");
    }

    //Print admin menu options
    public static void printAdminMenu(){
        System.out.println("------------------------------------------------");
        System.out.println("1. ADD A COURSE");
        System.out.println("2. REMOVE A COURSE");
        System.out.println("3. DISPLAY ALL COURSES");
        System.out.println("4. DISPLAY A COURSE'S DETAILS");
        System.out.println("5. ADD STUDENT");
        System.out.println("6. REMOVE STUDENT");
        System.out.println("7. DISPLAY A STUDENT");
        System.out.println("8. SAVE AND EXIT");
        System.out.println("------------------------------------------------");
    }

    public static void studentMenu(Integer caoNumber,CourseChoicesManager mgr){
        Scanner keyboard = new Scanner(System.in);
        int option;
        String courseID = null;
        System.out.println("Please enter option: ");
        option = keyboard.nextInt();
        keyboard.nextLine();
        while (option != 5) {
            switch(option){
                case 1:
                    System.out.println("Please enter the courseID you want displayed: ");
                    courseID = keyboard.next();
                    System.out.println(mgr.getCourseDetails(courseID));
                    break;
                case 2:
                    System.out.println(mgr.getAllCourses());
                    break;
                case 3:
                    System.out.println(mgr.getStudentChoices(caoNumber));
                    break;
                case 4:
                    ArrayList<String> update = new ArrayList<>();
                    boolean running = true;
                    while(running){
                        System.out.println("Please enter the courseID to add to your choices or 'done' to finish: ");
                        courseID = keyboard.nextLine();
                        if(courseID.matches("") || courseID.matches("done")){
                            running = false;
                        } else {
                            update.add(courseID);
                        }
                    }
                    mgr.updateChoices(caoNumber,update);
                    System.out.println("UPDATED CHOICES");
                    System.out.println(mgr.getStudentChoices(caoNumber));
            }
            printMainMenu();
            System.out.println("Please enter option: ");
            option = keyboard.nextInt();
            keyboard.nextLine();
        }
    }

    public static void adminMenu(CourseChoicesManager mgr,StudentManager s,CourseManager c){
        Scanner keyboard = new Scanner(System.in);
        int option;
        String courseID = null;
        System.out.println("Please enter option: ");
        option = keyboard.nextInt();
        keyboard.nextLine();
        while (option != 8) {
            switch(option){
                case 1:
                    System.out.println("Please enter a courseID: ");
                    courseID = keyboard.next();
                    System.out.println("Please enter the level: ");
                    String level = keyboard.next();
                    keyboard.nextLine();
                    System.out.println("Please enter the title: ");
                    String title = keyboard.nextLine();
                    System.out.println("Please enter the institution: ");
                    String institution = keyboard.next();
                    c.addCourse(new Course(courseID,level,title,institution));
                    break;
                case 2:
                    System.out.println("Please enter a courseID to remove: ");
                    courseID = keyboard.next();
                    c.removeCourse(courseID);
                    break;
                case 3:
                    System.out.println(mgr.getAllCourses());
                    break;
                case 4:
                    System.out.println("Please enter a courseID: ");
                    courseID = keyboard.next();
                    System.out.println(mgr.getCourseDetails(courseID));
                    break;
                case 5:
                    System.out.println("Please enter your caoNumber");
                    int caoNumber = keyboard.nextInt();
                    while(validateCNumber(caoNumber) == false){
                        System.out.println("Invalid caoNumber");
                        System.out.println("Please enter your caoNumber");
                        caoNumber = keyboard.nextInt();
                    }
                    keyboard.nextLine();
                    System.out.println("Please enter your date of birth in the format YYYY-MM-DD");
                    String dob = keyboard.next();
                    while(validateDOB(dob) == false){
                        System.out.println("Invalid Date of Birth");
                        System.out.println("Please enter your date of birth in the format YYYY-MM-DD");
                        dob = keyboard.next();
                    }
                    System.out.println("Please enter your email");
                    String email = keyboard.next();
                    while(validateEmail(email) == false){
                        System.out.println("Invalid Email");
                        System.out.println("Please enter your email");
                        email = keyboard.next();
                    }
                    System.out.println("Please enter your password");
                    String password = keyboard.next();
                    while(validatePassword(password) == false){
                        System.out.println("Invalid Password");
                        System.out.println("Please enter your password");
                        password = keyboard.next();
                    }
                    s.addStudent(new Student(caoNumber,dob,email,password));
                    break;
                case 6:
                    System.out.println("Please enter a student caoNumber to remove: ");
                    caoNumber = keyboard.nextInt();
                    keyboard.nextLine();
                    s.removeStudent(caoNumber);
                    break;
                case 7:
                    System.out.println("Please enter a student caoNumber: ");
                    caoNumber= keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println(mgr.getStudentDetails(caoNumber));
                    break;
            }
            printAdminMenu();
            System.out.println("Please enter option: ");
            option = keyboard.nextInt();
            keyboard.nextLine();
        }
    }

    public static boolean validateCNumber(Integer caoNumber){
        if(caoNumber == 00000000){
            return true;
        } else if (caoNumber < 10000000 || caoNumber > 99999999){
            return false;
        } else {
            return true;
        }
    }

    public static boolean validatePassword(String password){
        String regex = "[a-zA-Z0-9]{10}";
        if (password == null){
            return false;
        } else if (password.matches("admin")){
            return true;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(password);
            return m.matches();
        }
    }

    public static boolean validateEmail(String email){
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null){
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean validateDOB(String dob){
        String regex = "[0-9]{4}[/-][0-9]{2}[/-][0-9]{2}";
        if (dob == null){
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(dob);
        return m.matches();
    }

}
