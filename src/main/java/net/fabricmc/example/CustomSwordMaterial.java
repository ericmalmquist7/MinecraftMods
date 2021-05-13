package net.fabricmc.example;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CustomSwordMaterial implements ToolMaterial {

    public static final CustomSwordMaterial INSTANCE = new CustomSwordMaterial();

    @Override
    public float getAttackDamage() {
        // getAttackDamage returns the base damage of the tool. Note that most tools ask for an integer damage amount in their constructor, which means the resulting damage is (float) materialDamage + (int) toolDamage + 1. If you want your tool to entirely control its damage amount in its constructor, you can make your material return an attack damage of 0F.
        return 0;
    }

    @Override
    public int getDurability() {
        // getDurability defines the base durability tools will have when they use this material. In vanilla, all tools of the same type have the same durability.
        return 55;
    }

    @Override
    public int getEnchantability() {
        // getEnchantability defines how enchantable a tool is. Gold comes in at 22 Enchatability, while Diamond sits at 10. Higher enchantability means better (and higher-level) enchantments.
        return 18;
    }

    @Override
    public int getMiningLevel() {
        // getMiningLevel sets the mining level of a tool. Diamond has a mining level of 3, and a value of 3+ is required to mine Obsidian.
        return 2;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        // getMiningSpeedMultiplier defines how fast tools are when mining blocks. For a general sense of scale, Wooden has a speed of 2.0F, and Diamond has a speed of 8.0F.
        return 12.0F;
    }

    @Override
    public Ingredient getRepairIngredient() {
        // getRepairIngredient returns the Ingredient required to repair a tool in an anvil.
        return Ingredient.ofItems(Items.ICE);
    }
    
}
