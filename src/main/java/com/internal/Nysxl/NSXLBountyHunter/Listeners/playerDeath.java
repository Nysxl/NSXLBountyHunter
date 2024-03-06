package com.internal.Nysxl.NSXLBountyHunter.Listeners;

import com.internal.Nysxl.NSXLBountyHunter.Bounties.Bounties;
import com.internal.Nysxl.NSXLBountyHunter.Utils.Balance;
import com.internal.Nysxl.NSXLBountyHunter.main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class playerDeath implements Listener {

    @EventHandler
    public void PlayerDeath(PlayerDeathEvent e){
        Player player = e.getEntity();
        Player killer = e.getEntity().getKiller();
        if(killer == null) return;

        //checks through all active bounties and returns
        // if the player who died has an active bounty.
        Bounties b = main.activeBounties.parallelStream().filter(s->s.playerUUID().
                equals(player.getUniqueId())).findFirst().orElse(null);

        if(b == null) return;

        b.claimBounty(killer);
    }







}
