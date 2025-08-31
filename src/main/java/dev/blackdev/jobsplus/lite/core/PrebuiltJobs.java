package dev.blackdev.jobsplus.lite.core;

import org.bukkit.Material;

public class PrebuiltJobs {
    public static void registerAll(JobManager jm){
        Job tree = new Job("treechopper", "Tree Chopper");
        for (Material m : new Material[]{ Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.MANGROVE_LOG, Material.CHERRY_LOG })
            tree.setReward(ActionType.BREAK, m, new Reward(2.0, 4));
        jm.register(tree);

        Job farmer = new Job("farmer", "Farmer");
        for (Material m : new Material[]{ Material.WHEAT, Material.CARROTS, Material.POTATOES, Material.BEETROOTS })
            farmer.setReward(ActionType.HARVEST, m, new Reward(1.0, 3));
        jm.register(farmer);

        Job miner = new Job("miner", "Miner");
        jm.register(miner);
        miner.setReward(ActionType.BREAK, Material.COAL_ORE, new Reward(1.0, 3));
        miner.setReward(ActionType.BREAK, Material.IRON_ORE, new Reward(2.0, 6));
        miner.setReward(ActionType.BREAK, Material.COPPER_ORE, new Reward(1.5, 5));
        miner.setReward(ActionType.BREAK, Material.GOLD_ORE, new Reward(4.0, 10));
        miner.setReward(ActionType.BREAK, Material.DIAMOND_ORE, new Reward(8.0, 20));
        miner.setReward(ActionType.BREAK, Material.EMERALD_ORE, new Reward(12.0, 28));

        Job stone = new Job("stoneworker", "Stone Worker");
        for (Material m : new Material[]{ Material.STONE, Material.DEEPSLATE, Material.COBBLESTONE, Material.ANDESITE, Material.DIORITE, Material.GRANITE })
            stone.setReward(ActionType.BREAK, m, new Reward(0.6, 1));
        jm.register(stone);

        Job sand = new Job("sanddigger", "Sand Digger");
        for (Material m : new Material[]{ Material.SAND, Material.RED_SAND, Material.GRAVEL })
            sand.setReward(ActionType.BREAK, m, new Reward(0.4, 1));
        jm.register(sand);

        Job nether = new Job("netherfarmer", "Nether Farmer");
        nether.setReward(ActionType.HARVEST, Material.NETHER_WART, new Reward(1.5, 4));
        jm.register(nether);

        Job berry = new Job("berrypicker", "Berry Picker");
        berry.setReward(ActionType.HARVEST, Material.SWEET_BERRY_BUSH, new Reward(0.8, 2));
        jm.register(berry);

        Job cocoa = new Job("cocoafarmer", "Cocoa Farmer");
        cocoa.setReward(ActionType.HARVEST, Material.COCOA, new Reward(1.2, 3));
        jm.register(cocoa);

        Job deep = new Job("deepslateminer", "Deepslate Miner");
        for (Material m : new Material[]{ Material.DEEPSLATE, Material.TUFF, Material.BASALT })
            deep.setReward(ActionType.BREAK, m, new Reward(0.9, 2));
        jm.register(deep);

        Job quarry = new Job("quarryman", "Quarryman");
        for (Material m : new Material[]{ Material.STONE_BRICKS, Material.CRACKED_STONE_BRICKS, Material.MOSSY_STONE_BRICKS, Material.CHISELED_STONE_BRICKS })
            quarry.setReward(ActionType.BREAK, m, new Reward(0.7, 2));
        jm.register(quarry);
    }
}
