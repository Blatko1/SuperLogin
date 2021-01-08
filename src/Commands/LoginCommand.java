package Commands;

import Data.PasswordFunctions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("login")){
                if(PasswordFunctions.isRegistered(p)){
                    if(args.length == 1 && (args[0].equals(PasswordFunctions.getPlayerPassword(p)))) {
                        p.sendMessage(ChatColor.GREEN + "You are logging in!");
                        PasswordFunctions.loginPlayer(p);
                        return true;
                    }
                    p.sendMessage(ChatColor.RED + "Invalid password");
                    return true;
                }
                p.sendMessage(ChatColor.RED + "Not registered!");
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + "Hello CONSOLE");
        }

        return true;

    }

}
