package dev.blackdev.jobsplus.lite.cmd;

import dev.blackdev.jobsplus.lite.JobsPlusLite;
import dev.blackdev.jobsplus.lite.core.Job;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class JobsCommandLite implements CommandExecutor, TabCompleter {
    private final JobsPlusLite plugin;
    public JobsCommandLite(JobsPlusLite plugin){ this.plugin = plugin; }
    private void msg(CommandSender s, String m){ s.sendMessage(ChatColor.GREEN + "[Jobs] " + ChatColor.RESET + m); }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (args.length == 0){
            msg(sender, "/jobs list, /jobs info <id>, /jobs join <id>, /jobs leave <id>, /jobs stats [id], /jobs notify [on|off], /jobs reload");
            return true;
        }
        String sub = args[0].toLowerCase(Locale.ROOT);
        switch(sub){
            case "list": {
                String list = plugin.getJobManager().getJobs().stream().map(Job::getId).collect(Collectors.joining(", "));
                msg(sender, "Available jobs: " + list);
                return true;
            }
            case "info": {
                if (args.length < 2){ msg(sender, "Usage: /jobs info <id>"); return true; }
                Job j = plugin.getJobManager().getJob(args[1]);
                if (j == null){ msg(sender, "Job not found."); return true; }
                msg(sender, ChatColor.AQUA + j.getName() + ChatColor.RESET + " (id: " + j.getId() + ")");
                msg(sender, "Rewards configured: " + j.getRewards().size() + " action types.");
                return true;
            }
            case "join": {
                if (!(sender instanceof Player)){ msg(sender, "Players only."); return true; }
                if (args.length < 2){ msg(sender, "Usage: /jobs join <id>"); return true; }
                Player p = (Player) sender;
                Job j = plugin.getJobManager().getJob(args[1]);
                if (j == null){ msg(sender, "Job not found."); return true; }
                if (!sender.hasPermission("jobsplus.use")){ msg(sender, "You lack permission."); return true; }
                boolean ok = plugin.getData().join(p, j.getId());
                if (!ok) msg(sender, "You're at your jobs limit.");
                else msg(sender, "Joined job: " + j.getName());
                return true;
            }
            case "leave": {
                if (!(sender instanceof Player)){ msg(sender, "Players only."); return true; }
                if (args.length < 2){ msg(sender, "Usage: /jobs leave <id>"); return true; }
                Player p = (Player) sender;
                boolean ok = plugin.getData().leave(p, args[1]);
                if (!ok) msg(sender, "You aren't in that job.");
                else msg(sender, "Left job: " + args[1]);
                return true;
            }
            case "stats": {
                if (!(sender instanceof Player)){ msg(sender, "Players only."); return true; }
                Player p = (Player) sender;
                if (args.length >= 2){
                    String id = args[1].toLowerCase(Locale.ROOT);
                    Job j = plugin.getJobManager().getJob(id);
                    if (j == null){ msg(sender, "Job not found."); return true; }
                    int xp = plugin.getData().getXp(p, id);
                    int lvl = plugin.getData().getLevel(p, id);
                    msg(sender, j.getName() + ": " + xp + " xp, level " + lvl);
                } else {
                    var joined = plugin.getData().getJoinedJobs(p);
                    if (joined.isEmpty()){ msg(sender, "You have no jobs."); return true; }
                    for (String id : joined){
                        Job j = plugin.getJobManager().getJob(id);
                        if (j == null) continue;
                        int xp = plugin.getData().getXp(p, id);
                        int lvl = plugin.getData().getLevel(p, id);
                        msg(sender, j.getName() + ": " + xp + " xp, level " + lvl);
                    }
                }
                return true;
            }
            case "notify": {
                if (!(sender instanceof Player)){ msg(sender, "Players only."); return true; }
                Player p = (Player) sender;
                if (args.length == 1){
                    boolean cur = plugin.getData().getNotify(p);
                    msg(sender, "Notify is " + (cur ? "on" : "off"));
                    return true;
                }
                String v = args[1].toLowerCase(Locale.ROOT);
                if (v.equals("on") || v.equals("true")) { plugin.getData().setNotify(p, true); msg(sender, "Notify set to on"); return true; }
                if (v.equals("off") || v.equals("false")) { plugin.getData().setNotify(p, false); msg(sender, "Notify set to off"); return true; }
                msg(sender, "Usage: /jobs notify [on|off]");
                return true;
            }
            case "reload": {
                if (!sender.hasPermission("jobsplus.admin")){ msg(sender, "No permission."); return true; }
                try{
                    plugin.reloadConfig();
                    msg(sender, "Reloaded config.");
                }catch(Exception ex){
                    msg(sender, "Reload failed: " + ex.getMessage());
                }
                return true;
            }
        }
        msg(sender, "Unknown subcommand.");
        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args){
        List<String> out = new ArrayList<>();
        if (args.length == 1){
            out.add("list"); out.add("info"); out.add("join"); out.add("leave"); out.add("stats"); out.add("notify"); out.add("reload");
        } else if (args.length == 2){
            String sub = args[0].toLowerCase(Locale.ROOT);
            if (sub.equals("info") || sub.equals("join") || sub.equals("leave") || sub.equals("stats")){
                out.addAll(plugin.getJobManager().getJobs().stream().map(Job::getId).collect(Collectors.toList()));
            } else if (sub.equals("notify")){
                out.add("on"); out.add("off");
            }
        }
        return out;
    }
}
