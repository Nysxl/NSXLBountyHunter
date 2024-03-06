package com.internal.Nysxl.NSXLBountyHunter.Commands;

import com.internal.Nysxl.NSXLBountyHunter.Bounties.Bounties;
import com.internal.Nysxl.NSXLBountyHunter.main;
import com.internal.nysxl.NysxlUtilities.ItemBuilder.ItemFactory;
import com.internal.nysxl.NysxlUtilities.Utility.Commands.CommandInterface;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class setBounty implements CommandInterface {
    @Override
    public boolean onCommand(CommandSender commandSender, String[] strings) {
        if(commandSender instanceof Player player){

            //checks that there are at least 2 arguments
            if(strings.length == 2){

                //checks if any players match the first argument.
                Player targetPlayer = Bukkit.getOnlinePlayers().parallelStream().
                        filter(s->s.getDisplayName().equalsIgnoreCase(strings[0])).findFirst().orElse(null);

                //if there aren't any matching players return false.
                if(targetPlayer == null) return false;

                double reward;

                //attempts to parse a double from string[1].
                try {
                    reward = Double.parseDouble(strings[1]);
                } catch (NumberFormatException e) {
                    player.sendMessage("Invalid Number");
                    return false;
                }

                //adds a new active bounty.
                main.activeBounties.add(new Bounties(targetPlayer.getUniqueId(),
                        new ItemFactory(new ItemStack(Material.PLAYER_HEAD)).withSkullOfPlayer(targetPlayer),
                        reward));
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, String[] strings) {
        if(strings.length == 1){
            return Bukkit.getOnlinePlayers().parallelStream().map(Player::getDisplayName).collect(Collectors.toList());
        }

        if(strings.length == 2){
            return new ArrayList<>(Collections.singleton("<Reward for killing bounty>"));
        }
        return null;
    }

    @Override
    public boolean hasPermission(CommandSender commandSender) {
        return false;
    }
}
