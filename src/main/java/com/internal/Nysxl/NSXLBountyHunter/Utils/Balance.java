package com.internal.Nysxl.NSXLBountyHunter.Utils;

import com.internal.Nysxl.NSXLBountyHunter.main;
import org.bukkit.Bukkit;

import java.util.UUID;

public class Balance {


    /**
     * returns a players balance.
     * @param playerUUID Player's balance to retrieve.
     * @return returns the players balance.
     */
    public double getPlayerBalance(UUID playerUUID){
        return main.getEconomy().getBalance(Bukkit.getPlayer(playerUUID));
    }

    /**
     * removes some of a players balance
     * @param playerUUID player whose balance is being reduced.
     * @param amount amount to remove from the player.
     * @return returns true if successful, false if unsuccessful.
     */
    public boolean removeBalance(UUID playerUUID, double amount){
        if(getPlayerBalance(playerUUID) < amount) return false;
        return main.getEconomy().withdrawPlayer(Bukkit.getPlayer(playerUUID), amount).transactionSuccess();
    }

    /**
     * deposits money into a players account
     * @param playerUUID player whose receiving the money
     * @param amount amount the player should receive.
     * @return returns true if successful, false if unsuccessful.
     */
    public boolean addBalance(UUID playerUUID, double amount){
        return main.getEconomy().depositPlayer(Bukkit.getPlayer(playerUUID), amount).transactionSuccess();
    }


}
