package Events;

import Data.PasswordFunctions;
import Data.PlayerDataManager;
import main.MainPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    String messageRegister = ChatColor.GOLD + "Type " + ChatColor.GREEN + "/register <password> <confirm password>" + ChatColor.GOLD + " to register!";
    String messageLogin = ChatColor.GOLD + "Type " + ChatColor.GREEN + "/login <password> " + ChatColor.GOLD + " to login!";

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(PasswordFunctions.isRegistered(p)){
            PasswordFunctions.askForLogin(p, messageLogin);
        }
        else{
            if(p.getWorld() != Bukkit.getWorld("spawn")){
                PlayerDataManager.storePlayerData(p, MainPlugin.getPlugin());
            }
            PasswordFunctions.askForLogin(p, messageRegister);
        }
    }

}
