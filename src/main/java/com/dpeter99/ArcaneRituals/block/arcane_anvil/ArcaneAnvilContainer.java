package com.dpeter99.ArcaneRituals.block.arcane_anvil;

import com.dpeter99.ArcaneRituals.ArcaneTileEntities;
import com.dpeter99.bloodylib.container.TileEntityContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class ArcaneAnvilContainer extends TileEntityContainer<ArcaneAnvilTileEntity> {


    public ArcaneAnvilContainer(int id, World world, BlockPos pos, PlayerInventory playerInventory) {
        super(ArcaneTileEntities.arcane_anvil_container, id,world,pos,playerInventory);

        addSlots();
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }


    @Override
    protected void addSlots() {
        addSlot(new SlotItemHandler(inv,0,80,94));
        addSlot(new SlotItemHandler(inv,1,17,114));
        addSlot(new SlotItemHandler(inv,2,144,114));

        layoutPlayerInventorySlots(8,144);
    }
}
