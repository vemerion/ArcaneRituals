package com.dpeter99.ArcaneRituals.block;

import com.dpeter99.ArcaneRituals.item.ArcaneItems;
import com.dpeter99.ArcaneRituals.tileentity.WitchAltarTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class WitchAltarBlock extends Block {

    protected static final VoxelShape BASE_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D);

    public WitchAltarBlock() {
        super(Properties.create(Material.IRON)
        .notSolid());

        setRegistryName("witch_altar");
    }

    /**
     * Called throughout the code as a replacement for block instanceof BlockContainer
     * Moving this to the Block base class allows for mods that wish to extend vanilla
     * blocks, and also want to have a tile entity on that block, may.
     * <p>
     * Return true from this function to specify this block has a tile entity.
     *
     * @param state State of the current block
     * @return True if block has a tile entity, false otherwise
     */
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return BASE_SHAPE;
    }

    /**
     * Called throughout the code as a replacement for ITileEntityProvider.createNewTileEntity
     * Return the same thing you would from that function.
     * This will fall back to ITileEntityProvider.createNewTileEntity(World) if this block is a ITileEntityProvider
     *
     * @param state The state of the current block
     * @param world The world to create the TE in
     * @return A instance of a class extending TileEntity
     */
    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WitchAltarTileEntity();
    }



    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult p_225533_6_) {
        if(!worldIn.isRemote){
            ItemStack current_item = player.inventory.getCurrentItem();
            WitchAltarTileEntity tileEntity = (WitchAltarTileEntity) worldIn.getTileEntity(pos);

            if(current_item.getItem() == ArcaneItems.vial) {
                if(tileEntity.hasSpaceForBlood()) {
                    current_item.shrink(1);
                    tileEntity.addBlood(10);
                }
            }
            else if(current_item.getItem() == ArcaneItems.basic_wand)
            {
                tileEntity.startCrafting();
            }
            else if(tileEntity instanceof INamedContainerProvider){
                NetworkHooks.openGui((ServerPlayerEntity) player,(INamedContainerProvider)tileEntity,tileEntity.getPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
            return ActionResultType.CONSUME;
        }
        return ActionResultType.CONSUME; // super.onBlockActivated(state, worldIn, pos, player, handIn, p_225533_6_);
    }
}
