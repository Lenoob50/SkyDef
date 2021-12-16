package fr.lenoob.skydef.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import fr.lenoob.skydef.main;

public class OnPlayerDeath implements Listener {
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		main.getInstance().death(p);
		if(main.getInstance().Attaquants.getEntries().size() == 0) {
			Bukkit.broadcastMessage("§2[§3SkyDefender I§2] : §3Victoire des défenseurs");
			for(Player ps : Bukkit.getOnlinePlayers()) {
				main.getInstance().title.sendTitle(ps, 10, 20, 10, "§2[§3SkyDefender I§2] : §3Victoire des défenseurs");
			}
		}
	}

}
