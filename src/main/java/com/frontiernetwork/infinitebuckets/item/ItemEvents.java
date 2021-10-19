package com.frontiernetwork.infinitebuckets.item;

import com.frontiernetwork.infinitebuckets.InfiniteBuckets;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemEvents implements Listener {

    private final InfiniteBuckets plugin;

    public ItemEvents(InfiniteBuckets plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerBucket(PlayerBucketEmptyEvent e)
    {
        Player player = e.getPlayer();
        Block b = e.getBlock();

        int x = e.getBlock().getX();
        int y = e.getBlock().getY();
        int z = e.getBlock().getZ();

        ItemStack bucket = player.getInventory().getItemInMainHand();

        if (!bucket.hasItemMeta()) return;

        NamespacedKey infinite = new NamespacedKey(plugin, "infinite");
        PersistentDataContainer container = bucket.getItemMeta().getPersistentDataContainer();

        // Checks if bucket is not an infinite bucket
        if (!container.has(infinite, PersistentDataType.INTEGER)) return;

        //Check if block can be waterlogged
        if (container.get(infinite, PersistentDataType.INTEGER) == 0) {
            if(b.getBlockData() instanceof Waterlogged){
                Waterlogged wl = (Waterlogged) b.getBlockData();
                wl.setWaterlogged(true);
                b.setBlockData(wl);
            } else {
                player.getWorld().getBlockAt(x, y, z).setType(Material.WATER);
            }
            e.setCancelled(true);
        }
    }
}
