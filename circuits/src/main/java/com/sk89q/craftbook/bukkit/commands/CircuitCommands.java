package com.sk89q.craftbook.bukkit.commands;

import com.sk89q.craftbook.bukkit.CircuitsPlugin;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.NestedCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CircuitCommands {

    CircuitsPlugin plugin;

    public CircuitCommands(CircuitsPlugin plugin) {

        this.plugin = plugin;
    }

    @Command(
            aliases = {"ic"},
            desc = "Commands to manage Craftbook IC's"
            )
    public void ic(CommandContext context, CommandSender sender) {

    }

    @Command(
            aliases = {"reloadics"},
            desc = "Reloads the IC config"
            )
    public void reload(CommandContext context, CommandSender sender) {

        plugin.reloadConfig();
        sender.sendMessage("The IC config has been reloaded.");
    }

    @Command(
            aliases = {"cbcircuits"},
            desc = "Handles the basic Craftbook Circuits commands."
            )
    @NestedCommand(NestedCommands.class)
    public void cbcircuits(CommandContext context, CommandSender sender) {

    }


    public static class NestedCommands {

        private final CircuitsPlugin plugin;

        public NestedCommands(CircuitsPlugin plugin) {

            this.plugin = plugin;
        }

        @Command(
                aliases = {"reload"},
                desc = "Reloads the craftbook circuits config"
                )
        @CommandPermissions("craftbook.circuit.reload")
        public void reload(CommandContext context, CommandSender sender) {

            plugin.getLocalConfiguration().reload();
            sender.sendMessage("Config has been reloaded successfully!");
        }
    }

    @Command(
            aliases = {"icdocs"},
            desc = "Documentation on CraftBook IC's",
            min = 1,
            max = 1
            )
    public void icdocs(CommandContext context, CommandSender sender) {

        if (!(sender instanceof Player)) return;
        Player player = (Player) sender;
        plugin.generateICDocs(player, context.getString(0));
    }

    @Command(
            aliases = {"listics"},
            desc = "List available IC's",
            min = 0,
            max = 1
            )
    public void listics(CommandContext context, CommandSender sender) {

        if (!(sender instanceof Player)) return;
        Player player = (Player) sender;
        String[] lines = plugin.generateICText(player, null);
        int pages = (lines.length - 1) / 9 + 1;
        int accessedPage;

        try {
            accessedPage = context.argsLength() < 1 ? 0 : context.getInteger(0) - 1;
            if (accessedPage < 0 || accessedPage >= pages) {
                player.sendMessage(ChatColor.RED + "Invalid page \"" + context.getInteger(0) + "\"");
                return;
            }
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Invalid page \"" + context.getInteger(0) + "\"");
            return;
        }

        player.sendMessage(ChatColor.BLUE + "  ");
        player.sendMessage(ChatColor.BLUE + "CraftBook ICs (Page " + (accessedPage + 1) + " of " + pages + "):");

        for (int i = accessedPage * 9; i < lines.length && i < (accessedPage + 1) * 9; i++) {
            player.sendMessage(lines[i]);
        }
    }

    @Command(
            aliases = {"searchics"},
            desc = "Search available IC's with names",
            min = 0,
            max = 2
            )
    public void searchics(CommandContext context, CommandSender sender) {

        if (!(sender instanceof Player)) return;
        Player player = (Player) sender;
        String[] lines = plugin.generateICText(player, context.getString(0));
        int pages = (lines.length - 1) / 9 + 1;
        int accessedPage;

        try {
            accessedPage = context.argsLength() < 2 ? 0 : context.getInteger(1) - 1;
            if (accessedPage < 0 || accessedPage >= pages) {
                player.sendMessage(ChatColor.RED + "Invalid page \"" + context.getInteger(1) + "\"");
                return;
            }
        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Invalid page \"" + context.getInteger(1) + "\"");
            return;
        }

        player.sendMessage(ChatColor.BLUE + "  ");
        player.sendMessage(ChatColor.BLUE + "CraftBook ICs \"" + context.getString(0) + "\" (Page " + (accessedPage + 1) + " of " + pages + "):");

        for (int i = accessedPage * 9; i < lines.length && i < (accessedPage + 1) * 9; i++) {
            player.sendMessage(lines[i]);
        }
    }
}