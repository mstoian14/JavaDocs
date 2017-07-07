package exercise3;

/**
 * Created by Mihaela.Stoian on 7/7/2017.
 */
public class Student1 extends Student {

    public Student1(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int hashCode() {
        int result = getFirstName().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(this.getClass() != obj.getClass()) {
            return false;
        }

        Student st = (Student) obj;
        if(this.getFirstName().equals(st.getFirstName()) == false) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
