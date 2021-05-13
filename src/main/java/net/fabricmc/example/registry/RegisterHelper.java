package net.fabricmc.example.registry;

import net.fabricmc.example.CustomFabricItem;
import net.fabricmc.example.ExampleMod;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RegisterHelper {

    public static final Item ModIconItem = new Item(new FabricItemSettings());

    public static final ItemGroup MOD_GROUP = FabricItemGroupBuilder.create(
		new Identifier(ExampleMod.MOD_ID_STRING, "general"))
		.icon(() -> new ItemStack(ModIconItem))
		.build();

    public static void initRegistry(){
        System.out.println("Beginning registry.");
        Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID_STRING, "mod_group_icon"), ModIconItem);
        registerItems();
        registerBlocks();
    }

    public static void registerItems(){
        System.out.println("Registering Items.");
        for(ItemRegistry item : ItemRegistry.values()){
            Registry.register(Registry.ITEM, new Identifier(ExampleMod.MOD_ID_STRING, item.toString()), item.item);
        }
    }

    public static void registerBlocks(){
        System.out.println("Registering Blocks.");
        for(BlockRegistry block : BlockRegistry.values()){
            Registry.register(Registry.BLOCK, new Identifier(ExampleMod.MOD_ID_STRING, block.toString()), block.block);
            Registry.register(Registry.ITEM,  new Identifier(ExampleMod.MOD_ID_STRING, block.toString()), block.blockItem);
        }
    }

	 
    
}
