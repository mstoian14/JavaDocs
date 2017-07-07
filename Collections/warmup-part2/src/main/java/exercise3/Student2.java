package exercise3;

/**
 * Created by Mihaela.Stoian on 7/7/2017.
 */
public class Student2 extends Student {
    public Student2(String firstName, String lastName) {
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

        Student2 aes = (Student2) obj;
        if(aes.getFirstName().equals(this.getFirstName()) == false) {
            return false;
        }

        if(!(this.getLastName().equals(aes.getLastName()))) {
            return false;
        }

        return true;
    }
    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName();
    }
}
