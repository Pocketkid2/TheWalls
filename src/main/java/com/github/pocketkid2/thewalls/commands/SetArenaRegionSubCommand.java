package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;
import com.github.pocketkid2.thewalls.TheWallsRegion;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;

public class SetArenaRegionSubCommand extends TheWallsSubCommand {

	public SetArenaRegionSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("setarenaregion", "setregion", "sr");
	}

	@Override
	public String description() {
		return "Sets your currently selected WorldEdit region to the arena region";
	}

	@Override
	public int minArguments() {
		return 1;
	}

	@Override
	public int maxArguments() {
		return 1;
	}

	@Override
	public boolean isAdminCommand() {
		return true;
	}

	@Override
	public String usageMessage() {
		return "setregion/sr <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		Arena arena = plugin.getGM().getArenaByName(args[0]);
		if (arena == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!"));
			return;
		}
		LocalSession session = WorldEdit.getInstance().getSessionManager().getIfPresent(BukkitAdapter.adapt(player));
		if (session == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "Couldn't find WorldEdit session!"));
			return;
		}
		try {
			Region region = session.getSelection();
			arena.setArenaRegion(new TheWallsRegion(region.getBoundingBox()));
			player.sendMessage(plugin.addPrefix(ChatColor.AQUA + "The cuboid region you have selected is now the arena region for " + ChatColor.GRAY + arena.getName()));
		} catch (IncompleteRegionException e) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "You haven't selected a WorldEdit region!"));
			return;
		}
	}

}
