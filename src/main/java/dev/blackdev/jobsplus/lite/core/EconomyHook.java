package dev.blackdev.jobsplus.lite.core;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public class EconomyHook {
    private Economy economy;
    public void setup(){
        try{
            if (Bukkit.getPluginManager().getPlugin("Vault") == null) return;
            RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
            if (rsp == null) return;
            economy = rsp.getProvider();
        }catch(Throwable t){ economy = null; }
    }
    public boolean isReady(){ return economy != null; }
    public void deposit(org.bukkit.OfflinePlayer p, double amount){
        if (!isReady() || amount <= 0) return;
        try{ economy.depositPlayer(p, amount); }catch(Throwable ignored){}
    }
}
