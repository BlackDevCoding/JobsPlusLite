package dev.blackdev.jobsplus.lite;

import dev.blackdev.jobsplus.lite.core.*;
import dev.blackdev.jobsplus.lite.listeners.BlockListener;
import dev.blackdev.jobsplus.lite.listeners.CropListener;
import dev.blackdev.jobsplus.lite.cmd.JobsCommandLite;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;

public class JobsPlusLite extends JavaPlugin {
    private JobManager jobManager;
    private PlayerDataStore data;
    private EconomyHook economyHook;
    private boolean rewardsMoneyEnabled;
    private boolean rewardsXpEnabled;

    @Override
    public void onEnable(){
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();
        File dataDir = new File(getDataFolder(), "playerdata");
        this.jobManager = new JobManager();
        this.data = new PlayerDataStore(dataDir, getConfig().getInt("jobs.max-joinable", 2));
        this.economyHook = new EconomyHook();
        this.economyHook.setup();
        this.rewardsMoneyEnabled = getConfig().getBoolean("rewards.enable-money", true);
        this.rewardsXpEnabled = getConfig().getBoolean("rewards.enable-xp", true);
        PrebuiltJobs.registerAll(jobManager);
        Bukkit.getPluginManager().registerEvents(new BlockListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CropListener(this), this);
        if (getCommand("jobs") != null){
            JobsCommandLite cmd = new JobsCommandLite(this);
            getCommand("jobs").setExecutor(cmd);
            getCommand("jobs").setTabCompleter(cmd);
        }
        getLogger().info("JobsPlusLite enabled with " + jobManager.getJobs().size() + " prebuilt jobs.");
    }

    public JobManager getJobManager(){ return jobManager; }
    public PlayerDataStore getData(){ return data; }
    public EconomyHook getEconomy(){ return economyHook; }
    public boolean isMoneyEnabled(){ return rewardsMoneyEnabled; }
    public boolean isXpEnabled(){ return rewardsXpEnabled; }
}
