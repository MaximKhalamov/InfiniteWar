package com.quazarx.infinitewar.fabric.item;

import com.quazarx.infinitewar.InfiniteWar;
import com.quazarx.infinitewar.definition.FoodDefinition;
import com.quazarx.infinitewar.item.ModItemFactory;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItemFabricFactory implements ModItemFactory {

    @Override
    public void createFood(FoodDefinition foodDefinition) {
        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                .hunger(foodDefinition.amount)
                .saturationModifier(foodDefinition.saturation);

        if(foodDefinition.isAlwaysEatable) foodComponentBuilder.alwaysEdible();

        FoodComponent foodComponent = foodComponentBuilder.build();

        Registry.register(Registries.ITEM,
                new Identifier(InfiniteWar.MOD_ID, foodDefinition.name),
                new Item(new Item.Settings().food(foodComponent) ));
    }
}
