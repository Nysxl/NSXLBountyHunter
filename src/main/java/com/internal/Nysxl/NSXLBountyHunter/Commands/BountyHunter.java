package com.internal.Nysxl.NSXLBountyHunter.Commands;

import com.internal.Nysxl.NSXLBountyHunter.BountyGUI.BountyGUI;
import com.internal.nysxl.NysxlUtilities.Utility.Commands.CommandInterface;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class BountyHunter implements CommandInterface {
    @Override
    public boolean onCommand(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof Player player){
            if(strings.length == 0){
                new BountyGUI(player);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        return null;
    }

    @Override
    public boolean hasPermission(CommandSender commandSender) {
        return false;
    }
}
