package exercise3;

/**
 * Created by Mihaela.Stoian on 7/7/2017.
 */
public class Student4 extends Student {
    public Student4(String firstName, String lastName) {
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

        if(this == obj) {
            return true;
        }

        if(this.getClass() != obj.getClass()) {
            return false;
        }

        Student4 st4 = (Student4) obj;

        if(this.getLastName().equals(st4.getLastName()) == false) {
            return false;
        }

        if(this.getFirstName().equals(st4.getFirstName()) == false) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
