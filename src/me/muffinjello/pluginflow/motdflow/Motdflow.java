package me.muffinjello.pluginflow.motdflow;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Motdflow extends JavaPlugin implements Listener {
    public String Motd = "";
    @Override
    public void onEnable() {
        saveDefaultConfig();
        Motd = getConfig().getString("motd", Motd);
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("+motd") && sender.hasPermission("motdflow.use")){
            if(args.length < 1){
                sender.sendMessage(ChatColor.GRAY + "[MOTDFlow] Made by Muffinjello - Correct usage: ");
                sender.sendMessage(ChatColor.YELLOW + "/+motd set <NEW MOTD>");
                sender.sendMessage(ChatColor.YELLOW + "/+motd reload-config");
                sender.sendMessage(ChatColor.YELLOW + "/+motd view");
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
                        sender.sendMessage(ChatColor.GRAY + "[MOTDFlow] MOTD set: " + ChatColor.YELLOW + s);
                        return true;
                    } else if(args[0].equalsIgnoreCase("set") && args.length == 1){
                        sender.sendMessage(ChatColor.GRAY + "[MOTDFlow] Incorrect Usage, try: " + ChatColor.YELLOW + "/+motd set <message>");
                    }
                    else if(args[0].equalsIgnoreCase("view")){
                        sender.sendMessage(ChatColor.GRAY + "[MOTDFlow] The current MOTD is: ");
                        sender.sendMessage(ChatColor.YELLOW + "[Plain] " + Motd);
                        sender.sendMessage(ChatColor.YELLOW + "[Color] " + Motd.replaceAll("(&([a-f0-9]))", "§$2"));
                        return true;
                    }
                    else if(args[0].equalsIgnoreCase("reload-config")){
                        sender.sendMessage(ChatColor.GRAY + "[MOTDFlow] Reloaded MOTD from the config!");
                        this.reloadConfig();
                        Motd = this.getConfig().getString("motd", Motd);
                        return true;
                    }
                }
            }
        }
        return true;
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onServerListPing(ServerListPingEvent event){
        event.setMotd(Motd.replaceAll("(&([a-f0-9]))", "§$2"));
    }
}

