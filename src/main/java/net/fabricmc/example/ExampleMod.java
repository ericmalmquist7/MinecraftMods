package net.fabricmc.example;
import net.fabricmc.example.registry.RegisterHelper;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ExampleMod implements ModInitializer {

	public static final String MOD_ID_STRING = "em-testing";
	public static final String MOD_NAME_STRING = "Ether";

	public static final ToolItem CUSTOMSWORD_ITEM = new CustomSwordItem(CustomSwordMaterial.INSTANCE, 5, 4.0F, new Item.Settings().group(RegisterHelper.MOD_GROUP) );

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.	

		RegisterHelper.initRegistry();
		
		
	}

}
