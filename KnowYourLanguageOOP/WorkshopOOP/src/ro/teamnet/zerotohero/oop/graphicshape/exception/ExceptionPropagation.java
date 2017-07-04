package ro.teamnet.zerotohero.oop.graphicshape.exception;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class ExceptionPropagation {
    void e2() {
        int div = 10/0;
        System.out.println(div);
    }
    void e1() {
        try {
            e2();
        } catch (Exception e) {
            System.out.println("Exceptie propagata.");
        }
    }

    public static void main(String args[]) {
        ExceptionPropagation ep = new ExceptionPropagation();
        ep.e1();
        System.out.println("Am trecut de exceptie.");
    }
}
