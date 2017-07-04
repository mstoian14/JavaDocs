package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class Shape extends AbstractShape implements ShapeBehaviour{
    protected int color;
    protected float saturation;

    public double area() {
        return 0.3;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public int getColor() {
        return this.color;
    }
}
