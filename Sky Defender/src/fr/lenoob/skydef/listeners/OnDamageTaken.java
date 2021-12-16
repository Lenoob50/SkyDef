package fr.lenoob.skydef.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.lenoob.skydef.main;
import fr.lenoob.skydef.utils.SDState;

public class OnDamageTaken implements Listener {
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		DamageCause cause = e.getCause();
		if(cause == DamageCause.FALL || cause == DamageCause.SUFFOCATION) {
			if(main.getInstance().isState(SDState.WAIT)) {
				e.setCancelled(true);
			}else {
				e.setCancelled(false);
			}
			
		}
	}

}
