package io.github.justuswalterhelk.chestpresents.blocks;

import io.github.justuswalterhelk.chestpresents.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class PresentBlockEntity extends BlockEntity {
    public PresentBlockEntity(BlockPos p_155229_, BlockState p_155230_) {
        super(BlockEntityInit.present_block_entity.get(), p_155229_, p_155230_);
    }
}
