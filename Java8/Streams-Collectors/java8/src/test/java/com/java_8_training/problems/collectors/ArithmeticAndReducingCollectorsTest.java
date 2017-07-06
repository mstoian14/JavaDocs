
package com.java_8_training.problems.collectors;

import org.junit.Ignore;
import org.junit.Test;

import java.util.IntSummaryStatistics;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.summarizingInt;
import static junit.framework.Assert.assertEquals;

@Ignore
public class ArithmeticAndReducingCollectorsTest {

    // See: Dish.menu.stream()

    @Test
    public void leastCaloricDishMEAT() {
        //TODO #C5
//        Dish leastCaloricMEAT = new Dish();
        Dish leastCaloricMEAT = Dish.menu.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT)
                .min(comparing(Dish::getCalories)).get();


        assertEquals("chicken", leastCaloricMEAT.getName());
    }

    @Test
    public void statisticsForVegetarianDishes() {
        //TODO #C6
//        IntSummaryStatistics vegetarianStats = new IntSummaryStatistics();
        IntSummaryStatistics vegetarianStats = Dish.menu.stream()
                .filter(Dish::isVegetarian)
                .collect(summarizingInt(Dish::getCalories));
        System.out.println(vegetarianStats);
        assertEquals(4, vegetarianStats.getCount());
        assertEquals(1550, vegetarianStats.getSum());
        assertEquals(120, vegetarianStats.getMin());
        assertEquals(387.5, vegetarianStats.getAverage());
        assertEquals(550, vegetarianStats.getMax());

    }
}
