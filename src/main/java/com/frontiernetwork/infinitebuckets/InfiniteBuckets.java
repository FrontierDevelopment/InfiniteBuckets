package com.frontiernetwork.infinitebuckets;

import com.frontiernetwork.infinitebuckets.commands.Commands;
import com.frontiernetwork.infinitebuckets.item.ItemEvents;
import com.frontiernetwork.infinitebuckets.item.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class InfiniteBuckets extends JavaPlugin {

    public static InfiniteBuckets instance;

    private ItemManager itemManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        itemManager = new ItemManager(this);

        super.getServer().getPluginCommand("infinitebuckets").setExecutor(new Commands(this));

        super.getServer().getPluginManager().registerEvents(new ItemEvents(this), this);

        super.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ItemManager getItemManager() { return itemManager; }
}
