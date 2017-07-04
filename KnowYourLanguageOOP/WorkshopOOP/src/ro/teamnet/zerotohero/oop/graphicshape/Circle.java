package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class Circle extends Shape {
    private int xPos;
    private int yPos;
    private int radius;

    public Circle() {
        this.xPos = 1;
        this.yPos = 1;
        this.radius = 2;
    }

    public Circle(int radius) {
        this.radius = radius;
    }

    public Circle(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public Circle(int xPos, int yPos, int radius) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * Math.pow(this.radius,2);
    }

    @Override
    public String toString() {
        return  "center = (" + this.xPos + "," + this.yPos +") and radius = " + this.radius;
    }

    public void fillColour() {
        System.out.println(super.color);
    }
    public void fillColour(int color) {
        setColor(color);
        System.out.println("The circle color is now " + getColor());
    }

    public void fillColour(float saturation) {
        setSaturation(saturation);
    }
}
