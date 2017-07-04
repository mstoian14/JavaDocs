package ro.teamnet.zerotohero.oop.graphicshape.exception;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class TryCatchFinally extends Exception{
    public static void main(String args[]) {
        int data = 10;

        try {
            data /= 0;
        } catch (Exception e) {
           // e.printStackTrace();
            System.out.println("Am tratat exceptia.");
        } finally {
            System.out.println("Suntem in blocul finally");
        }
    }
}
