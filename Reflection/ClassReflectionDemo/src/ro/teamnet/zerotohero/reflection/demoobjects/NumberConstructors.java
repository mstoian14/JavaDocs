package ro.teamnet.zerotohero.reflection.demoobjects;

/**
 * Created by Mihaela.Stoian on 7/12/2017.
 */
public class NumberConstructors {
    int x;
    int y;
    int z;
    String str;

    public NumberConstructors() {
        this.x = this.y = this.z = 0;
    }
    public NumberConstructors(String str) {
        System.out.println("I have been invoke!");
        this.str = str;
    }
    public NumberConstructors(int x) {
        this.x = x;
    }

    public NumberConstructors(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
