package net.fabricmc.example.registry;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Settings;

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
    aux_e();


    public final Item item;

    ItemRegistry(){
        this.item = new Item(new Settings().group(RegisterHelper.MOD_GROUP));
    }
}
