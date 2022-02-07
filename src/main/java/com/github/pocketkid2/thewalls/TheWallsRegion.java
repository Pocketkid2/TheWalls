package com.github.pocketkid2.thewalls;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.BlockVector;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.CuboidRegion;

public class TheWallsRegion implements ConfigurationSerializable {

	private World world;
	private BlockVector min;
	private BlockVector max;

	// Creates a region using the WorldEdit CuboidRegion class
	public TheWallsRegion(CuboidRegion region) {
		world = BukkitAdapter.adapt(region.getWorld());
		min = BukkitAdapter.adapt(world, region.getMinimumPoint()).toVector().toBlockVector();
		max = BukkitAdapter.adapt(world, region.getMaximumPoint()).toVector().toBlockVector();
	}

	// Creates a region using a serialized map (deserialize)
	public TheWallsRegion(Map<String, Object> map) {
		world = Bukkit.getWorld((String) map.get("world"));
		min = (BlockVector) map.get("min");
		max = (BlockVector) map.get("max");
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("world", world.toString());
		map.put("min", min);
		map.put("max", max);
		return map;
	}

	// Converts this region into a WorldEdit region
	public CuboidRegion getWorldEditRegion() {
		return new CuboidRegion(BukkitAdapter.adapt(world), BukkitAdapter.asBlockVector(min.toLocation(world)), BukkitAdapter.asBlockVector(max.toLocation(world)));
	}
}
