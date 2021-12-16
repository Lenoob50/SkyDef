package fr.lenoob.skydef.commands;


import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.lenoob.skydef.main;
import fr.lenoob.skydef.scoreboard.SbPrelaunch;
import fr.lenoob.skydef.utils.SDState;


public class CommandSDStop implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("sd.stop")) {
				World world = Bukkit.getWorld("world");
				world.setDifficulty(Difficulty.PEACEFUL);
				main.getInstance().setState(SDState.WAIT);
				for(Player ps : Bukkit.getOnlinePlayers()) {
					new SbPrelaunch(main.getInstance().sm).prelaunch(ps);
					Location sp = new Location(world, 0, 198, -25);
					ps.teleport(sp);
	        		}
				Bukkit.broadcastMessage("§2[§3SkyDefender I§2] : §3Partie stoppé");
				world.setTime(0);
				world.setDifficulty(Difficulty.PEACEFUL);
				Bukkit.dispatchCommand(p, "gamerule doDaylightCycle false");
				Bukkit.getScheduler().cancelAllTasks();

			}
		}
		return false;
	}

}
