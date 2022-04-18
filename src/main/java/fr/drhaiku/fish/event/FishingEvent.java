package fr.drhaiku.fish.event;



import fr.drhaiku.fish.ItemBuilder;
import fr.drhaiku.fish.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.*;

public class FishingEvent implements Listener {

    private final HashMap<UUID, Long> cooldown;

    public FishingEvent(){this.cooldown = new HashMap<>();}


    @EventHandler
    public void onInteract(PlayerInteractEvent event){

        Player player = event.getPlayer();
        Action action = event.getAction();
        if(!cooldown.containsKey(player.getUniqueId())) return;
        long timeElapsed = System.currentTimeMillis() -cooldown.get(player.getUniqueId());

        if(timeElapsed <= 2000) {

            if(action == Action.RIGHT_CLICK_BLOCK){
                event.setCancelled(true);
            }

            if(action == Action.RIGHT_CLICK_AIR){
                event.setCancelled(true);
            }

        }

    }



    //PLUS PETITE DES CANNE A PECHE !

    @EventHandler
    public void onFishing(PlayerFishEvent event) throws InterruptedException {

        Connection connection = Main.getInstance().database.getCo();

        Player player = event.getPlayer();




        if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equalsIgnoreCase("Canne à Pêche [Débutant]")){

            if(event.getCaught() instanceof Item){

                if(((Item) event.getCaught()) != null){


                    event.setCancelled(true);

                    this.cooldown.put(player.getUniqueId(), System.currentTimeMillis());


                    List<String> fish = new ArrayList<String>();
                    fish.add("une Perche commune");
                    fish.add("une Truite");
                    fish.add("un Gardon");
                    fish.add("une Brème commune");
                    fish.add("un Rotengle");

                    String repfsh = fish.get(new Random().nextInt(fish.size()));

                    player.sendTitle(ChatColor.GOLD +"Félicitaion !", ChatColor.DARK_GREEN + "Vous avez péché " + repfsh + " !", 1, 20, 1);

                    if(repfsh.equalsIgnoreCase("une Truite")){
                        float poid = (float) (0.3 + (Math.random()*0.5));

                        String dec = "#.##";

                        DecimalFormat df = new DecimalFormat(dec);
                        df.setRoundingMode(RoundingMode.HALF_UP);
                        String size = df.format(poid);

                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `fishingmc`(`uuid`, `type`, `poids`) VALUE ( ?, ?, ?)");
                            preparedStatement.setString(1, player.getName());
                            preparedStatement.setString(2, repfsh.toString());
                            preparedStatement.setString(3, size.toString());
                            preparedStatement.execute();
                            preparedStatement.close();
                        } catch (SQLException e) {
                        }

                        Bukkit.broadcastMessage(ChatColor.GREEN + "Le joueur : " + ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.GREEN + " vient de pêcher " + ChatColor.DARK_GREEN + repfsh + ChatColor.GREEN + " de " + size + ChatColor.GREEN + "kg !" );
                    }

                    if(repfsh.equalsIgnoreCase("une Perche commune")){
                        float poid = (float) (0.7 + (Math.random()*2.0));

                        String dec = "#.##";

                        DecimalFormat df = new DecimalFormat(dec);
                        df.setRoundingMode(RoundingMode.HALF_UP);
                        String size = df.format(poid);

                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `fishingmc`(`uuid`, `type`, `poids`) VALUE ( ?, ?, ?)");
                            preparedStatement.setString(1, player.getName());
                            preparedStatement.setString(2, repfsh.toString());
                            preparedStatement.setString(3, size.toString());
                            preparedStatement.execute();
                            preparedStatement.close();
                        } catch (SQLException e) {
                        }

                        Bukkit.broadcastMessage(ChatColor.GREEN + "Le joueur : " + ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.GREEN + " vient de pêcher " + ChatColor.DARK_GREEN + repfsh + ChatColor.GREEN + " de " + ChatColor.DARK_GREEN + size + ChatColor.GREEN + "kg !" );
                    }

                    if(repfsh.equalsIgnoreCase("un Gardon")){
                        float poid = (float) (0.3 + (Math.random()*1.0));

                        String dec = "#.##";

                        DecimalFormat df = new DecimalFormat(dec);
                        df.setRoundingMode(RoundingMode.HALF_UP);
                        String size = df.format(poid);

                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `fishingmc`(`uuid`, `type`, `poids`) VALUE ( ?, ?, ?)");
                            preparedStatement.setString(1, player.getName());
                            preparedStatement.setString(2, repfsh.toString());
                            preparedStatement.setString(3, size.toString());
                            preparedStatement.execute();
                            preparedStatement.close();
                        } catch (SQLException e) {
                        }

                        Bukkit.broadcastMessage(ChatColor.GREEN + "Le joueur : " + ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.GREEN + " vient de pêcher " + ChatColor.DARK_GREEN + repfsh + ChatColor.GREEN + " de " + ChatColor.DARK_GREEN + size + ChatColor.GREEN + "kg !" );
                    }

                    if(repfsh.equalsIgnoreCase("une Brème commune")){
                        float poid = (float) (0.5 + (Math.random()*2.5));

                        String dec = "#.##";

                        DecimalFormat df = new DecimalFormat(dec);
                        df.setRoundingMode(RoundingMode.HALF_UP);
                        String size = df.format(poid);

                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `fishingmc`(`uuid`, `type`, `poids`) VALUE ( ?, ?, ?)");
                            preparedStatement.setString(1, player.getName());
                            preparedStatement.setString(2, repfsh.toString());
                            preparedStatement.setString(3, size.toString());
                            preparedStatement.execute();
                            preparedStatement.close();
                        } catch (SQLException e) {
                        }

                        Bukkit.broadcastMessage(ChatColor.GREEN + "Le joueur : " + ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.GREEN + " vient de pêcher " + ChatColor.DARK_GREEN + repfsh + ChatColor.GREEN + " de " + ChatColor.DARK_GREEN + size + ChatColor.GREEN + "kg !" );
                    }

                    if(repfsh.equalsIgnoreCase("un Epinoche")){
                        float poid = (float) (0.5 + (Math.random()*2.5));

                        String dec = "#.##";

                        DecimalFormat df = new DecimalFormat(dec);
                        df.setRoundingMode(RoundingMode.HALF_UP);
                        String size = df.format(poid);

                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `fishingmc`(`uuid`, `type`, `poids`) VALUE ( ?, ?, ?)");
                            preparedStatement.setString(1, player.getName());
                            preparedStatement.setString(2, repfsh.toString());
                            preparedStatement.setString(3, size.toString());
                            preparedStatement.execute();
                            preparedStatement.close();
                        } catch (SQLException e) {
                        }

                        Bukkit.broadcastMessage(ChatColor.GREEN + "Le joueur : " + ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.GREEN + " vient de pêcher " + ChatColor.DARK_GREEN + repfsh + ChatColor.GREEN + " de " + ChatColor.DARK_GREEN + size + ChatColor.GREEN + "kg !" );
                    }
                    if(repfsh.equalsIgnoreCase("un Rotengle")){
                        float poid = (float) (0.3 + (Math.random()*1.8));

                        String dec = "#.##";

                        DecimalFormat df = new DecimalFormat(dec);
                        df.setRoundingMode(RoundingMode.HALF_UP);
                        String size = df.format(poid);

                        try {
                            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `fishingmc`(`uuid`, `type`, `poids`) VALUE ( ?, ?, ?)");
                            preparedStatement.setString(1, player.getName());
                            preparedStatement.setString(2, repfsh.toString());
                            preparedStatement.setString(3, size.toString());
                            preparedStatement.execute();
                            preparedStatement.close();
                        } catch (SQLException e) {
                        }

                        Bukkit.broadcastMessage(ChatColor.GREEN + "Le joueur : " + ChatColor.DARK_GREEN + player.getDisplayName() + ChatColor.GREEN + " vient de pêcher " + ChatColor.DARK_GREEN + repfsh + ChatColor.GREEN + " de "  + ChatColor.DARK_GREEN + size + ChatColor.GREEN + " kg !" );
                    }


                }
            }



        }


    }


}
