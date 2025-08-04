package com.quazarx.infinitewar.definition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.quazarx.infinitewar.InfiniteWar;
import com.quazarx.infinitewar.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefinitionFactory {
    private static final List<AbstractDefinition> definitionList = new ArrayList<>();

    public static FoodDefinition getFoodDefinition(String name, int amount, float saturation){
        FoodDefinition foodDefinition = new FoodDefinition();

        foodDefinition.name = name;
        foodDefinition.hungerRestores = amount;
        foodDefinition.saturation = saturation;

        definitionList.add(foodDefinition);

        return foodDefinition;
    }

    public static ItemDefinition getItemDefinition(String name){
        ItemDefinition itemDefinition = new ItemDefinition();

        itemDefinition.name = name;

        definitionList.add(itemDefinition);

        return itemDefinition;
    }

    public static BlockDefinition getBlockDefinition(String name,
                                                     float hardness,
                                                     float resistance,
                                                     boolean requiresTool){
        BlockDefinition blockDefinition = new BlockDefinition();

        blockDefinition.name = name;

        blockDefinition.hardness = hardness;
        blockDefinition.resistance = resistance;
        blockDefinition.requiresTool = requiresTool;

        definitionList.add(blockDefinition);

        return blockDefinition;
    }

    public static void writeJSONToFile(){
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode jsonArray = objectMapper.createArrayNode();

        for(AbstractDefinition definition : definitionList){
            jsonArray.add(definition.getJson());
        }

        File jsonFile = new File(Parameters.JSON_PATH);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(jsonFile, jsonArray);
            InfiniteWar.commonLogger.info("Successful write JSON definitions to the file {}", jsonFile.getAbsolutePath());
        } catch (IOException e) {
            InfiniteWar.commonLogger.error("Cannot write JSON definitions to the file {}", jsonFile.getAbsolutePath());
//            throw new RuntimeException(e);
        }
    }
}
