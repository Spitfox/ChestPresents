package io.github.justuswalterhelk.chestpresents;

import io.github.justuswalterhelk.chestpresents.blocks.PresentBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit
{
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Main.MODID);

    public static final RegistryObject<BlockEntityType<?>> present_block_entity = BLOCK_ENTITES.register("present_block_entity", () -> BlockEntityType.Builder.of(PresentBlockEntity::new, BlockInit.presentBlock.get()).build(null));
}
