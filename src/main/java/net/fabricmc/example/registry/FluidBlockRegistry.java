package net.fabricmc.example.registry;

import net.fabricmc.example.ExampleMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum FluidBlockRegistry {
    
    ether_a_fluid_block(new FluidBlock(FluidRegistry.ether_a_fluid.still, FabricBlockSettings.copy(Blocks.WATER)){});

    public final FluidBlock fluid_block;
    public final BlockItem block_item;

    FluidBlockRegistry(FluidBlock fluid_block){
        this.fluid_block = fluid_block;
        this.block_item = new BlockItem(this.fluid_block, new FabricItemSettings().group(RegisterHelper.MOD_GROUP));
    }
}
