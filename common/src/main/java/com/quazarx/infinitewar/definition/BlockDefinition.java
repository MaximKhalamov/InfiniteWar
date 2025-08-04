package com.quazarx.infinitewar.definition;

import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.List;

public class BlockDefinition extends AbstractDefinition{
    public float hardness;      // Time to destroy block
    public float resistance;    // Resistance to explosion
    public boolean requiresTool;

    private List<InstrumentType> minableTools = new ArrayList<>();

    public BlockDefinition(){
        type = "block";
        subtype = "block";
    }

    @Override
    public ObjectNode getJson(){
        ObjectNode json = super.getJson();
        json.put("hardness", hardness);
        json.put("resistance", resistance);
        json.put("requiresTool", requiresTool);

        json.putArray("minableTools");

        return json;
    }

    public void addTool(InstrumentType instrumentType){
        if(minableTools.contains(instrumentType))
            minableTools.add(instrumentType);
    }
}
