package ro.teamnet.zerotohero.oop.graphicshape.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Mihaela.Stoian on 7/4/2017.
 */
public class TryCatchResources {
    static String readFirstLineFromFile(String path) throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }

    }
    public static void main(String args[]) {
        String str = null;
        try {
            str = readFirstLineFromFile("C:\\Users\\mihaela.stoian\\workshop2\\WorkshopOOP\\text.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
