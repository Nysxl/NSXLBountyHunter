package com.internal.Nysxl.NSXLBountyHunter.Listeners;

import com.internal.Nysxl.NSXLBountyHunter.Bounties.Bounties;
import com.internal.Nysxl.NSXLBountyHunter.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class playerDeath implements Listener {

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if(killer == null) return;

        //checks through all active bounties and returns
        // if the player who died has an active bounty.
        List<Bounties> b = main.activeBounties.parallelStream().filter(s->s.playerUUID().
                equals(player.getUniqueId())).collect(Collectors.toList());

        if(b.isEmpty()) return;

        for(Bounties bounty: b){
            bounty.claimBounty(player);
        }
    }







}
