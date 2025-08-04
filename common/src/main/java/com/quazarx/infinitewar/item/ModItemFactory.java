package com.quazarx.infinitewar.item;

import com.quazarx.infinitewar.definition.BlockDefinition;
import com.quazarx.infinitewar.definition.FoodDefinition;
import com.quazarx.infinitewar.definition.ItemDefinition;

public interface ModItemFactory {
    public void createFood(FoodDefinition foodDefinition);
    public void createBlock(BlockDefinition blockDefinition);
    public void createItem(ItemDefinition itemDefinition);
}
