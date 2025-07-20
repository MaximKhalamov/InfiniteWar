package com.quazarx.infinitewar.definition;

import java.util.ArrayList;
import java.util.List;

public class DefenitionFactory {
    private static final List<AbstractDefinition> definitionList = new ArrayList<>();

    public static FoodDefinition getFoodDefinition(String name, int amount, float saturation, boolean isWolfFood){
        FoodDefinition foodDefinition = new FoodDefinition();

        foodDefinition.name = name;
        foodDefinition.amount = amount;
        foodDefinition.saturation = saturation;
        foodDefinition.isWolfFood = isWolfFood;

        definitionList.add(foodDefinition);

        return foodDefinition;
    }
}
