package fr.lenoob.skydef.listeners;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.lenoob.skydef.main;

public class OnTalk implements Listener {
	
	@EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
	Player p = e.getPlayer();
	String newmsg = e.getMessage().substring(1);
	e.setCancelled(true);
	if(main.getInstance().Attaquants.getEntries().contains(p.getDisplayName())) {
		if(!e.getMessage().startsWith("!")) {
			for (String pa : main.getInstance().Attaquants.getEntries()){
					Player ta = Bukkit.getPlayer(pa);
					ta.sendMessage("§7[Team] §c"+p.getDisplayName()+"§6 >> §r"+e.getMessage());
				}
			}else {
				for (Player op : Bukkit.getOnlinePlayers()) {
					op.sendMessage("§7[Global] §c"+p.getDisplayName()+"§6 >> §r"+newmsg);
				}
			}
		}else if(main.getInstance().Défenseur.getEntries().contains(p.getDisplayName())) {
			if(!e.getMessage().startsWith("!")) {
			for (String pd : main.getInstance().Défenseur.getEntries()){
					Player td = Bukkit.getPlayer(pd);
					td.sendMessage("§7[Team] §3"+p.getDisplayName()+"§6 >> §r"+e.getMessage());
				}
			}else {
				for (Player op : Bukkit.getOnlinePlayers()) {
					op.sendMessage("§7[Global] §3"+p.getDisplayName()+"§6 >> §r"+newmsg);
				}
			}
		}else {
			for (Player op : Bukkit.getOnlinePlayers()) {
				op.sendMessage("§7[Global] §3"+p.getDisplayName()+"§6 >> §r"+newmsg);
			}
		}
	}
}



