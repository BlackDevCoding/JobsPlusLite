package dev.blackdev.jobsplus.lite.core;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class PlayerDataStore {
    private final File dir;
    private final int maxJoinable;
    private final Map<UUID, YamlConfiguration> cache = new HashMap<>();

    public PlayerDataStore(File dir, int maxJoinable){
        this.dir = dir; this.maxJoinable = maxJoinable;
        if (!dir.exists()) dir.mkdirs();
    }
    private File file(UUID uuid){ return new File(dir, uuid.toString()+".yml"); }
    private YamlConfiguration load(UUID uuid){
        return cache.computeIfAbsent(uuid, id -> {
            YamlConfiguration yc = new YamlConfiguration();
            File f = file(id);
            try{ if (f.exists()) yc.load(f); }catch(Exception ignored){}
            return yc;
        });
    }
    public void save(UUID uuid){ try{ load(uuid).save(file(uuid)); }catch(IOException ignored){} }

    public Set<String> getJoinedJobs(OfflinePlayer p){
        java.util.List<String> list = load(p.getUniqueId()).getStringList("joined");
        return new java.util.LinkedHashSet<>(list);
    }
    public boolean join(OfflinePlayer p, String jobId){
        Set<String> joined = getJoinedJobs(p);
        if (joined.contains(jobId)) return true;
        if (joined.size() >= maxJoinable) return false;
        joined.add(jobId);
        load(p.getUniqueId()).set("joined", new java.util.ArrayList<>(joined));
        save(p.getUniqueId());
        return true;
    }
    public boolean leave(OfflinePlayer p, String jobId){
        Set<String> joined = getJoinedJobs(p);
        if (!joined.remove(jobId)) return false;
        load(p.getUniqueId()).set("joined", new java.util.ArrayList<>(joined));
        save(p.getUniqueId());
        return true;
    }
    public int addXp(OfflinePlayer p, String jobId, int amount){
        if (amount <= 0) return getXp(p, jobId);
        String path = "xp."+jobId;
        int xp = load(p.getUniqueId()).getInt(path, 0) + amount;
        load(p.getUniqueId()).set(path, xp);
        save(p.getUniqueId());
        return xp;
    }
    public int getXp(OfflinePlayer p, String jobId){
        return load(p.getUniqueId()).getInt("xp."+jobId, 0);
    }
    public int getLevel(OfflinePlayer p, String jobId){
        int xp = getXp(p, jobId);
        return 1 + Math.floorDiv(xp, 100);
    }
    public boolean getNotify(OfflinePlayer p){
        return load(p.getUniqueId()).getBoolean("notify", true);
    }
    public void setNotify(OfflinePlayer p, boolean val){
        load(p.getUniqueId()).set("notify", val);
        save(p.getUniqueId());
    }
}
