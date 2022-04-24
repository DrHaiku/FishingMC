package fr.drhaiku.fish.sop;

import com.sun.tools.javac.jvm.Items;
import fr.drhaiku.fish.ItemBuilder;
import fr.drhaiku.fish.Main;
import fr.drhaiku.fish.bank.Economy;
import fr.drhaiku.fish.sop.FishSell;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.drhaiku.fish.bank.Economy;

public class Poissonier implements Listener {

    //Economy eco = Main.getInstance().eco;
    Economy eco = new Economy();

    FishSell fishSell = new FishSell();

    @EventHandler
    public void onInventoryLeft(InventoryCloseEvent e){

        Player player = (Player) e.getPlayer();

        ItemStack item = (ItemStack) player.getOpenInventory().getItem(2).getItemMeta();


        if(player.getOpenInventory().getTitle().equalsIgnoreCase("Chez Edward")){
            if(!player.getOpenInventory().getItem(2).getType().equals(Material.AIR)){
                player.getInventory().addItem(item);
            }


        }

    }


    @EventHandler
    public void onClick(InventoryClickEvent e){


        Player player = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if (current == null) return;

        if(player.getOpenInventory().getTitle().equalsIgnoreCase("Chez Edward")){
            if(current.getType().equals(Material.WHITE_STAINED_GLASS_PANE)){

                e.setCancelled(true);

            }

            if(current.getType().equals(Material.GREEN_TERRACOTTA)){

                if(player.getOpenInventory().getItem(2).getType().equals(Material.COD)){


                    if(player.getOpenInventory().getItem(2).getItemMeta().getDisplayName().equalsIgnoreCase("Truite")){

                        float size = player.getOpenInventory().getItem(2).getItemMeta().getLore();


                        if(size <= 0.4){

                            player.sendMessage("test ton poisson -0.3");

                        }

                        if(size > 0.4 && size < 0.5){

                            player.sendMessage("test ton poisson + 0.4 et -0.5");

                        }

                        if(size >= 0.5){

                            player.sendMessage("test ton poisson + 0.5");

                        }

                        player.getOpenInventory().setItem(2, new ItemStack(Material.AIR));

                    }

                    e.setCancelled(true);


                } else{
                    e.setCancelled(true);
                }

            }


        }

    }


    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {
        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();

        if (entity instanceof ArmorStand) {
            if (entity.getName().equalsIgnoreCase("poissonier")) {

                Inventory poissonier = Bukkit.createInventory(null, 9, "Chez Edward");

                player.openInventory(poissonier);

                poissonier.setItem(0, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").toItemStack());
                poissonier.setItem(1, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").toItemStack());
                poissonier.setItem(3, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").toItemStack());
                poissonier.setItem(4, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").toItemStack());
                poissonier.setItem(5, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").toItemStack());
                poissonier.setItem(7, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").toItemStack());
                poissonier.setItem(8, new ItemBuilder(Material.WHITE_STAINED_GLASS_PANE).setName(" ").toItemStack());

                poissonier.setItem(6, new ItemBuilder(Material.GREEN_TERRACOTTA).setName("Ok").toItemStack());
            }
        }
    }

}
