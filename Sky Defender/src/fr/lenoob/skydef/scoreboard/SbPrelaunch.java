package fr.lenoob.skydef.scoreboard;

import static org.bukkit.ChatColor.AQUA;
import static org.bukkit.ChatColor.GOLD;
import static org.bukkit.ChatColor.RED;
import static org.bukkit.ChatColor.WHITE;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;

import fr.lenoob.skydef.main;
import fr.lenoob.skydef.utils.SDState;



public class SbPrelaunch {
	
	private ScoreboardManager scoreboardManager;
    public SbPrelaunch(ScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    }
    
    
    
    public void prelaunch(Player player) {
        scoreboardManager.createBoard(player, fastBoard -> {
            fastBoard.updateTitle(AQUA+"Sky Defender");
            World world = Bukkit.getWorld("world");
            main.getInstance().setState(SDState.WAIT);
            world.setGameRuleValue("doDayLightCycle", "false");
            WorldBorder wb = world.getWorldBorder();
            Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
				
				@Override
				public void run() {
					fastBoard.updateLine(0, AQUA + "Joueurs en attente §3"+main.getInstance().playersInGame.size());
					fastBoard.updateLine(6, GOLD+ "Attaquants : §c"+main.getInstance().Attaquants.getEntries().size());
					fastBoard.updateLine(8, GOLD+"Défenseur : §3"+main.getInstance().Défenseur.getEntries().size());
					
				}
			}, 20, 20);
            fastBoard.updateLines(
                    AQUA + "Joueurs en attente §3"+main.getInstance().playersInGame.size() ,
                    WHITE + "§l>>-------------<<",
                    GOLD + "Jour : 0" ,
                    GOLD + "Heure : "+AQUA+"--h--" ,
                    GOLD + "Bordure : " + wb.getSize() + " X "+ wb.getSize(),
                    WHITE + "§l>>-------------<<",
                    GOLD+ "Attaquants : §c"+main.getInstance().Attaquants.getEntries().size(),
                    WHITE +" ",
                    GOLD+"Défenseurs : §3"+main.getInstance().Défenseur.getEntries().size(),
                    WHITE +" ",
                    WHITE + "§l>>-------------<<",
                    GOLD+"PVP : "+RED+"✗",
                    GOLD+"",
                    GOLD+"Nether : "+RED+"✗"

                    

            );
        });
    }

}


