package com.frontiernetwork.infinitebuckets.commands;

import com.frontiernetwork.infinitebuckets.InfiniteBuckets;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Commands implements CommandExecutor, TabCompleter {

    private final InfiniteBuckets plugin;

    public Commands(InfiniteBuckets plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (cmd.getName().equalsIgnoreCase("infinitebuckets")) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.AQUA + "InfiniteBuckets Usage:");
                    sender.sendMessage(ChatColor.RED + "/infinitebuckets give <player> <water | lava>");
                    sender.sendMessage(ChatColor.RED + "/infinitebuckets reload");
                } else {
                    if (args.length >= 1) {
                        if (args[0].equalsIgnoreCase("reload")) {
                            plugin.reloadConfig();
                            sender.sendMessage(ChatColor.GREEN + "Configuration Reloaded.");
                            return true;
                        } else if (args[0].equalsIgnoreCase("give")) {
                            if (args.length >= 2) {
                                String playerName = args[1];
                                Player target = sender.getServer().getPlayerExact(playerName);
                                if (target == null) {
                                    sender.sendMessage(ChatColor.RED + playerName + " is not online.");
                                    return true;
                                } else {
                                    if (args.length == 3) {
                                        if (args[2].equalsIgnoreCase("water")) {
                                            sender.sendMessage(ChatColor.GOLD + playerName + " has been given an Infinite Water Bucket");
                                            target.sendMessage(ChatColor.GREEN + "You have received an Infinite Water Bucket!");
                                            target.getInventory().addItem(plugin.getItemManager().infiniteWaterBucket());
                                        } else if (args[2].equalsIgnoreCase("lava")) {
                                            sender.sendMessage(ChatColor.GOLD + playerName + " has been given an Infinite Lava Bucket");
                                            target.sendMessage(ChatColor.GREEN + "You have received an Infinite Lava Bucket!");
                                            target.getInventory().addItem(plugin.getItemManager().infiniteLavaBucket());
                                        } else {
                                            sender.sendMessage(ChatColor.RED + "Invalid bucket type: " + args[2]);
                                            sender.sendMessage(ChatColor.RED + "Available options are: water, lava");
                                        }
                                    } else {
                                        sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "USAGE: " + ChatColor.RESET + "" + ChatColor.RED + "/infinitebuckets give <player> <water | lava>");
                                    }
                                }
                                return true;
                            } else {
                                sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "USAGE: " + ChatColor.RESET + "" + ChatColor.RED + "/infinitebuckets give <player> <water | lava>");
                            }
                        } else {
                            sender.sendMessage(ChatColor.AQUA + "InfiniteBuckets Usage:");
                            sender.sendMessage(ChatColor.RED + "/infinitebuckets give <player> <water | lava>");
                            sender.sendMessage(ChatColor.RED + "/infinitebuckets reload");
                        }
                    }
                }
            }
        } else {
            Player player = (Player) sender;
            if (player.hasPermission("infbuckets.admin")) {
                if (cmd.getName().equalsIgnoreCase("infinitebuckets")) {
                    if (args.length == 0) {
                        sender.sendMessage(ChatColor.AQUA + "InfiniteBuckets Usage:");
                        sender.sendMessage(ChatColor.RED + "/infinitebuckets give <player> <water | lava>");
                        sender.sendMessage(ChatColor.RED + "/infinitebuckets reload");
                    } else {
                        if (args.length >= 1) {
                            if (args[0].equalsIgnoreCase("reload")) {
                                plugin.reloadConfig();
                                sender.sendMessage(ChatColor.GREEN + "Configuration Reloaded.");
                                return true;
                            } else if (args[0].equalsIgnoreCase("give")) {
                                if (args.length >= 2) {
                                    String playerName = args[1];
                                    Player target = sender.getServer().getPlayerExact(playerName);
                                    if (target == null) {
                                        sender.sendMessage(ChatColor.RED + playerName + " is not online.");
                                        return true;
                                    } else {
                                        if (args.length == 3) {
                                            if (args[2].equalsIgnoreCase("water")) {
                                                sender.sendMessage(ChatColor.GOLD + playerName + " has been given an Infinite Water Bucket");
                                                target.sendMessage(ChatColor.GREEN + "You have received an Infinite Water Bucket!");
                                                target.getInventory().addItem(plugin.getItemManager().infiniteWaterBucket());
                                            } else if (args[2].equalsIgnoreCase("lava")) {
                                                sender.sendMessage(ChatColor.GOLD + playerName + " has been given an Infinite Lava Bucket");
                                                target.sendMessage(ChatColor.GREEN + "You have received an Infinite Lava Bucket!");
                                                target.getInventory().addItem(plugin.getItemManager().infiniteLavaBucket());
                                            } else {
                                                sender.sendMessage(ChatColor.RED + "Invalid bucket type: " + args[2]);
                                                sender.sendMessage(ChatColor.RED + "Available options are: water, lava");
                                            }
                                        } else {
                                            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "USAGE: " + ChatColor.RESET + "" + ChatColor.RED + "/infinitebuckets give <player> <water | lava>");
                                        }
                                    }
                                    return true;
                                } else {
                                    sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "USAGE: " + ChatColor.RESET + "" + ChatColor.RED + "/infinitebuckets give <player> <water | lava>");
                                }
                            } else {
                                sender.sendMessage(ChatColor.AQUA + "InfiniteBuckets Usage:");
                                sender.sendMessage(ChatColor.RED + "/infinitebuckets give <player> <water | lava>");
                                sender.sendMessage(ChatColor.RED + "/infinitebuckets reload");
                            }
                        }
                    }
                }
            } else {
                sender.sendMessage(ChatColor.BOLD + "" + ChatColor.RED + "HEY! " + ChatColor.RED + "You don't have permission for that.");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (sender instanceof Player) {
            if (args.length == 1) {
                List<String> Commands = new ArrayList<>();
                Commands.add("give"); Commands.add("reload");
                final List<String> completions = new ArrayList<>();
                StringUtil.copyPartialMatches(args[0], Commands, completions);
                Collections.sort(completions);
                return completions;
            }
            if (args.length == 3) {
                List<String> BucketType = new ArrayList<>();
                BucketType.add("water"); BucketType.add("lava");
                final List<String> completions = new ArrayList<>();
                StringUtil.copyPartialMatches(args[2], BucketType, completions);
                Collections.sort(completions);
                return completions;
            }
        }
        return null;
    }
}
