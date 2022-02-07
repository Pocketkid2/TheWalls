package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class SetLobbySpawnSubCommand extends TheWallsSubCommand {

	public SetLobbySpawnSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("setlobbyspawn", "ls");
	}

	@Override
	public String description() {
		return "Sets TheWalls lobby spawn location to your current location";
	}

	@Override
	public int minArguments() {
		return 0;
	}

	@Override
	public int maxArguments() {
		return 0;
	}

	@Override
	public boolean isAdminCommand() {
		return true;
	}

	@Override
	public String usageMessage() {
		return "setlobbyspawn/ls";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		plugin.setLobbySpawn(player.getLocation());
		player.sendMessage(plugin.addPrefix(ChatColor.AQUA + "The lobby spawn has been set to your current location!"));
	}
}
