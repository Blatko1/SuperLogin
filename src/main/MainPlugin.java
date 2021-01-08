package main;

import Commands.LoginCommand;
import Commands.RegisterCommand;
import Commands.ResetPasswordCommand;
import Data.PasswordFunctions;
import Data.PlayerDataManager;
import Events.DisconnectEvent;
import Events.JoinEvent;
import Events.PreLogin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class MainPlugin extends JavaPlugin {

    private static JavaPlugin plugin;

    @Override
    public void onEnable(){
        plugin = this;
        this.getServer().getPluginManager().registerEvents(new PreLogin(), this);
        this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        this.getServer().getPluginManager().registerEvents(new DisconnectEvent(), this);
        this.getCommand("register").setExecutor(new RegisterCommand());
        this.getCommand("rpass").setExecutor(new ResetPasswordCommand());
        this.getCommand("login").setExecutor(new LoginCommand());

        PasswordFunctions.readyAlternateWorld();
    }

    @Override
    public void onDisable(){
        for(Player p: Bukkit.getOnlinePlayers()){
            PlayerDataManager.storePlayerData(p, this);
        }
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
