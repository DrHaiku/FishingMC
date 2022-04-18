package fr.drhaiku.fish.sop;

import com.sun.tools.javac.jvm.Items;
import fr.drhaiku.fish.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Poissonier implements Listener {

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event){

        Player player = event.getPlayer();

        Entity entity = event.getRightClicked();

        if(entity instanceof ArmorStand){
            if(entity.getName().equalsIgnoreCase("poissonier")){

                Inventory poisson = Bukkit.createInventory(null, 54, " ");

                player.openInventory(poisson);

                poisson.setItem(1, new ItemStack(new ItemBuilder(Material.DIAMOND_HOE).setName(" ").setCustomModelData(4).toItemStack()));
                poisson.setItem(19, new ItemStack(new ItemBuilder(Material.DIAMOND_HOE).setName(" ").setCustomModelData(5).toItemStack()));
                poisson.setItem(37, new ItemStack(new ItemBuilder(Material.DIAMOND_HOE).setName(" ").setCustomModelData(6).toItemStack()));

                poisson.setItem(4, new ItemStack(new ItemBuilder(Material.DIAMOND_HOE).setName(" ").setCustomModelData(2).toItemStack()));
                poisson.setItem(22, new ItemStack(new ItemBuilder(Material.DIAMOND_HOE).setName(" ").setCustomModelData(2).toItemStack()));
                poisson.setItem(40, new ItemStack(new ItemBuilder(Material.DIAMOND_HOE).setName(" ").setCustomModelData(2).toItemStack()));

                poisson.setItem(53, new ItemStack(new ItemBuilder(Material.DIAMOND_HOE).setName(" ").setCustomModelData(1).toItemStack()));
                poisson.setItem(52, new ItemStack(new ItemBuilder(Material.DIAMOND_HOE).setName(" ").setCustomModelData(7).toItemStack()));

            }

        }

    }

    @EventHandler
    public void onInteract(InventoryClickEvent event){

        Player player = (Player) event.getWhoClicked();

        ItemStack current = event.getCurrentItem()

    }

}
