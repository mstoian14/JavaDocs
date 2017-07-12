package ro.teamnet.zerotohero.reflection;

import ro.teamnet.zerotohero.reflection.demoobjects.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * in order to resolve the exercises below, you will have to create
 * all the needed clasees, with their members and methods
 * (in ro.teamnet.zerotohero.reflection.demoobjects package)
 */
public class ClassReflectionDemoMain {
    public static void main(String[] args) {
        // get the class for a String object, and print it
		String str = new String("Ana");
		Class cls = str.getClass();
        System.out.println(cls.toString());

        // get the class of an Enum, and print it
        Class clsEnul = EnumClass.Season.class;
        System.out.println(clsEnul.toString());

        // get the class of a collection, and print it\
        ArrayList<String> als = new ArrayList<>(10);
        Class clsCollection = als.getClass();
        System.out.println(clsCollection);

        // get the class of a primitive type, and print it
        Class clsPrimitive = int.class;
        System.out.println(clsPrimitive.toString());

        // get and print the class for a field of primitive type
        FieldPrimitive fp = new FieldPrimitive();
        try {
            Class clsField = fp.getClass();
            System.out.println("Primitiva: " + clsField.getField("i").getType());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // get and print the class for a primitive type, using the wrapper class
        WrapperClassPrimitiveType wcpt = new WrapperClassPrimitiveType();
        Class clsPrimitiveClass = Integer.TYPE;
        System.out.println("Primitive type using wrapper class: " + clsPrimitiveClass);

        // get the class for a specified class name
        try {
            Class clsName = Class.forName("ro.teamnet.zerotohero.reflection.demoobjects.ClassName");
            System.out.println("Get the class for a specified class name: " + clsName.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // get the superclass of a class, and print it
        Class clspc = ChildClass.class.getSuperclass();
        System.out.println("get the superclass of a class, and print it \n\t" + clspc.toString());
        // get the superclass of the superclass above, and print it
        ChildClass cld = new ChildClass();
        Class clsSuperClass =  cld.getClass();
        clsSuperClass = clsSuperClass.getSuperclass();
        clsSuperClass = clsSuperClass.getSuperclass();
        System.out.println("get the superclass of the superclass above, and print it \n\t" + clsSuperClass.toString());

        // get and print the declared classes within some other class
        Class clases[] = OuterClass.class.getDeclaredClasses();
        System.out.println("get and print the declared classes within some other class\t");
        for(Class c : clases) {
            System.out.println("\t" + c);
        }

        // print the number of constructors of a class
        System.out.println("print the number of constructors of a class \n\t" + NumberConstructors.class.getDeclaredConstructors().length);

        //get and invoke a public constructor of a class
        NumberConstructors cons = new NumberConstructors();
        Class clsCons = cons.getClass();
        try {
            System.out.println(clsCons.getConstructor(String.class).newInstance("Ceva"));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //get and print the class of one private field
        NumberConstructors strCons = new NumberConstructors("Ceva");
        try {
            Field privateStringField = ClassPrivateField.class.getDeclaredField("var");
            System.out.println("get and print the class of one private field \n\t" + privateStringField);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //TODO set and print the value of one private field for an object
        ClassPrivateField cpf = new ClassPrivateField();
        try {
            Field privateStringField = ClassPrivateField.class.getDeclaredField("var");
            privateStringField.setAccessible(true);
            try {
                int fieldVal = (int)privateStringField.get(cpf);
                System.out.println("set and print the value of one private field for an object " + fieldVal);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        //TODO get and print only the public fields class
        Field fd[] = ClassPrivateField.class.getFields();
        System.out.println("Public fields: ");
        for(Field f : fd) {
            System.out.println("\t" + f);
        }

        //TODO get and invoke one public method of a class
        try {
            Method publicMethod = GetPublicMethod.class.getMethod("printMe", null);
            try {
                System.out.println("\t");
                publicMethod.invoke(new GetPublicMethod(), new Object[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        //TODO get and invoke one inherited method of a class
        try {
            Method childMethod = ChildMethod.class.getMethod("printParent", new Class[0]);
            try {
                childMethod.invoke(new ChildMethod(), new Object[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        //TODO invoke a method of a class the classic way for ten times, and print the timestamp (System.currentTimeMillis())
        System.out.println();
        TimeStampClass tsc = new TimeStampClass();
        double s1 = System.currentTimeMillis()*100;
        for(int i = 0; i < 10; i++) {
            tsc.printTime();
        }
        double s2 = System.currentTimeMillis()*100 - s1;
        System.out.println();
        System.out.printf("%.10f \n", s2);
        //TODO invoke a method of a class by Reflection for 100 times, and print the timestamp
		//what do you observe?
		
    }
}
