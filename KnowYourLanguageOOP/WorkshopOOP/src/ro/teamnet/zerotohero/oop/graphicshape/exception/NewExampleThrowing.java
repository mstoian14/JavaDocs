package ro.teamnet.zerotohero.oop.graphicshape.exception;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class NewExampleThrowing {

    public static void main(String args[]) {
        try {
            throw new NewException("Exceptia mea.");
        } catch (NewException ne) {
            System.out.println("This is my catch block");
            System.out.println(ne);
        }

    }
}
