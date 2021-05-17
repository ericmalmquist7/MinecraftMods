package net.fabricmc.example.registry;

import net.fabricmc.example.DimensionBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;

public enum DimensionBlockRegistry {
    
    dimension_a(Material.METAL, 4.0f, 1000.0f),
    dimension_b(Material.METAL, 4.0f, 1000.0f),
    dimension_c(Material.METAL, 4.0f, 1000.0f),
    dimension_d(Material.METAL, 4.0f, 1000.0f),
    dimension_e(Material.METAL, 4.0f, 1000.0f);


    public final DimensionBlock block;
    public final BlockItem block_item;

    DimensionBlockRegistry(Material material, float hardness, float resistance){
        
        this.block = new DimensionBlock(FabricBlockSettings.of(material).strength(hardness, resistance));
        this.block_item = new BlockItem(this.block, new FabricItemSettings().group(RegisterHelper.MOD_GROUP));
    }
}
