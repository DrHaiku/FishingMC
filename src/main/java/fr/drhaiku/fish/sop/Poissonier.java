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

import java.util.Objects;

public class Poissonier implements Listener {

    //Economy eco = Main.getInstance().eco;
    Economy eco = new Economy();

    FishSell fishSell = new FishSell();

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e){

        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();


        if(entity instanceof ArmorStand){

            if(entity.getName().equalsIgnoreCase("poissonier")){

                if(player.getInventory().getItemInMainHand().getType().equals(Material.COD)){

                    return;

                }else{

                    if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("Truite")){

                        float size = Float.parseFloat(player.getInventory().getItemInMainHand().getItemMeta().getLore().get(1));

                        if(size <= 0.4){

                            player.sendMessage("test ton poisson -0.3");

                        }

                        if(size > 0.4 && size < 0.5){

                            player.sendMessage("test ton poisson + 0.4 et -0.5");

                        }

                        if(size >= 0.5){

                            player.sendMessage("test ton poisson + 0.5");

                        }



                    }

                }

            }

        }

    }



}
