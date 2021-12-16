package fr.lenoob.skydef.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lenoob.skydef.main;

public class CommandeRevive implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("sd.revive")) {
				if(args.length == 0) {
					p.sendMessage("La commande est /revive <player>");
				}
				if(args.length >= 1) {
					
					StringBuilder bc = new StringBuilder();
					for(String part : args) {
						bc.append(part + "");
					}
				Player rp = Bukkit.getPlayer(bc.toString());
				main.getInstance().playersInGame.add(rp.getUniqueId());
				Bukkit.broadcastMessage("§2[§3SkyDefender I§2] : §3"+bc.toString()+" à été revive");
				Bukkit.dispatchCommand(p, "gamemode survival "+bc.toString());
				rp.setGameMode(GameMode.SURVIVAL);
				}
			}
		}
		return false;
	}

}
