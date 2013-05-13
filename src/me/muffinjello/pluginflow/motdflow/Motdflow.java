package me.muffinjello.pluginflow.motdflow;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Motdflow extends JavaPlugin implements Listener {
    public String Motd = "";
    @Override
    public void onEnable() {
        saveDefaultConfig();
        this.getConfig().getString("motd", Motd);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("motd")){
            if(args.length < 2){
                sender.sendMessage(ChatColor.GRAY + "[Flow] Correct usage: ");
                sender.sendMessage(ChatColor.YELLOW + "/motd set <NEW MOTD>");
                sender.sendMessage(ChatColor.YELLOW + "/motd reload-config");
                sender.sendMessage(ChatColor.YELLOW + "/motd view");
            } else {
                if (args.length >= 2){
                    if(args[0].equalsIgnoreCase("set")){
                        String s = "";
                        for (int x = 1; x < args.length; x++){
                            s = s + args[x] + " ";
                            Motd = s;
                            this.getConfig().set("motd", Motd);
                        }
                        sender.sendMessage(s);
                        return true;
                    }
                    else if(args[0].equalsIgnoreCase("view")){
                        sender.sendMessage(ChatColor.GRAY + "[Flow] The current MOTD is: " + ChatColor.YELLOW + Motd);
                    }
                    else if(args[0].equalsIgnoreCase("reload-config")){
                        sender.sendMessage(ChatColor.GRAY + "Reloaded MOTD from the config!");
                        this.getConfig().getString("motd", Motd);
                    }
                }
            }
        }
        return true;
    }
}

