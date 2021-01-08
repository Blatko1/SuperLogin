package Commands;

import Data.PasswordFunctions;
import Data.PasswordStorage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(cmd.getName().equals("register")){
                if(!PasswordFunctions.isRegistered(p)){
                    if(args.length == 2){
                        if(args[0].equals(args[1])){
                            PasswordFunctions.createPassword(p, args[1]);
                            p.sendMessage(ChatColor.GREEN + "Registering!");
                            p.kickPlayer("Reconnect to login!");
                            return true;
                        }
                        p.sendMessage(ChatColor.RED + "Passwords aren't same!");
                        return true;
                    }
                    p.sendMessage(ChatColor.RED + "Usage: /register <password> <confirm password>.");
                    return true;
                }
                p.sendMessage(ChatColor.RED + "You are already registered!");
                return true;
            }
        }

        else {
            sender.sendMessage(ChatColor.RED + "Hello CONSOLE!");
            return true;
        }
        return false;
    }
}
