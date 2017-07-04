package ro.teamnet.zerotohero.oop.graphicshape.exception;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class NestedExceptions {
    public static void met() throws NewException {
        try {
            int div = 10/0;
        } catch (Exception e) {
            throw new NewException("Alta exceptie");
        }
    }
    public static void main(String args[]) {
        try {
            met();
        } catch (Exception e) {
            System.out.println("Exceptie: " + e);
        }
    }
}
