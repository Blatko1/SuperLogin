package Commands;

import Data.PasswordFunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetPasswordCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if(sender instanceof Player) {
            Player p = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);
            if(cmd.getName().equalsIgnoreCase("rpass")){

                if(args.length == 1){
                    if(PasswordFunctions.isRegistered(target)){
                        PasswordFunctions.resetPassword(target);
                        target.sendMessage(ChatColor.GREEN + "Password reset!");
                        target.kickPlayer("Reconnect to register!");
                        return true;
                    }
                    p.sendMessage(ChatColor.RED + "Player has no password!");
                    return true;
                }
                p.sendMessage(ChatColor.RED + "Invalid arguments!");
            }
        }

        else {
            sender.sendMessage(ChatColor.RED + "Hello CONSOLE");
        }
        return true;
    }
}
