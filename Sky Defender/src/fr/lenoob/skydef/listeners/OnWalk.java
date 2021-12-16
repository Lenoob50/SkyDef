package fr.lenoob.skydef.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.lenoob.skydef.main;
import fr.lenoob.skydef.utils.ActionBar;
import fr.lenoob.skydef.utils.ArrowTargetUtils;

public class OnWalk implements Listener {
	
	@EventHandler
	public void onMoove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		int locx = p.getLocation().getBlockX();
		int locy = p.getLocation().getBlockY();
		int locz = p.getLocation().getBlockZ();
		Location tpbas = new Location(Bukkit.getWorld("world"),1,71,35);
		Location tphaut = new Location(Bukkit.getWorld("world"),0,193,11);
		Location banner = new Location(Bukkit.getWorld("world"), 0, 233, -11);
		if(main.getInstance().Défenseur.getEntries().contains(p.getDisplayName())) {
				Location tpbasloc = new Location(Bukkit.getWorld("world"), 1, 72, 21);
				Location tphautloc = new Location(Bukkit.getWorld("world"), 0, 194, 21);
				int y = p.getLocation().getBlockY();
				if(y < 74) {
					ActionBar.sendActionBar(p, "§6Le téléporteur est à §f"+(int)p.getLocation().distance(tpbasloc)+" §6blocks "+ArrowTargetUtils.calculateArrow(p, tpbasloc));
				}else {
					ActionBar.sendActionBar(p, "§6Le téléporteur est à §f"+(int)p.getLocation().distance(tphautloc)+" §6blocks "+ArrowTargetUtils.calculateArrow(p, tphautloc));
			}
		}
		if(main.getInstance().Attaquants.getEntries().contains(p.getDisplayName())) {
			ActionBar.sendActionBar(p, "§6La bannière est à §f"+(int)p.getLocation().distance(banner)+" §6blocks "+ArrowTargetUtils.calculateArrow(p, banner));
		}
		if(locx == 0 && locy == 194 && locz == 21) {
			if(main.getInstance().Défenseur.getEntries().contains(p.getDisplayName())) {
				p.teleport(tpbas);
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
			}else {
				p.sendMessage("§2[§3SkyDefender I§2] : §cTu ne peux pas utiliser sa");
			}
		}else if(locx == 1 && locy == 72 && locz == 21) {
			if(main.getInstance().Défenseur.getEntries().contains(p.getDisplayName())) {
				p.teleport(tphaut);
				p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
			}else {
				p.sendMessage("§2[§3SkyDefender I§2] : §cTu ne peux pas utiliser sa");
			}
		
	}
	}
}
