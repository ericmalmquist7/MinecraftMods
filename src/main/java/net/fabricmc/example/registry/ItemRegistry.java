package net.fabricmc.example.registry;

import net.fabricmc.example.ExampleMod;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.Item.Settings;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum ItemRegistry {
    mat_a(),
    mat_b(),
    mat_c(),
    mat_d(),
    mat_e(),

    aux_a(),
    aux_b(),
    aux_c(),
    aux_d(),
    aux_e(),

    ether_a_bucket(new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)),

    axetest();


    public final Item item;

    ItemRegistry(){
        this.item = new Item(new Settings().group(RegisterHelper.MOD_GROUP));
    }

    ItemRegistry(Settings settings){
        this.item = new Item(settings.group(RegisterHelper.MOD_GROUP));
    }
}
