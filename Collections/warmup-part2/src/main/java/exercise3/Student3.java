package exercise3;

/**
 * Created by Mihaela.Stoian on 7/7/2017.
 */
public class Student3 extends Student{
    public Student3(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public int hashCode() {
        int result;
        result = getFirstName().hashCode() + getLastName().hashCode();
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

        Student3 st3 = (Student3)obj;

        if(this.getFirstName().equals(st3.getFirstName()) == false) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
