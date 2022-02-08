package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.pocketkid2.thewalls.Arena;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

import net.md_5.bungee.api.ChatColor;

public class LeaveSubCommand extends TheWallsSubCommand {

	public LeaveSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return true;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("leave", "quit", "exit");
	}

	@Override
	public String description() {
		return "Leaves the game you are currently in, and if the game is in progress, you forfeit";
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
		return false;
	}

	@Override
	public String usageMessage() {
		return "leave";
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		Arena arena = plugin.getGM().getArenaForPlayer(player);
		if (arena == null) {
			player.sendMessage(plugin.addPrefix(ChatColor.RED + "You're not currently in a game!"));
			return;
		}

		// Schedule leave task
		new BukkitRunnable() {
			@Override
			public void run() {
				player.getInventory().clear();
				player.setHealth(20);
				player.setFoodLevel(20);
				player.setExp(0);
				player.setExhaustion(20);
				player.teleport(plugin.getLobbySpawn());
				arena.removePlayer(player);
				plugin.debug("Resetting player " + player.getName());
				player.sendMessage(plugin.addPrefix(ChatColor.BOLD + "You left the game"));
				arena.broadcast(ChatColor.GREEN + player.getDisplayName() + ChatColor.AQUA + " has left the game voluntarily (" + ChatColor.GOLD + arena.getPlayers().size() + ChatColor.AQUA + ")");
			}
		};

	}

}
