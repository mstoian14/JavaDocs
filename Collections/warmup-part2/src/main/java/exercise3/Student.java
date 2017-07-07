package exercise3;

/**
 * Created by Mihaela.Stoian on 7/7/2017.
 */
public class Student {
    private String lastName;
    private String firstName;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
