package ro.teamnet.zerotohero.oop.graphicshape.exception;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class MultiCatch {
    public static void main(String[] args) {
        try {
            int date = 10/0;
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException");
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
