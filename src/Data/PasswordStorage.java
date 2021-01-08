package Data;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class PasswordStorage {

    public static void storePassword(Player p, String password, NamespacedKey key){
        p.getPersistentDataContainer().set(key, PersistentDataType.STRING, password);
    }

}
