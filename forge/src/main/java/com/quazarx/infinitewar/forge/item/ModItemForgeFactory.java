package com.quazarx.infinitewar.forge.item;

import com.quazarx.infinitewar.InfiniteWar;
import com.quazarx.infinitewar.definition.FoodDefinition;
import com.quazarx.infinitewar.item.ModItemFactory;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemForgeFactory implements ModItemFactory {
    public final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, InfiniteWar.MOD_ID);

    @Override
    public void createFood(FoodDefinition foodDefinition) {
        RegistryObject<Item> FOOD = ITEMS.register(foodDefinition.name,
                () -> new Item(new Item.Settings().food(new FoodComponent.Builder()
                        .hunger(foodDefinition.hungerRestores)
                        .saturationModifier(foodDefinition.saturation)
                        .build())));
    }

    public void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
