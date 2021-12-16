package fr.lenoob.skydef.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.lenoob.skydef.main;

public class InventoryInterract implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory clickInv = e.getClickedInventory();
		ItemStack it = e.getCurrentItem();
		switch(it.getType()) {
		case BANNER :
			if(clickInv.getName().equals("§6Choix des teams")) {
				e.setCancelled(true);
				if(it.getItemMeta().getDisplayName() == "§c§oRejoindre Attaquants") {
					main.getInstance().Attaquants.addEntry(p.getDisplayName());
					p.sendMessage("§2[§3SkyDefender I§2] : §3Barvo tu as rejoins les §c Attaquants.");
					Bukkit.broadcastMessage("§2[§3SkyDefender I§2] : §c"+p.getDisplayName()+"§3 à rejoins l'équipe des §cAttaquants");
					ItemStack bannerget = new ItemStack(Material.BANNER,1,(short)1);
					ItemMeta bannergetM = bannerget.getItemMeta();
					bannergetM.setDisplayName("§6§lChoix de la Team");
					bannerget.setItemMeta(bannergetM);
					p.getInventory().clear();
					p.getInventory().setItem(4, bannerget);
					p.closeInventory();
				}
				if(it.getItemMeta().getDisplayName() == "§3§oRejoindre Défenseur") {
					main.getInstance().Défenseur.addEntry(p.getDisplayName());
					p.sendMessage("§2[§3SkyDefender I§2] : §3Barvo tu as rejoins les §3 Défenseur.");
					Bukkit.broadcastMessage("§2[§3SkyDefender I§2] : §3"+p.getDisplayName()+"§3 à rejoins l'équipe des Défenseurs");
					ItemStack bannerget = new ItemStack(Material.BANNER,1,(short)12);
					ItemMeta bannergetM = bannerget.getItemMeta();
					bannergetM.setDisplayName("§6§lChoix de la Team");
					bannerget.setItemMeta(bannergetM);
					p.getInventory().clear();
					p.getInventory().setItem(4, bannerget);
					p.closeInventory();
				}
			}
			break;
		case STAINED_GLASS_PANE :
			e.setCancelled(true);
		default:
			break;
		}
			
	}

}
