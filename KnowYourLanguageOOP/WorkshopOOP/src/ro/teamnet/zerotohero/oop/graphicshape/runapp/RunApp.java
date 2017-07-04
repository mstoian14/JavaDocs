package ro.teamnet.zerotohero.oop.graphicshape.runapp;

import ro.teamnet.zerotohero.oop.graphicshape.*;
import ro.teamnet.zerotohero.oop.graphicshape.canvas.Canvas;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class RunApp {
    public static void main(String args[]) {
        Circles circles = new Circles();
        System.out.println("The default circle area is " + circles.getAreaPub());
        circles.getAreaDef();
        Canvas canvas = new Canvas();
        Shape shape = new Circle(10);
        System.out.println(shape.area());
        ShapeBehaviour shapeBehaviour = new Square(10);
        System.out.println("Square area obj " + shapeBehaviour.area());
        Object p1 = new Point(10, 20);
        Object p2 = new Point(50, 100);
        Object p3 = new Point(10, 20);
        System.out.println("p1 equals p2 is " + p1.equals(p2));
        System.out.println("p1 equals p3 is " + p1.equals(p3));
        ImmutableClass ic = new ImmutableClass("ceva");
        System.out.println(ic.getString());
    }
}
