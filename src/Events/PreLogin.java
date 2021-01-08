package Events;

import Data.SavedIPs;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PreLogin implements Listener {

    @EventHandler
    public void preLogin(AsyncPlayerPreLoginEvent e) {
        String ipAddres = e.getAddress().toString().substring(1);

        if(SavedIPs.getIP(e.getName()) != null){
            if(!ipAddres.equals(SavedIPs.getIP(e.getName()))){
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "Nema hackanja!");
                System.out.println(ipAddres + SavedIPs.getIP(e.getName()));
            }
        }

    }

}
