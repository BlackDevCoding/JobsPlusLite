package dev.blackdev.jobsplus.lite.listeners;

import dev.blackdev.jobsplus.lite.JobsPlusLite;
import dev.blackdev.jobsplus.lite.core.ActionType;
import dev.blackdev.jobsplus.lite.core.Job;
import dev.blackdev.jobsplus.lite.core.Reward;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockListener implements Listener {
    private final JobsPlusLite plugin;
    public BlockListener(JobsPlusLite plugin){ this.plugin = plugin; }
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        Material mat = e.getBlock().getType();
        boolean isLog = Tag.LOGS.isTagged(mat);
        for (String jobId : plugin.getData().getJoinedJobs(p)){
            Job j = plugin.getJobManager().getJob(jobId);
            if (j == null) continue;
            Reward r = j.getReward(ActionType.BREAK, mat);
            if (r == null && isLog) r = j.getReward(ActionType.BREAK, Material.OAK_LOG);
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
