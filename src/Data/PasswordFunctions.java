package Data;

import main.MainPlugin;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class PasswordFunctions {

    private static HashMap<Player, Integer> map = new HashMap<>();

    private static final NamespacedKey key = new NamespacedKey(MainPlugin.getPlugin(), "password");

    public static boolean isRegistered(Player p){
        return p.getPersistentDataContainer().has(key, PersistentDataType.STRING);
    }

    public static void createPassword(Player p, String password){
        PasswordStorage.storePassword(p, password, key);
        Bukkit.getScheduler().cancelTask(map.get(p));

    }

    public static void loginPlayer(Player p){
        Bukkit.getScheduler().cancelTask(map.get(p));
        SavedIPs.storeIP(p);
        PlayerDataManager.loadBackPlayer(p, MainPlugin.getPlugin());
    }

    public static void askForLogin(Player p, String message){
        p.teleport(new Location(Bukkit.getWorld("spawn"),0,4,0));
        p.getInventory().clear();
        int i = Bukkit.getScheduler().scheduleSyncRepeatingTask(MainPlugin.getPlugin(), new Runnable() {
            private String m = message;
            @Override
            public void run() {
                p.teleport(new Location(Bukkit.getWorld("spawn"),0,4,0));
                p.sendMessage(m);
                if(Bukkit.getPlayer(p.getName()) == null){
                    Bukkit.getScheduler().cancelTask(map.get(p));
                }
            }
        }, 5, 5);
        map.put(p, i);
    }

    public static void readyAlternateWorld(){
        World world = Bukkit.getWorld("spawn");
        if(world == null){
            WorldCreator wc = new WorldCreator("spawn");
            wc.environment(World.Environment.NORMAL);
            wc.type(WorldType.FLAT);

            world = wc.createWorld();
        }
        world.setDifficulty(Difficulty.PEACEFUL);
    }

    public static void resetPassword(Player p){
        p.getPersistentDataContainer().remove(key);
    }

    public static String getPlayerPassword(Player p) {
        return p.getPersistentDataContainer().get(key, PersistentDataType.STRING);
    }
}
