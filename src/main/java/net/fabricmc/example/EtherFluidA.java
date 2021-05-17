package net.fabricmc.example;

import net.fabricmc.example.registry.BlockRegistry;
import net.fabricmc.example.registry.FluidBlockRegistry;
import net.fabricmc.example.registry.FluidRegistry;
import net.fabricmc.example.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;

public abstract class EtherFluidA extends EtherFluid {
    @Override
	public Fluid getStill() {
		return FluidRegistry.ether_a_fluid.still;
	}
 
	@Override
	public Fluid getFlowing() {
		return FluidRegistry.ether_a_fluid.flowing;
	}
 
	@Override
	public Item getBucketItem() {
		return ItemRegistry.ether_a_bucket.item;
	}

    public static class Flowing extends EtherFluidA {
		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}
 
		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}
 
		@Override
		public boolean isStill(FluidState fluidState) {
			return false;
		}

        @Override
        protected BlockState toBlockState(FluidState state) {
            return FluidBlockRegistry.ether_a_fluid_block.fluid_block.getDefaultState().with(Properties.LEVEL_15, method_15741(state));
        }
	}
 
	public static class Still extends EtherFluidA {
		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}
 
		@Override
		public boolean isStill(FluidState fluidState) {
			return true;
		}

        @Override
        protected BlockState toBlockState(FluidState state) {
            return null;
        }
	}
}
