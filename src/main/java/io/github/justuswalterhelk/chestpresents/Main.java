package io.github.justuswalterhelk.chestpresents;

import com.mojang.logging.LogUtils;
import io.github.justuswalterhelk.chestpresents.events.E_PlayerInteract;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "chestpresents";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Main()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new E_PlayerInteract());

        BlockEntityInit.BLOCK_ENTITES.register(bus);
        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
    }

}