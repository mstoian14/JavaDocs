package ro.teamnet.zerotohero.oop.graphicshape.exception;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class MultipleException {
    public static void main(String[] args) {
        try {
            int data = 10/0;
        } catch (ArithmeticException | NullPointerException e) {
            System.out.println("Am prins exceptia.");
        }
    }
}
