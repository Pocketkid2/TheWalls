package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.pocketkid2.thewalls.TheWallsPlugin;

import net.md_5.bungee.api.ChatColor;

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
		return Arrays.asList("setlobbyspawn");
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
		return "setlobbyspawn";
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		Player player = (Player) sender;
		plugin.setLobbySpawn(player.getLocation());
		player.sendMessage(ChatColor.AQUA + "Set the lobby spawn to your current location!");
	}

}
