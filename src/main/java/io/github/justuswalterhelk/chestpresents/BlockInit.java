package io.github.justuswalterhelk.chestpresents;

import io.github.justuswalterhelk.chestpresents.blocks.PresentBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MODID);

    public static final RegistryObject<Block> presentBlock = BLOCKS.register("present_block", () -> new PresentBlock(BlockBehaviour.Properties.of(Material.WOOD).destroyTime(2)));


}
