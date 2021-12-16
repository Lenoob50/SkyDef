package fr.lenoob.skydef.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.lenoob.skydef.main;

public class OnBreak implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Block b = e.getBlock();
		if(b.getType().equals(Material.WALL_BANNER)) {
				if(main.getInstance().Défenseur.getEntries().size() == 0) {
					main.getInstance().title.sendTitle(e.getPlayer(), 10, 20, 10, "§2[§3SkyDefender I§2] : §3 Victoire des §cAttaquants");
				}else {
					e.setCancelled(true);
					e.getPlayer().sendMessage("§2[§3SkyDefender I§2] : §3Tue d'abbord tout les défenseurs");
			}
					
		}
	}

}
