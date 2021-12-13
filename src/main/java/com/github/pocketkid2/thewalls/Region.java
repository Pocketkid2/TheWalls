package com.github.pocketkid2.thewalls;

import java.util.Map;

import org.bukkit.World;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.util.BlockVector;

public class Region implements ConfigurationSerializable {

	private World world;
	private BlockVector min;
	private BlockVector max;

	public Region() {

	}

	@Override
	public Map<String, Object> serialize() {
		// TODO Auto-generated method stub
		return null;
	}
}
