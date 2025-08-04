package com.quazarx.infinitewar.forge;

import com.quazarx.infinitewar.forge.item.ModItemForgeFactory;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

import com.quazarx.infinitewar.InfiniteWar;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(InfiniteWar.MOD_ID)
public final class InfiniteWarForge {
    ModItemForgeFactory modItemForgeFactory;
    InfiniteWar infiniteWar;

    public InfiniteWarForge(FMLJavaModLoadingContext context) {
        // Run our common setup.
        modItemForgeFactory = new ModItemForgeFactory();
        infiniteWar = new InfiniteWar(modItemForgeFactory);

        infiniteWar.init();

        IEventBus modEventBus = context.getModEventBus();

//        modEventBus.addListener(this::commonSetup);

        modItemForgeFactory.BLOCKS.register(modEventBus);
        modItemForgeFactory.ITEMS.register(modEventBus);

//        infiniteWar.register(modEventBus);

//        BLOCKS.register(modEventBus);
//        ITEMS.register(modEventBus);
//        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

//        modEventBus.addListener(this::addCreative);

//        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        
    }
}
