package fr.lenoob.skydef.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import fr.lenoob.skydef.main;
import fr.lenoob.skydef.utils.SDState;

public class OnPlace implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		if(e.getBlockPlaced().getType() == Material.BANNER ) {
			if(main.getInstance().isState(SDState.WAIT)) {
				if(e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName() == "§6§lChoix de la Team") {
					e.setCancelled(true);
					e.setBuild(false);
				}
			}
		}else {
			e.setCancelled(false);
			e.setBuild(true);
		}
	}

}
