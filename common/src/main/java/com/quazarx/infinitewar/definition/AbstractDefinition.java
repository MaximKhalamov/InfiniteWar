package com.quazarx.infinitewar.definition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class AbstractDefinition {
    public String name          = "";
    public String engName       = "";
    public String type          = "abstract";
    public String subtype       = "abstract";
    public boolean genRecipe    = true;
    public boolean genModel     = true;

    AbstractDefinition(){}

    // MUST be overridden in the children classes
    public ObjectNode getJson(){
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode json = mapper.createObjectNode();

        json.put("name", name);
        json.put("type", type);
        json.put("subtype", subtype);
        json.put("engName", engName);
        json.put("genRecipe", genRecipe);
        json.put("genModel", genModel);

        return json;
    }

    //    public String getJSON();
}
