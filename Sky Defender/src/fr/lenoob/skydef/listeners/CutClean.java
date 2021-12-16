package fr.lenoob.skydef.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;



public class CutClean implements Listener {
	
    @EventHandler
    public void onBlocBreak(BlockBreakEvent event) {
        if(event.isCancelled()){
            return;
        }
        Block b = event.getBlock();
        World w =b.getWorld();
        Location l = b.getLocation();
        Player p = event.getPlayer();
        int playerExp = (int) p.getExp();

        if(b.getType().equals(Material.IRON_ORE)){
            event.setCancelled(true);
            b.setType(Material.AIR);
            p.getInventory().addItem(new ItemStack(Material.IRON_INGOT,1));
            p.setExp(playerExp+10);
        }else if(b.getType().equals(Material.GOLD_ORE)){
            event.setCancelled(true);
            b.setType(Material.AIR);
            p.getInventory().addItem(new ItemStack(Material.GOLD_INGOT,1));
            p.setExp(playerExp+10);
        }
    }

}
