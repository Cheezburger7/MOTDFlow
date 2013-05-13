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
    public void onDisable() {
        this.getConfig().set("motd", Motd);
    }

    public void onEnable() {
        saveDefaultConfig();
        this.getConfig().getString("motd", Motd);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("motd")){
            if(args.length < 1){
                sender.sendMessage(ChatColor.GRAY + "[Flow] Correct usage: " + ChatColor.YELLOW + "/motd <Message>");
            } else {
                if (args.length >= 1){
                    String s = "";
                    for (int x = 0; x < args.length; x++){
                        s = s + args[x] + " ";
                    }
                    sender.sendMessage(s);
                }
            }
        }
        return true;
    }
}

