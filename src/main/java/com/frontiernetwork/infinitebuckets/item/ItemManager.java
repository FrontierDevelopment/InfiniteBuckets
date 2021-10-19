package com.frontiernetwork.infinitebuckets.item;

import com.frontiernetwork.infinitebuckets.InfiniteBuckets;
import com.frontiernetwork.infinitebuckets.utils.StringUtils;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.stream.Collectors;

public class ItemManager {

    private final InfiniteBuckets plugin;

    public ItemManager(InfiniteBuckets plugin) {
        this.plugin = plugin;
    }

    public ItemStack infiniteWaterBucket() {
        ItemStack item = new ItemStack(Material.WATER_BUCKET, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(StringUtils.format(plugin.getConfig().getString("water.display")));

        List<String> lore = plugin.getConfig().getStringList("water.lore").stream().map(StringUtils::format).collect(Collectors.toList());

        meta.setLore(lore);

        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "infinite"), PersistentDataType.INTEGER, 0);

        item.setItemMeta(meta);

        return item;
    }

    public ItemStack infiniteLavaBucket() {
        ItemStack item = new ItemStack(Material.LAVA_BUCKET, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(StringUtils.format(plugin.getConfig().getString("lava.display")));

        List<String> lore = plugin.getConfig().getStringList("lava.lore").stream().map(StringUtils::format).collect(Collectors.toList());

        meta.setLore(lore);

        meta.addEnchant(Enchantment.DURABILITY, 3, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        meta.getPersistentDataContainer().set(new NamespacedKey(plugin, "infinite"), PersistentDataType.INTEGER, 1);

        item.setItemMeta(meta);

        return item;
    }

}
