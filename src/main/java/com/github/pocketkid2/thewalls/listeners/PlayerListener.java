package com.github.pocketkid2.thewalls.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.github.pocketkid2.thewalls.Status;
import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class PlayerListener implements Listener {

	private TheWallsPlugin plugin;

	public PlayerListener(TheWallsPlugin p) {
		plugin = p;
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (plugin.getGM().isInGame(event.getPlayer()) && (plugin.getGM().getArenaForPlayer(event.getPlayer()).getStatus() != Status.INGAME)) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(plugin.addPrefix(ChatColor.RED + "The game has not started yet!"));
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if (plugin.getGM().isProtected(event.getBlock())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(plugin.addPrefix(ChatColor.RED + "Please do not break the walls!"));
		}
	}

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		if (plugin.getGM().isInGame(event.getPlayer())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage(plugin.addPrefix(ChatColor.RED + "Do not teleport out of the game! If you would like to leave, type /tw leave"));
		}
	}
}
