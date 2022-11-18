package io.github.justuswalterhelk.chestpresents;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;


@Mod(Main.MODID)
public class Main {
    public static final String MODID = "chestpresents";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Main()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);

        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);
    }

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    @SubscribeEvent
    public void PlayerInteract(PlayerInteractEvent.RightClickBlock event)
    {
        Level level = event.getLevel();
        BlockPos position = event.getPos();
        Player player = event.getEntity();

        ItemStack blockToPlace = event.getItemStack();

        if(blockToPlace.is(BlockInit.presentBlock.get().asItem()))
        {
            CompoundTag tag = blockToPlace.getTag();
            ItemStackHandler inv = new ItemStackHandler(27);
            inv.deserializeNBT(tag);
            for(int i = 0; i < inv.getSlots(); i++)
            {
                //Need Entity to store the data on the Block before destroying it!
            }
        }

        BlockEntity block = level.getBlockEntity(position);
        try {
            ChestBlockEntity chest = (ChestBlockEntity) block;
            if (chest != null && player.isCrouching()) {
                InvWrapper handler = (InvWrapper) chest.getCapability(ForgeCapabilities.ITEM_HANDLER).orElseThrow(RuntimeException::new);
                ItemStackHandler inv = new ItemStackHandler(handler.getSlots());
                for(int i = 0; i < handler.getSlots(); i++)
                {
                    inv.setStackInSlot(i, handler.getStackInSlot(i));
                }
                CompoundTag tag = inv.serializeNBT();
                chest.clearContent();
                level.destroyBlock(position, false);
                ItemStack present = new ItemStack(ItemInit.present_item.get());


                present.setTag(tag);

                ItemEntity item = new ItemEntity(level, position.getX(), position.getY(), position.getZ(), present);
                level.addFreshEntity(item);
            }
        }
        catch (Exception e)
        {

        }

        Item item = event.getItemStack().getItem();
    }
}
