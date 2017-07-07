package exercise3;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Mihaela.Stoian on 7/7/2017.
 */
public class Main {
    public static <T, E> void print(Map<T, E> m) {
        Iterator it = m.entrySet().iterator();
        while(it.hasNext()) {
            T st = (T)it.next();
            System.out.print(st.toString());
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Map<Student1,BigDecimal> st1 = new HashMap<Student1, BigDecimal>();
        Map<Student2,BigDecimal> st2 = new HashMap<Student2, BigDecimal>();
        Map<Student3,BigDecimal> st3 = new HashMap<Student3, BigDecimal>();
        Map<Student4,BigDecimal> st4 = new HashMap<Student4, BigDecimal>();

        //hash doar pe first name + eq => dc. punem acelasi first name, dar cu lastname si varst.
        // dif => se suprascrie doar varsta ob. in map in care gaseste firstName egal, chiar dc. ii dam alt lastname
        st1.put(new Student1("Ana", "Maria"), new BigDecimal(1.0));
        st1.put(new Student1("Maria", "Ioana"), new BigDecimal(2.0));
        st1.put(new Student1("Annet", "Maria"), new BigDecimal(3.0));
        Main.print(st1);
        st1.put(new Student1("Ana", "Maria"), new BigDecimal(3.0));
        print(st1);
        st1.put(new Student1("Ana", "Mariaa"), new BigDecimal(9.0));
        print(st1);
        st1.put(new Student1("Alin", "Mariaa"), new BigDecimal(9.0));
        print(st1);


        //stud2
        //hash firstName just
        //eq. all prop
        System.out.println();
        st2.put(new Student2("Pruna", "Marius"), new BigDecimal(1.0));
        st2.put(new Student2("Perna", "Acasa"), new BigDecimal(2.0));
        st2.put(new Student2("Somnul", "Dulce"), new BigDecimal(3.0));
        print(st2);
        st2.put(new Student2("Somnul", "Dulceag"), new BigDecimal(3.0));
        print(st2);
        st2.put(new Student2("Somnul", "Dulceag"), new BigDecimal(4.0));
        print(st2); //varsta se modfica, adica campul value

        //stud3
        //hash all prop
        //eq first name
        System.out.println();
        st3.put(new Student3("Mere", "Merisor"), new BigDecimal(1.00));
        st3.put(new Student3("Pere", "Perisoare"), new BigDecimal(2.00));
        st3.put(new Student3("Capsune", "Capsunele"), new BigDecimal(3.00));
        print(st3);
        st3.put(new Student3("Capsunes", "Capsunele"), new BigDecimal(4.00));
        print(st3);
        st3.put(new Student3("Capsune", "Capsuneles"), new BigDecimal(5.00));
        print(st3); //chiar daca verifica daca exista first name, cand face hasul obitine alt rezultat

        //stud4
        // hash all
        //eq all
        System.out.println();
        st4.put(new Student4("PrenumeA", "NumeA"), new BigDecimal(1.0));
        st4.put(new Student4("PrenumeB", "NumeB"), new BigDecimal(2.0));
        st4.put(new Student4("PrenumeC", "NumeC"), new BigDecimal(3.0));
        print(st4);
        st4.put(new Student4("PrenumeC", "Nume"), new BigDecimal(3.0));
        print(st4);
        st4.put(new Student4("PrenumeC", "NumeC"), new BigDecimal(32.0));
        print(st4);



    }
}
