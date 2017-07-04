package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class Square extends Shape {
    private int side;
    public Square(int side) {
        this.side = side;
    }

    @Override
    public double area() {
        return Math.pow(side,2);
    }
}
