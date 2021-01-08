package Events;

import Data.PlayerDataManager;
import Data.SavedIPs;
import main.MainPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class DisconnectEvent implements Listener {

    @EventHandler
    public void onDisconnect(PlayerQuitEvent e){
        if(e.getPlayer().getWorld() != Bukkit.getWorld("spawn")){
            Player p = e.getPlayer();
            PlayerDataManager.storePlayerData(p, MainPlugin.getPlugin());
            SavedIPs.removeIP(p);
        }
    }
}
