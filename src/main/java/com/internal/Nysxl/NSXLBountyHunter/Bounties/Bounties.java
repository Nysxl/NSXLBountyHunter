package com.internal.Nysxl.NSXLBountyHunter.Bounties;

import com.internal.Nysxl.NSXLBountyHunter.main;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public record Bounties(UUID playerUUID, ItemStack playerHead, double reward) {

    /**
     * claims the bounty for the player who killed the bounty.
     * the reward is taxed slightly.
     * @param killer the person who killed the player.
     */
    public void claimBounty(Player killer){
        main.balance.addBalance(killer.getUniqueId(), (this.reward() * 0.9));
        main.activeBounties.remove(this);
    }

    /**
     * adds a new active bounty.
     * @param b player the bounty will be set on.
     * @param p the player who set the bounty.
     * @param reward the amount that will be paid out when the bounty is claimed.
     */
    public void setBounty(Player b, Player p, double reward){
        if(main.balance.getPlayerBalance(p.getUniqueId()) >= reward){
            main.balance.removeBalance(p.getUniqueId(), reward);

            main.activeBounties.add(this);
        }
    }

}
