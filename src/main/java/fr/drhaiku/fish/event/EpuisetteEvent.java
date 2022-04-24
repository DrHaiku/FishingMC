package fr.drhaiku.fish.event;

import fr.drhaiku.fish.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EpuisetteEvent implements Listener {

    @EventHandler
    public void onEnter(PlayerMoveEvent event) {

        Player player = event.getPlayer();
        if (!Main.hasEpuisette(player)) return;
        if (Main.epuisette.contains(player)) return;
        Main.epuisette.add(player);


    }

}
