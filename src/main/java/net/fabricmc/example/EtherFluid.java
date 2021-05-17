package net.fabricmc.example;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.block.entity.BlockEntity;

public abstract class EtherFluid extends FlowableFluid{
    @Override
	public boolean matchesType(Fluid fluid) {
		return fluid == getStill() || fluid == getFlowing();
	}

    @Override
	protected boolean isInfinite() {
		return false;
	}
    
    @Override //Drop stacks if liquid breaks replacable block
	protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
		final BlockEntity blockEntity = state.getBlock().hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world, pos, blockEntity);
	}
    
    @Override
	protected boolean canBeReplacedWith(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
		return false;
	}

    @Override
	protected int getFlowSpeed(WorldView worldView) {
		return 8;
	}

    @Override
	protected int getLevelDecreasePerBlock(WorldView worldView) {
		return 1;
	}

    @Override
	public int getTickRate(WorldView worldView) {
		return 2;
	}

    @Override
	protected float getBlastResistance() {
		return 100.0F;
	}

    
}
