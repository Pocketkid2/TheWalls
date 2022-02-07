package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;
import com.github.pocketkid2.thewalls.Utils;

import net.md_5.bungee.api.ChatColor;

public class SetArenaJoinSignSubCommand extends TheWallsSubCommand {

	public SetArenaJoinSignSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("setarenajoinsign", "js");
	}

	@Override
	public String description() {
		return "Sets the arena join sign for the specified arena to the sign you are currently looking at";
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
		return "setarenajoinsign/js <name>";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		Arena arena = plugin.getGM().getArenaByName(args[0]);
		if (arena == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "The arena " + ChatColor.GRAY + args[0] + ChatColor.RED + " does not exist!"));
			return;
		}
		Block b = player.getTargetBlockExact(5);
		if (b == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "Please get closer to your target join sign!"));
			return;
		}
		if (Utils.SIGN_TYPES.contains(b.getType())) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "You need to be looking at a sign!"));
			return;
		}
		arena.setJoinSign(b.getLocation());
		player.sendMessage(plugin.addPrefix(ChatColor.AQUA + "The sign you have targeted is now the join sign for arena " + ChatColor.GREEN + arena.getName()));
	}

}
