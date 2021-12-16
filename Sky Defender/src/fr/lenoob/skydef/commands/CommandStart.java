package fr.lenoob.skydef.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lenoob.skydef.main;
import fr.lenoob.skydef.scoreboard.Sb;

public class CommandStart implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
			if(sender instanceof Player) {
				Player p = (Player) sender;
				Bukkit.dispatchCommand(p, "gamerule doDaylightCycle true");
				World world = Bukkit.getWorld("world");
				world.setGameRuleValue("doDaylightCycle ", "true");
				world.setDifficulty(Difficulty.NORMAL);
				if(p.hasPermission("sd.start")) {
					for(Player ps : Bukkit.getOnlinePlayers()) {
						ps.getInventory().clear();
						ps.setGameMode(GameMode.SURVIVAL);
						if(main.getInstance().Attaquants.getEntries().contains(ps.getDisplayName())) {
							Random r = new Random();
							int low = 100;
							int high = 1000;
							int result = r.nextInt(high-low) + low;
							ps.teleport(new Location(Bukkit.getWorld("world"), result, 100, result));
						}else {
							Location spawn = new Location(Bukkit.getWorld("world"), 0, 207, -26);
							ps.teleport(spawn);
						}
						new Sb(main.getInstance().sm).game(ps);
					}
					Bukkit.broadcastMessage("§2[§3SkyDefender I§2] : §3Partie démarré");
					}else {
						p.sendMessage("§2[§3SkyDefender I§2] : §3Tu n'as pas la permisions d'executer ceci");
					}
			}else {
				sender.sendMessage("§2[§3SkyDefender I§2] : §3Tu n'es pas un joueur");
			}

		return false;
	}

}
