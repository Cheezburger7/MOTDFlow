package me.muffinjello.pluginflow.motdflow;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Motdflow extends JavaPlugin implements Listener {
    public String Motd = "";
    @Override
    public void onEnable() {
        PluginDescriptionFile pdf = this.getDescription();
        saveDefaultConfig();
        Motd = getConfig().getString("motd", Motd);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("motd") && sender.hasPermission("motd.use")){
            if(args.length < 1){
                sender.sendMessage(ChatColor.GRAY + "[Flow] Correct usage: ");
                sender.sendMessage(ChatColor.YELLOW + "/motd set <NEW MOTD>");
                sender.sendMessage(ChatColor.YELLOW + "/motd reload-config");
                sender.sendMessage(ChatColor.YELLOW + "/motd view");
            } else {
                if (args.length >= 1){
                    if(args[0].equalsIgnoreCase("set") && args.length > 1){
                        String s = "";
                        for (int x = 1; x < args.length; x++){
                            s = s + args[x] + " ";
                            Motd = s;
                            getConfig().set("motd", Motd);
                            this.saveConfig();
                        }
                        sender.sendMessage(ChatColor.GRAY + "[Flow] MOTD set: " + ChatColor.YELLOW + s);
                        return true;
                    }
                    else if(args[0].equalsIgnoreCase("view")){
                        sender.sendMessage(ChatColor.GRAY + "[Flow] The current MOTD is: " + ChatColor.YELLOW + Motd);
                        return true;
                    }
                    else if(args[0].equalsIgnoreCase("reload-config")){
                        sender.sendMessage(ChatColor.GRAY + "[Flow] Reloaded MOTD from the config!");
                        this.reloadConfig();
                        Motd = this.getConfig().getString("motd", Motd);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

