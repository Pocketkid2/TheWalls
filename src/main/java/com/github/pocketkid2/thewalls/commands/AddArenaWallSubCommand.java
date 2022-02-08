package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.Status;
import com.github.pocketkid2.thewalls.TheWallsPlugin;
import com.github.pocketkid2.thewalls.TheWallsRegion;
import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.regions.Region;

public class AddArenaWallSubCommand extends TheWallsSubCommand {

	public AddArenaWallSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("addarenawall", "addwall", "aw");
	}

	@Override
	public String description() {
		return "Adds your currently selected WorldEdit region as an arena wall";
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
		return "addwall/aw <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		Arena arena = plugin.getGM().getArenaByName(args[0]);
		if (arena == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!"));
			return;
		}
		if ((arena.getStatus() != Status.INCOMPLETE) && (arena.getStatus() != Status.READY)) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "Please wait till the game is finished to modify it's values"));
			return;
		}
		if (arena.getArenaRegion() == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "Please define the arena region first before you add a wall!"));
			return;
		}
		LocalSession session = WorldEdit.getInstance().getSessionManager().getIfPresent(BukkitAdapter.adapt(player));
		if (session == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "Couldn't find WorldEdit session!"));
			return;
		}
		try {
			Region wallRegion = session.getSelection();
			Region arenaRegion = arena.getArenaRegion().getWorldEditRegion();
			if (!arenaRegion.contains(wallRegion.getMaximumPoint()) || !arenaRegion.contains(wallRegion.getMinimumPoint())) {
				player.sendMessage(plugin.addPrefix(ChatColor.RED + "The wall region can't be outside the arena region!"));
				return;
			}
			arena.addWallRegion(new TheWallsRegion(wallRegion.getBoundingBox()));
			player.sendMessage(plugin.addPrefix(ChatColor.AQUA + "The cuboid region you have selected has been added to the list of wall regions for " + ChatColor.GRAY + arena.getName()));
		} catch (IncompleteRegionException e) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "You haven't selected a WorldEdit region!"));
			return;
		}
	}

}
