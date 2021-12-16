package fr.lenoob.skydef.scoreboard;

import static org.bukkit.ChatColor.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import fr.lenoob.skydef.main;
import fr.lenoob.skydef.utils.SDState;


public class Sb {

    private ScoreboardManager scoreboardManager;
    public Sb(ScoreboardManager scoreboardManager) {
        this.scoreboardManager = scoreboardManager;
    
	}	

	public static Sb instance;
	public static Sb getInstance(){
	        return instance;
	}
    public int timer = 0;
    public int Episode = 1;
    public void game(Player player) {
        scoreboardManager.createBoard(player, fastBoard -> {
            fastBoard.updateTitle(AQUA +"Sky Defender I");
            World w = Bukkit.getWorld("world");
            WorldBorder wb = w.getWorldBorder();

            Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
				@Override
                public void run() {
                    timer++;
                    FileWriter fw = null;
                    String dateformat = new SimpleDateFormat(GOLD + "'Heure : §3'mm:ss").format(timer * 1000);
                    for(Player p : Bukkit.getOnlinePlayers()) {
                        fastBoard.updateLine(3, dateformat);
                        try {
                        	fw = new FileWriter("plugins/SkyDefender/time.txt");
						} catch (IOException e) {
							e.printStackTrace();
						}
                        try {
							fw.write(dateformat);
						} catch (IOException e) {
							e.printStackTrace();
						}
                        try {
							fw.close();
						} catch (IOException e2) {
							e2.printStackTrace();
						}
                        fastBoard.updateLine(4, GOLD +"Bordure : §3" + wb.getSize() + "X "+ wb.getSize());
                    	fastBoard.updateLine(0, AQUA + "Joueurs en game : " + main.getInstance().playersInGame.size());
    					fastBoard.updateLine(6, GOLD+ "Attaquants : §c"+main.getInstance().Attaquants.getEntries().size());
    					fastBoard.updateLine(8, GOLD+"Défenseur : §3"+main.getInstance().Défenseur.getEntries().size());
                    }
                    if(timer == 1200){
                    	Episode ++;
                        Bukkit.broadcastMessage(GREEN +"[§3Sky Defender I§2]: §3Début du jour : " + Episode);
                        fastBoard.updateLine(2, GOLD + "Jour : " + Episode);
                        timer = 0;
                        Bukkit.getWorld("world").setTime(0);
                        FileWriter f2 = null;
						try {
							f2 = new FileWriter("plugins/SkyDefender/ep.txt");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        try {
							f2.write(Episode);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                        try {
							f2.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

                    }
                    if(timer == 60 && Episode == 1) {
                    	Bukkit.broadcastMessage(GREEN +"[§3Sky Defender I§2]: §3Dégats Actif");
                    	main.getInstance().setState(SDState.GAME);
                    }
                    if(Episode == 2 && timer == 0) {
                    	Bukkit.broadcastMessage(GREEN +"[§3Sky Defender I§2]: §3PVP Actif");
                    	w.setPVP(true);
                    	main.getInstance().setState(SDState.PVP);
                    	fastBoard.updateLine(10, GOLD+"PVP : "+GREEN+"✔");
                    	
                    }
                    if(main.getInstance().isState(SDState.PVP)) {
                    	fastBoard.updateLine(10, GOLD+"PVP : "+GREEN+"✔");
                    }
                    if(timer == 600) {
                    	w.setTime(13000);
                    	Bukkit.broadcastMessage(GREEN +"[§3Sky Defender I§2]: §3Début de la nuit");
                    }
                    
                    if(Episode == 8) {
                    	wb.setCenter(0, 0);
                    	wb.setDamageAmount(1.0);
                    	Bukkit.broadcastMessage(GREEN +"[§3Sky Defender I§2]: §3Bordure en mouvement");
                    	Bukkit.getScheduler().scheduleSyncRepeatingTask(main.getInstance(), new Runnable() {
							
							@Override
							public void run() {
								if(wb.getSize() >= 50) {
									wb.setSize(wb.getSize() - 2.0);
								}
							}
						}, 20, 20);
                    }
                    if(timer == 1170) {
                    	player.sendMessage(GREEN +"[§3Sky Defender I§2]: §3Fin du jour : " + Episode + GREEN+" dans 30 secondes");
                    }
                    if(timer == 1190) {
                    	player.sendMessage(GREEN +"[§3Sky Defender I§2]: §3Fin du jour : " + Episode + GREEN+" dans 10 secondes");
                    }
                }
            }, 20, 20);
            	

            fastBoard.updateLines(
                    AQUA + "Joueurs en game :"+main.getInstance().playersInGame.size() ,
                    WHITE + "§l>>-------------<<",
                    GOLD + "Jour : §3"+Episode ,
                    GOLD + "Heure : " ,
                    GOLD + "Bordure : " + wb.getSize() + " X "+ wb.getSize(),
                    WHITE + "§l>>-------------<<",
                    GOLD+ "Attaquants : §c"+main.getInstance().Attaquants.getEntries().size(),
                    WHITE +" ",
                    GOLD+"Défenseurss : §3"+main.getInstance().Défenseur.getEntries().size(),
                    WHITE +" ",
                    WHITE + "§l>>-------------<<",
                    GOLD+"PVP : "+RED+"✗",
                    GOLD+"",
                    GOLD+"Nether : "+RED+"✗"

            );
        });
    }

}



