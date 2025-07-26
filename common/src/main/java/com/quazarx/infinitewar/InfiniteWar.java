package com.quazarx.infinitewar;

import com.quazarx.infinitewar.definition.DefinitionFactory;
import com.quazarx.infinitewar.definition.FoodDefinition;
import com.quazarx.infinitewar.item.ModItemFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class InfiniteWar {
    public static Logger commonLogger = LoggerFactory.getLogger("IW-Common");
    public static final String MOD_ID = "infinitewar";
    ModItemFactory modItemFactory;

    public InfiniteWar(ModItemFactory modItemFactory){
        this.modItemFactory = modItemFactory;
    }

    private void createItems(){
        FoodDefinition diamondAppleDefinition = DefinitionFactory
                .getFoodDefinition("diamond_apple", 6, 10F);
        diamondAppleDefinition.engName = "Diamond apple";

        modItemFactory.createFood(diamondAppleDefinition);
    }

    public void init() {
        createItems();
        if(Parameters.GENERATE_JSON)
            generateJSON();
    }

    private void generateJSON(){
        commonLogger.info("Writing to JSON");
        DefinitionFactory.writeJSONToFile();
    }
}
