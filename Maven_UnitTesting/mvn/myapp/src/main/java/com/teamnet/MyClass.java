package com.teamnet;

import java.util.Random;

/**
 * Created by Mihaela.Stoian on 7/10/2017.
 */
public class MyClass {
    public String concatenate(String one, String two) {
        if(one == null) {
            return two;
        }
        if(two == null) {
            return one;
        }
        return one+two;
    }

    public boolean getBoolean() {
        return new Random().nextBoolean();
    }
}
