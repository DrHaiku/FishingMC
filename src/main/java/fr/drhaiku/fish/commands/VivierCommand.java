package fr.drhaiku.fish.commands;

import fr.drhaiku.fish.ItemBuilder;
import fr.drhaiku.fish.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VivierCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        Connection connection = Main.getInstance().database.getCo();

        if(label.equalsIgnoreCase("vivier")){

            if(args.length == 0){

                try{
                    Inventory vivier = Bukkit.createInventory(null, 54, "Vivier de " + sender.getName());


                    String request = "SELECT * FROM `fishingmc` WHERE `uuid` = '" + sender.getName() + "'";

                    Statement stmt = connection.createStatement();

                    ResultSet resultats = stmt.executeQuery(request);

                    while(resultats.next()){

                        vivier.addItem((new ItemBuilder(Material.COD).setName(ChatColor.DARK_GREEN + resultats.getString(2)).setLore(ChatColor.GREEN + "Poids : " + resultats.getString(3)).toItemStack()));

                    }


                    Player player = (Player) sender;

                    player.openInventory(vivier);

                }catch (SQLException e){

                }

            }

            if(args.length == 1){

                String player = args[0];

                try{
                    Inventory vivier = Bukkit.createInventory(null, 54, "Vivier de " + player);


                    String request = "SELECT * FROM `fishingmc` WHERE `uuid` = '" + player + "'";

                    Statement stmt = connection.createStatement();

                    ResultSet resultats = stmt.executeQuery(request);

                    while(resultats.next()){

                        vivier.addItem((new ItemBuilder(Material.COD).setName(ChatColor.DARK_GREEN + resultats.getString(2)).setLore(ChatColor.GREEN + "Poids : " + resultats.getString(3)).toItemStack()));

                    }


                    Player p = (Player) sender;

                    p.openInventory(vivier);

                }catch (SQLException e){

                }

            }

        }

        return false;
    }
}
