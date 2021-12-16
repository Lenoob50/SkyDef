package fr.lenoob.skydef.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.lenoob.skydef.utils.InventoryManager;

public class OnInterract implements Listener {
	
	@EventHandler
	public void onInterract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack it = p.getInventory().getItemInHand();
		Action act = e.getAction();
		if(it.getType() == Material.BANNER && it.getItemMeta().getDisplayName() == "§6§lChoix de la Team" ) {
			if(act == Action.RIGHT_CLICK_AIR || act == Action.RIGHT_CLICK_BLOCK) {
				e.setCancelled(true);
				Inventory inv = new InventoryManager().Team;
				p.openInventory(inv);
				p.updateInventory();
		}else {
			return;
		}
	}
	}

}
