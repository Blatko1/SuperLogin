package Data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SavedIPs {

    private static HashMap<Player, String> map = new HashMap<>();

    public static void storeIP(Player p){
        map.put(p, p.getAddress().toString().substring(1));
    }

    public static String getIP(String name){
        return map.get(Bukkit.getPlayer(name));
    }

    public static void removeIP(Player p){
        map.remove(p);
    }

}
