package com.dpeter99.ArcaneRituals.altars.necromantic;

import com.dpeter99.ArcaneRituals.altars.AbstractFluidAltarTileEnity;
import com.dpeter99.ArcaneRituals.fluid.ArcaneFluids;
import com.dpeter99.ArcaneRituals.ArcaneTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IIntArray;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nullable;

public class NecromanticAltarTileEntity extends AbstractFluidAltarTileEnity {

    public static final int FUEL_AMOUNT = 0;
    protected final IIntArray data = new IIntArray() {
        public int get(int index) {
            switch (index) {
                case FUEL_AMOUNT:
                    return tank.getFluidAmount();
                default:
                    return 0;
            }
        }

        public void set(int index, int value) {
            switch (index) {
                case FUEL_AMOUNT:
                    //progress = fuel;
                default:
            }

        }

        public int size() {
            return 1;
        }
    };



    public NecromanticAltarTileEntity() {
        super(ArcaneTileEntities.necromantic_altar);
    }



    @Override
    protected String getAltarType() {
        return "necromantic_altar";
    }

    @Override
    protected void duringCrafting() {

    }

    @Override
    protected Fluid getFluidType() {
        return ArcaneFluids.blood;
    }


    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent(getType().getRegistryName().getPath());
    }

    @Nullable
    @Override
    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new NecromanticAltarContainer(i, world, pos, playerInventory, altarData, data);
    }

}
