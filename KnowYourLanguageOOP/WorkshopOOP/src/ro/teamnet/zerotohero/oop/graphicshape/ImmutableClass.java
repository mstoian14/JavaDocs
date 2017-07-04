package ro.teamnet.zerotohero.oop.graphicshape;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class ImmutableClass {
    String str;
    public ImmutableClass(String str) {
        this.str = new String(str);
    }

    public String getString() {
        return this.str;
    }
}
