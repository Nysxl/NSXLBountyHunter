package com.internal.Nysxl.NSXLBountyHunter;

import com.internal.Nysxl.NSXLBountyHunter.Bounties.Bounties;
import com.internal.Nysxl.NSXLBountyHunter.Commands.BountyHunter;
import com.internal.Nysxl.NSXLBountyHunter.Commands.setBounty;
import com.internal.Nysxl.NSXLBountyHunter.ConfigManager.NSXLBountyHunterConfigManager;
import com.internal.Nysxl.NSXLBountyHunter.LanguageManager.LanguageManager;
import com.internal.Nysxl.NSXLBountyHunter.Utils.Balance;
import com.internal.nysxl.NysxlUtilities.CommandManager.CommandManager;
import com.internal.nysxl.NysxlUtilities.ConfigManager.ConfigManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class main extends JavaPlugin {

    public CommandManager commandManager = new CommandManager(this);
    public static ArrayList<Bounties> activeBounties = new ArrayList<>();
    private static Economy econ = null;
    public static Balance balance;
    private static ConfigManager cfgM;
    private static LanguageManager languageManager = new LanguageManager();

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        balance = new Balance();
        registerCommands();


        cfgM = new ConfigManager(this);

        languageManager.loadLanguage();

        NSXLBountyHunterConfigManager.loadBounties();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public void registerCommands(){
        commandManager.registerCommand("BountyHunter", new BountyHunter());
        commandManager.registerCommand("setBounty", new setBounty());
    }

    public static ConfigManager getCfgM() {
        return cfgM;
    }

    public static LanguageManager getLanguageManager() {
        return languageManager;
    }
}