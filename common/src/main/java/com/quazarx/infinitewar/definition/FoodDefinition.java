package com.quazarx.infinitewar.definition;

import com.fasterxml.jackson.databind.node.ObjectNode;

public class FoodDefinition extends AbstractDefinition{
    public int hungerRestores       = 0;
    public float saturation         = 0F;
    public boolean isAlwaysEatable  = false;

    public FoodDefinition(){
        type    = "item";
        subtype = "food";
    }

    @Override
    public ObjectNode getJson(){
        ObjectNode json = super.getJson();
        json.put("hungerRestores", hungerRestores);
        json.put("saturation", saturation);
        json.put("isAlwaysEatable", isAlwaysEatable);

        return json;
    }
}
