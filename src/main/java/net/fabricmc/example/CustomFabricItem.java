package net.fabricmc.example;

import java.util.List;
import java.util.Random;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class CustomFabricItem extends Item {
		
    public long cooldown = 5000;
    private long lastTime;

    public CustomFabricItem(Settings settings){
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (System.currentTimeMillis() - lastTime> cooldown){
            lastTime = System.currentTimeMillis();

            playerEntity.playSound(SoundEvents.BLOCK_PORTAL_TRAVEL, 0.15F, 3.0F);
            playerEntity.heal(1.0F);
        }
        else{
            playerEntity.playSound(SoundEvents.BLOCK_METAL_HIT, 1.25F, 2.0F);
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext tooltipContext){
        tooltip.add(new TranslatableText("item.em-testing.custom_test_item.tooltip"));
    }

}