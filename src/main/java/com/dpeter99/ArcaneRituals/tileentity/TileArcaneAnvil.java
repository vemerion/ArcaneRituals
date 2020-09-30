package com.dpeter99.ArcaneRituals.tileentity;

import com.dpeter99.ArcaneRituals.ArcaneRituals;
import com.dpeter99.ArcaneRituals.ArcaneTileEntities;
import com.dpeter99.ArcaneRituals.Registries;
import com.dpeter99.ArcaneRituals.block.ArcaneBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileArcaneAnvil extends TileEntity {

    public static final RegistryObject<TileEntityType<?>> ARCANE_ANVIL_TILE_ENTITY =
            Registries.TILE_ENTITY_REGISTRY.register("arcane_anvil", () -> TileEntityType.Builder.create(TileArcaneAnvil::new, ArcaneBlocks.arcane_anvil).build(null));

    public final ItemStackHandler inventory = new ItemStackHandler(3) {

        @Override
        protected int getStackLimit(int slot, ItemStack stack) {
            switch (slot){
                case 0: return 1;
                case 1:
                case 2: return 64;
            }
            return 1;
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            markDirty();
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
            return super.insertItem(slot, stack, simulate);
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return super.isItemValid(slot, stack);
        }
    };

    private final LazyOptional<IItemHandler> inventory_provider = LazyOptional.of(() -> inventory);


    public TileArcaneAnvil() {
        super(ArcaneTileEntities.arcane_anvil);
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        CompoundNBT invTag = nbt.getCompound("inventory");
        inventory.deserializeNBT(invTag);

        super.read(state, nbt);
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        CompoundNBT inv_tag = inventory.serializeNBT();
        nbt.put("inventory", inv_tag);

        return super.write(nbt);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return inventory_provider.cast();
        }
        return super.getCapability(cap, side);
    }

}
