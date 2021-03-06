//Desmond Madden D00154375
package dkit.oop;

public class Student {
    private int caoNumber;  // In the CAO system, cao number is unique identifier for student
    private String dateOfBirth; // yyyy-mm-dd
    private String password;    // min 8 characters
    private String email;



    // Constructor
    public Student(int caoNumber, String dateOfBirth, String password, String email) {
        this.caoNumber = caoNumber;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.email = email;
    }

    // Copy Constructor
    // Copies the contents of a Student object argument into
    // a new Student object, and returns that new object (a clone)
    // (add here)

    public Student(Student copy){
//        this.caoNumber = copy.caoNumber;
//        this.dateOfBirth = copy.dateOfBirth;
//        this.password = copy.password;
//        this.email = copy.email;
        this(copy.getCaoNumber(),copy.getDayOfBirth(),copy.getPassword(),copy.getEmail());
    }

    //public boolean verifyLoginCredentials( yyy-mm-dd, password);

    public int getCaoNumber() {
        return caoNumber;
    }

    public void setCaoNumber(int caoNumber) {
        this.caoNumber = caoNumber;
    }

    public String getDayOfBirth() {
        return dateOfBirth;
    }

    public void setDayOfBirth(String dayOfBirth) {
        this.dateOfBirth = dayOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Student{" +
                "caoNumber=" + caoNumber +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
