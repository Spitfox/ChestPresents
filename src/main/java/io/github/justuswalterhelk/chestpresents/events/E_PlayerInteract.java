package io.github.justuswalterhelk.chestpresents.events;


import io.github.justuswalterhelk.chestpresents.BlockInit;
import io.github.justuswalterhelk.chestpresents.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class E_PlayerInteract
{
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
            BlockEntity block1 =  level.getBlockEntity(position.above());
        }
        else
        {
            BlockEntity block = level.getBlockEntity(position);
            try {
                ChestBlockEntity chest = (ChestBlockEntity) block;
                if (chest != null && player.isCrouching() && event.getItemStack().is(ItemInit.wrapping_paper.get().asItem()) ) {
                    event.getItemStack().shrink(1);
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
            catch (Exception ignored) {}
        }


    }
}
