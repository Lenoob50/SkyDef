package fr.lenoob.skydef.utils;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryManager {

	public Inventory Team;
	
	 public InventoryManager(){
	        teamSelect();
	 }
	 public void teamSelect(){
	        Inventory invt = Bukkit.createInventory(null,27,"§6Choix des teams");
	        ItemStack is = new ItemStack(Material.STAINED_GLASS_PANE,1,(short) 9);
	        ItemMeta im = is.getItemMeta();
	        im.setDisplayName(" ");
	        is.setItemMeta(im);
	        
	    	ItemStack g = new ItemStack(Material.BANNER, 1,(short)1);
			ItemMeta gM= g.getItemMeta();
			gM.setDisplayName("§c§oRejoindre Attaquants");
			g.setItemMeta(gM);

	    	ItemStack sp = new ItemStack(Material.BANNER, 1,(short)12);
			ItemMeta spM= sp.getItemMeta();
			spM.setDisplayName("§3§oRejoindre Défenseur");
			sp.setItemMeta(spM);
	        
	        int[] pan = {0,1,2,3,4,5,6,7,8,9,10,11,13,15,16,17,18,19,20,21,22,23,24,25,26};
	        for(int slotpan : pan){
	            invt.setItem(slotpan,is);
	        }
	        
	        invt.setItem(12,g);
	        invt.setItem(14,sp);
	        
	        Team = invt;
	 }
}

