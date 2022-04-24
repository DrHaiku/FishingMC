package fr.drhaiku.fish.sop;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class FishSell {

    public void isTruite(Player player){

        if(player.getOpenInventory().getTitle().equalsIgnoreCase("Chez Edward")){

            if(player.getOpenInventory().getItem(2).getItemMeta().getDisplayName().equalsIgnoreCase("Truite")){

                float size = Integer.parseInt(String.valueOf(player.getOpenInventory().getItem(2).getItemMeta().getLore()));


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

        }



    }


}
