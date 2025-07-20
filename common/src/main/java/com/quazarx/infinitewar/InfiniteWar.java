package com.quazarx.infinitewar;

import com.quazarx.infinitewar.definition.DefenitionFactory;
import com.quazarx.infinitewar.definition.FoodDefinition;
import com.quazarx.infinitewar.item.ModItemFactory;

public final class InfiniteWar {
    public static final String MOD_ID = "infinitewar";
    ModItemFactory modItemFactory;

    public InfiniteWar(ModItemFactory modItemFactory){
        this.modItemFactory = modItemFactory;
    }

    private void createItems(){
        FoodDefinition diamondAppleDefinition = DefenitionFactory
                .getFoodDefinition("diamond_apple", 6, 10F, false);

        modItemFactory.createFood(diamondAppleDefinition);
    }

    public void init() {
        createItems();
    }
}
