package Data;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.util.Objects;

public class PlayerDataManager {

    public static void loadBackPlayer(Player p, JavaPlugin plugin) {
        // Delete player inventory.
        p.getInventory().clear();
        p.setExp(0);
        for (PotionEffect pe : p.getActivePotionEffects()) {
            p.removePotionEffect(pe.getType());
        }
        p.setGameMode(GameMode.SURVIVAL);

        // Player position.
        String pos = null;
        try {
            pos = Objects.requireNonNull(plugin.getConfig().get(p.getUniqueId().toString() + ".location")).toString();
        } catch (Exception e) {
            System.err.println(ChatColor.RED + "Player last location doesn't exist!\n It will be created on player disconnect!");
        }
        if (pos == null) {
            p.teleport(new Location(Bukkit.getWorld("world"), Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation().getX(), Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation().getY(), Objects.requireNonNull(Bukkit.getWorld("world")).getSpawnLocation().getZ()));
        } else {
            World world = Bukkit.getWorld(pos.substring(pos.indexOf("name=") + 5, pos.indexOf("},x=")));
            double posX = Double.parseDouble(pos.substring(pos.indexOf(",x=") + 3, pos.indexOf(",y=")));
            double posY = Double.parseDouble(pos.substring(pos.indexOf(",y=") + 3, pos.indexOf(",z=")));
            double posZ = Double.parseDouble(pos.substring(pos.indexOf(",z=") + 3, pos.indexOf(",pitch=")));
            float yaw = Float.parseFloat(pos.substring(pos.indexOf(",pitch=") + 7, pos.indexOf(",yaw=")));
            float pitch = Float.parseFloat(pos.substring(pos.indexOf(",yaw=") + 5, pos.length() - 1));
            Location loc = new Location(world, posX, posY, posZ, pitch, yaw);
            p.teleport(loc);

            // Player inventory.
            String[] keys = plugin.getConfig().getString(p.getUniqueId().toString() + ".inventory.positions").split(" ");
            if (!keys[0].isEmpty()) {
                for (String s : keys) {
                    p.getInventory().setItem(Integer.parseInt(s), plugin.getConfig().getItemStack(p.getUniqueId().toString() + ".inventory." + Integer.parseInt(s)));
                }
            }

            // Player properties
            p.setHealth(plugin.getConfig().getDouble(p.getUniqueId().toString() + ".properties.health"));
            p.setFoodLevel(plugin.getConfig().getInt(p.getUniqueId().toString() + ".properties.hunger"));
            p.setExp((float) plugin.getConfig().getDouble(p.getUniqueId().toString() + ".properties.exp"));
        }
    }

    public static void storePlayerData(Player p, JavaPlugin plugin){
        saveInventory(p, plugin);
        savePosition(p, plugin);
        saveProperties(p, plugin);

        plugin.saveConfig();
    }

    private static void saveInventory(Player p, JavaPlugin plugin){
        StringBuilder str = new StringBuilder();
        plugin.getConfig().set(p.getUniqueId().toString() + ".inventory", null);
        for(int i = 0;i < p.getInventory().getSize();i++){
            if(p.getInventory().getItem(i) != null) {
                plugin.getConfig().set(p.getUniqueId().toString() + ".inventory." + i, p.getInventory().getItem(i));
                str.append(i).append(" ");
            }
        }
        if(str.length() != 0){
            str.substring(0, str.length()-1);
        }
        plugin.getConfig().set(p.getUniqueId().toString() + ".inventory.positions", str.toString());
    }

    private static void savePosition(Player p, JavaPlugin plugin){
        plugin.getConfig().set(p.getUniqueId().toString() + ".location", p.getLocation().toString());
    }

    private static void saveProperties(Player p, JavaPlugin plugin){
        plugin.getConfig().set(p.getUniqueId().toString() + ".properties." + "health", p.getHealth());
        plugin.getConfig().set(p.getUniqueId().toString() + ".properties." + "hunger", p.getFoodLevel());
        plugin.getConfig().set(p.getUniqueId().toString() + ".properties." + "exp", p.getExp());
    }

}
