package com.java_8_training.problems.lambdas;


import com.java_8_training.problems.lambdas.model.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.Arrays.asList;

public class LambdasRefactor {
    public static void main(String[] args) {
        System.out.println(sortInventoryByDecreasingWeight());
        System.out.println(filterGreenApples());
    }

    public static List<Apple> sortInventoryByDecreasingWeight() {
        //TODO: refactor to use lambda expression

        List<Apple> inventory = asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
        inventory.sort((apple1, apple2) -> apple2.getWeight().compareTo(apple1.getWeight()));
//        inventory.sort((apple1, apple2) -> {return Integer.compare(apple1.getWeight(), apple2.getWeight());});
        return inventory;
    }

    public static List<Apple> filterGreenApples() {

        // TODO: refactor to use lambda expression
        // TODO: refactor to use standard functional interface
        List<Apple> inventory = asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));

//        List<Apple> greenApples = filterApples(inventory, new ApplePredicate() {
//            @Override
//            public boolean test(Apple apple) {
//                return "green".equals(apple.getColor());
//            }
//        });
        String destColor = "green";
        List<Apple> greenApples = filterApples(inventory, (Apple a) -> destColor.equals(a.getColor()));

        return greenApples;
    }

    private static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    @FunctionalInterface
    interface ApplePredicate {
        boolean test(Apple apple);
    }
}
