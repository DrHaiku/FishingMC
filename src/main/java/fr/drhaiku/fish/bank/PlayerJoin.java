package fr.drhaiku.fish.bank;

import fr.drhaiku.fish.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.*;
import java.util.UUID;

public class PlayerJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event){


        Player player = event.getPlayer();

        Main.getInstance().createAccount(player);

    }

}
