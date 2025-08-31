package dev.blackdev.jobsplus.lite.listeners;

import dev.blackdev.jobsplus.lite.JobsPlusLite;
import dev.blackdev.jobsplus.lite.core.ActionType;
import dev.blackdev.jobsplus.lite.core.Job;
import dev.blackdev.jobsplus.lite.core.Reward;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class CropListener implements Listener {
    private final JobsPlusLite plugin;
    public CropListener(JobsPlusLite plugin){ this.plugin = plugin; }
    private boolean isMature(Block b){
        if (!(b.getBlockData() instanceof Ageable)) return false;
        Ageable a = (Ageable) b.getBlockData();
        return a.getAge() >= a.getMaximumAge();
    }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Block b = e.getBlock();
        if (!isMature(b)) return;
        Player p = e.getPlayer();
        Material m = b.getType();
        for (String jobId : plugin.getData().getJoinedJobs(p)){
            Job j = plugin.getJobManager().getJob(jobId);
            if (j == null) continue;
            Reward r = j.getReward(ActionType.HARVEST, m);
            if (r != null){
                if (plugin.isXpEnabled() && r.xp > 0) plugin.getData().addXp(p, jobId, r.xp);
                if (plugin.isMoneyEnabled() && r.money > 0) plugin.getEconomy().deposit(p, r.money);
                if (plugin.getConfig().getBoolean("ui.enable-actionbar", true) && plugin.getData().getNotify(p)){
                    String msg = "";
                    if (plugin.isMoneyEnabled() && r.money > 0) msg += "+$" + r.money;
                    if (plugin.isXpEnabled() && r.xp > 0) msg += (msg.isEmpty() ? "" : " ") + "+" + r.xp + "xp";
                    if (!msg.isEmpty()) p.sendActionBar(Component.text(msg));
                }
            }
        }
    }
}
