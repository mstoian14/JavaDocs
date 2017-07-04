package ro.teamnet.zerotohero.oop.graphicshape.exception;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class TryFinally extends Exception{
    public static void main(String[] args) {
        try{
            System.out.println("Suntem in try.");
        } finally {
            System.out.println("Suntem in finally.");
        }
    }
}
