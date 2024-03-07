package com.internal.Nysxl.NSXLBountyHunter.Bounties;

import com.internal.Nysxl.NSXLBountyHunter.ConfigManager.NSXLBountyHunterConfigManager;
import com.internal.Nysxl.NSXLBountyHunter.LanguageManager.Language;
import com.internal.Nysxl.NSXLBountyHunter.LanguageManager.LanguageManager;
import com.internal.Nysxl.NSXLBountyHunter.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
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

        String message = main.getLanguageManager().getMessage(Language.CLAIMED_BOUNTY);
        message = LanguageManager.parsePlaceHolder(message, "%player%", Bukkit.getPlayer(playerUUID).getDisplayName());
        message = LanguageManager.parsePlaceHolder(message, "%reward%", reward+"");

        killer.sendMessage(message);
        NSXLBountyHunterConfigManager.saveBounties();
    }

    /**
     * adds a new active bounty.
     * @param b player the bounty will be set on.
     * @param p the player who set the bounty.
     * @param reward the amount that will be paid out when the bounty is claimed.
     */
    public void setBounty(Player p, double reward){
        if(main.balance.getPlayerBalance(p.getUniqueId()) >= reward){
            main.balance.removeBalance(p.getUniqueId(), reward);

            main.activeBounties.add(this);

            String message = main.getLanguageManager().getMessage(Language.BOUNTY_SET_MESSAGE);
            message = LanguageManager.parsePlaceHolder(message, "%player%", Bukkit.getPlayer(playerUUID).getDisplayName());
            message = LanguageManager.parsePlaceHolder(message, "%reward%", reward+"");

            p.sendMessage(message);

            NSXLBountyHunterConfigManager.saveBounty(this);
        }
    }

    public Map<String, Object> serialize() {
        Map<String, Object> serialized = new HashMap<>();
        serialized.put("UUID", this.playerUUID.toString());
        serialized.put("HeadItemStack", this.playerHead.serialize());
        serialized.put("reward", this.reward);
        return serialized;
    }

    // Static method to deserialize a Bounties object, if not already defined
    public static Bounties deserialize(Map<String, Object> map) {
        UUID uuid = UUID.fromString((String) map.get("UUID"));
        ItemStack head = ItemStack.deserialize((Map<String, Object>) map.get("HeadItemStack"));
        double reward = (double) map.get("reward");
        return new Bounties(uuid, head, reward);
    }
}
