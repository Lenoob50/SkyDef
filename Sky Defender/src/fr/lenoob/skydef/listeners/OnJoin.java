package fr.lenoob.skydef.listeners;


import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.lenoob.skydef.main;
import fr.lenoob.skydef.scoreboard.SbPrelaunch;
import fr.lenoob.skydef.scoreboard.SbRejoin;
import fr.lenoob.skydef.utils.SDState;

public class OnJoin implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) throws IOException {
		Player p = e.getPlayer();
		p.setScoreboard(main.getInstance().sb);
		e.setJoinMessage("");
		Bukkit.broadcastMessage("§2[§3SkyDefender I§2] : §3"+p.getDisplayName()+" §2à rejoins Sky Defender I");
		main.getInstance().addInGame(p);
		ItemStack banner = new ItemStack(Material.BANNER, 1);
		ItemMeta bannerM = banner.getItemMeta();
		bannerM.setDisplayName("§6§lChoix de la Team");
		banner.setItemMeta(bannerM);
		if(main.getInstance().isState(SDState.WAIT)) {
			new SbPrelaunch(main.getInstance().sm).prelaunch(p);
			p.getInventory().clear();
			p.getInventory().setItem(4, banner);
		}
		if(main.getInstance().isState(SDState.GAME)||main.getInstance().isState(SDState.PVP)||main.getInstance().isState(SDState.NETHER)) {
			new SbRejoin(main.getInstance().sm).game(p);
		}
	}

}
