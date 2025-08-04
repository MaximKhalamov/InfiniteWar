package com.quazarx.infinitewar.fabric.item;

import com.quazarx.infinitewar.InfiniteWar;
import com.quazarx.infinitewar.definition.BlockDefinition;
import com.quazarx.infinitewar.definition.FoodDefinition;
import com.quazarx.infinitewar.definition.ItemDefinition;
import com.quazarx.infinitewar.item.ModItemFactory;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModItemFabricFactory implements ModItemFactory {

    @Override
    public void createFood(FoodDefinition foodDefinition) {
        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                .hunger(foodDefinition.hungerRestores)
                .saturationModifier(foodDefinition.saturation);

        if(foodDefinition.isAlwaysEatable) foodComponentBuilder.alwaysEdible();

        FoodComponent foodComponent = foodComponentBuilder.build();

        Registry.register(Registries.ITEM,
                new Identifier(InfiniteWar.MOD_ID, foodDefinition.name),
                new Item(new Item.Settings().food(foodComponent) ));
    }

    @Override
    public void createItem(ItemDefinition itemDefinition){

    }

    @Override
    public void createBlock(BlockDefinition blockDefinition){
        AbstractBlock.Settings settings = AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY).
                strength(blockDefinition.hardness, blockDefinition.resistance);

        if(blockDefinition.requiresTool)
            settings.requiresTool();

        Block customBlock = new Block(settings);

        Item customBlockItem = new BlockItem(customBlock, new Item.Settings());
        Identifier ID = Identifier.of(InfiniteWar.MOD_ID, blockDefinition.name);

        Registry.register(Registries.BLOCK, ID, customBlock);
        Registry.register(Registries.ITEM, ID, customBlockItem);
    }
}
