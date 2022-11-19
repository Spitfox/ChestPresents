package io.github.justuswalterhelk.chestpresents.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class PresentBlock extends BaseEntityBlock {
    public PresentBlock(Properties properties) {super(properties);}

    private static final VoxelShape SHAPE = makeShape();

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx)
    {
        return SHAPE;
    }


    public static VoxelShape makeShape(){
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 1, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 1, 0, 0.5625, 1.0625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0, -0.0625, 0.5625, 1, 0), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 0, 1, 0.5625, 1, 1.0625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 1, 0.4375, 1, 1.0625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.0625, 0, 0.4375, 0, 1, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 1.0625, 0.1875, 0.5625, 1.125, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 1.1875, 0.1875, 0.5625, 1.25, 0.4375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 1.125, 0.125, 0.5625, 1.1875, 0.1875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 1.1875, 0.5625, 0.5625, 1.25, 0.8125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 1.125, 0.4375, 0.5625, 1.1875, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.4375, 1.125, 0.8125, 0.5625, 1.1875, 0.875), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.5625, 1.1875, 0.4375, 0.8125, 1.25, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 1.0625, 0.4375, 0.8125, 1.125, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.8125, 1.125, 0.4375, 0.875, 1.1875, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 1.125, 0.4375, 0.1875, 1.1875, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.1875, 1.1875, 0.4375, 0.4375, 1.25, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(1, 0, 0.4375, 1.0625, 1, 0.5625), BooleanOp.OR);
        return shape;
    }

    /* Block Entity */

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    //Called when block gets destroyed
    @Override
    public void onRemove(BlockState p_60515_, Level p_60516_, BlockPos p_60517_, BlockState p_60518_, boolean p_60519_) {
        //TODO: Summon items on level with a slight delay in between
        System.out.print("remove");
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos p_153215_, BlockState p_153216_) {
        return new PresentBlockEntity(p_153215_, p_153216_);
    }
}
