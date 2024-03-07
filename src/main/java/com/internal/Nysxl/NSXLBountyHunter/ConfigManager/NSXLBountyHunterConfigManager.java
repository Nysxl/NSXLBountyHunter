package com.internal.Nysxl.NSXLBountyHunter.ConfigManager;

import com.internal.Nysxl.NSXLBountyHunter.Bounties.Bounties;
import com.internal.Nysxl.NSXLBountyHunter.main;
import com.internal.nysxl.NysxlUtilities.ConfigManager.ConfigManager;
import com.internal.nysxl.NysxlUtilities.ConfigManager.Utils.SerializationUtility;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NSXLBountyHunterConfigManager {

    private ConfigManager configManager;

    public NSXLBountyHunterConfigManager(Plugin plugin){
        configManager = new ConfigManager(plugin);
    }

    public void saveBounties() {
        List<Map<String, Object>> serializedBounties = main.activeBounties.stream().
                map(Bounties::serialize).toList();

        Bukkit.broadcastMessage(serializedBounties.size()+"");

        configManager.getConfig("bounties.yml").set("bounties", serializedBounties);
        configManager.saveConfig("bounties.yml");
    }

    public void saveBounty(Bounties bounty) {

        Map<String, Object> serializedBounty = bounty.serialize();

        FileConfiguration config = configManager.getConfig("bounties.yml");
        List<Map<?, ?>> existingBounties = config.getMapList("bounties");

        if (existingBounties.isEmpty()) {
            existingBounties = new ArrayList<>();
        }

        existingBounties.add(serializedBounty);

        config.set("bounties", existingBounties);
        configManager.saveConfig("bounties.yml");
    }

    public void loadBounties(){
        FileConfiguration config = configManager.getConfig("bounties.yml");
        List<Map<?,?>> serializedBounties = config.getMapList("bounties");

        main.activeBounties = (ArrayList<Bounties>) serializedBounties.stream().
                map(map -> Bounties.deserialize((Map<String, Object>) map)).
                collect(Collectors.toList());
    }

}
