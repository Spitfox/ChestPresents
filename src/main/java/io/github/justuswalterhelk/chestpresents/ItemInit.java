package io.github.justuswalterhelk.chestpresents;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MODID);

    public static final RegistryObject<BlockItem> present_item = ITEMS.register("present_block", () -> new BlockItem(BlockInit.presentBlock.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
}
