package fr.drhaiku.fish.camion;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class MissionCam implements Listener {

    //Premiere event, charger le camion

    public void onInteract(PlayerInteractAtEntityEvent e){

        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();

        if(entity instanceof ArmorStand){
            if(entity.getName().equalsIgnoreCase("chargeur")){

                
            }

        }
    }


}
