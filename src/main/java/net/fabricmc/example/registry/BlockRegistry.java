package net.fabricmc.example.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;

public enum BlockRegistry {
    base_a(Material.METAL, 4.0f, 1000.0f),
    base_b(Material.METAL, 4.0f, 1000.0f),
    base_c(Material.METAL, 4.0f, 1000.0f),
    base_d(Material.METAL, 4.0f, 1000.0f),
    base_e(Material.METAL, 4.0f, 1000.0f),
    
    dimension_a(Material.METAL, 4.0f, 1000.0f),
    dimension_b(Material.METAL, 4.0f, 1000.0f),
    dimension_c(Material.METAL, 4.0f, 1000.0f),
    dimension_d(Material.METAL, 4.0f, 1000.0f),
    dimension_e(Material.METAL, 4.0f, 1000.0f),

    extractor_a(Material.METAL, 4.0f, 1000.0f),
    extractor_b(Material.METAL, 4.0f, 1000.0f),
    extractor_c(Material.METAL, 4.0f, 1000.0f),
    extractor_d(Material.METAL, 4.0f, 1000.0f),
    extractor_e(Material.METAL, 4.0f, 1000.0f);


    public final Block block;
    public final BlockItem blockItem;

    BlockRegistry(Material material, float hardness, float resistance){
        
        this.block = new Block(FabricBlockSettings.of(material).strength(hardness, resistance));
        this.blockItem = new BlockItem(this.block, new FabricItemSettings().group(RegisterHelper.MOD_GROUP));
    }
}
