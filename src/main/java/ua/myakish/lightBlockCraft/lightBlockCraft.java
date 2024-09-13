package ua.myakish.lightBlockCraft;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class lightBlockCraft extends JavaPlugin {

    @Override
    public void onEnable() {
        createLightBlockRecipe();

        if (Bukkit.getRecipe(new NamespacedKey(this, "invisible_light")) != null) {
            getLogger().info("Recipe registered successfully!");
        } else {
            getLogger().severe("Failed to register recipe.");
        }
    }

    private void createLightBlockRecipe() {
        ItemStack lightBlock = new ItemStack(Material.LIGHT, 8);
        ItemMeta lightMeta = lightBlock.getItemMeta();
        lightMeta.setDisplayName("Invisible Light Block");
        lightBlock.setItemMeta(lightMeta);

        ItemStack invisibilityPotion = new ItemStack(Material.POTION);
        PotionMeta potionMeta = (PotionMeta) invisibilityPotion.getItemMeta();
        potionMeta.setBasePotionData(new PotionData(PotionType.INVISIBILITY));
        invisibilityPotion.setItemMeta(potionMeta);

        NamespacedKey key = new NamespacedKey(this, "invisible_light");

        ShapedRecipe recipe = new ShapedRecipe(key, lightBlock);
        recipe.shape("TTT", "PLP", "TAT");

        recipe.setIngredient('T', Material.TORCH);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        recipe.setIngredient('L', Material.REDSTONE_LAMP);
        recipe.setIngredient('P', new RecipeChoice.ExactChoice(invisibilityPotion));

        Bukkit.addRecipe(recipe);
    }
}
