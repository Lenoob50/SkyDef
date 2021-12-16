package fr.lenoob.skydef.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.lenoob.skydef.main;

public class OnQuitt implements Listener {
	
	@EventHandler
	public void onQuitt(PlayerQuitEvent e) {
		e.setQuitMessage("");
		Player p = e.getPlayer();
		Bukkit.broadcastMessage("§2[§3SkyDefender I§2] : §3"+p.getDisplayName()+" §2à quitter Sky Defender I");
		main.getInstance().playersInGame.remove(p.getUniqueId());
	}

}
