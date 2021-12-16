package com.github.pocketkid2.thewalls;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;

public class Utils {
	public static final List<Material> SIGN_TYPES = Arrays.asList(Material.values()).stream().filter(m -> m.toString().contains("SIGN")).collect(Collectors.toList());
}
