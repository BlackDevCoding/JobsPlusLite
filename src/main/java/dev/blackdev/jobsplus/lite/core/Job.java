package dev.blackdev.jobsplus.lite.core;

import org.bukkit.Material;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Job {
    private final String id;
    private final String name;
    private final Map<ActionType, Map<Material, Reward>> rewards = new EnumMap<>(ActionType.class);

    public Job(String id, String name){ this.id=id; this.name=name; }
    public String getId(){ return id; }
    public String getName(){ return name; }
    public Map<ActionType, Map<Material, Reward>> getRewards(){ return rewards; }

    public void setReward(ActionType action, Material material, Reward reward){
        rewards.computeIfAbsent(action, a -> new LinkedHashMap<>()).put(material, reward);
    }
    public Reward getReward(ActionType action, Material mat){
        Map<Material, Reward> m = rewards.get(action);
        return m==null?null:m.get(mat);
    }
}
