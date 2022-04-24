package fr.drhaiku.fish.bank;

import fr.drhaiku.fish.ItemBuilder;
import fr.drhaiku.fish.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.*;
import java.util.Locale;

public class BanqueCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Connection connection = Main.getInstance().database.getCo();

        if(label.equalsIgnoreCase("banque")){


            if (args.length == 0) {

                try {

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT `uuid`, `balance` FROM `banque` WHERE uuid = (?)");
                    preparedStatement.setString(1, sender.getName().toString());
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();

                    sender.sendMessage(ChatColor.GREEN + "Votre compte en banque : " + ChatColor.DARK_GREEN + rs.getString(2) + ChatColor.GREEN + " €");


                } catch (SQLException e) {
                    System.out.println(e);
                }

                return false;

            }

            if(args[0].equalsIgnoreCase("add")){

                String player = args[2];


                try {

                    PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `banque` SET `balance` = (`balance` + ?) WHERE uuid = ?");
                    preparedStatement.setString(1, args[1]);
                    preparedStatement.setString(2, args[2].toString());
                    System.out.println(preparedStatement);
                    int rs = preparedStatement.executeUpdate();
                    preparedStatement.close();

                    sender.sendMessage(ChatColor.GREEN + "Ajout de " + ChatColor.DARK_GREEN + args[1] + ChatColor.GREEN + " pour le joueur " + ChatColor.DARK_GREEN + args[2]);

                    String p = args[2];

                    Player player1 = Bukkit.getPlayer(p);

                    player1.sendMessage(ChatColor.GREEN + "Ajout de " + ChatColor.DARK_GREEN + args[1] + ChatColor.GREEN + " par " + ChatColor.DARK_GREEN + sender.getName());


                } catch (SQLException e) {
                    System.out.println(e);
                    sender.sendMessage("/banque add amount pseudo");
                }

            }

            if(args[0].equalsIgnoreCase("remove")){

                try {

                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT `uuid`, `balance` FROM `banque` WHERE uuid = (?)");
                    preparedStatement.setString(1, sender.getName().toString());
                    ResultSet rs = preparedStatement.executeQuery();
                    rs.next();

                    int comtpe = rs.getInt(2);

                    int retret = Integer.parseInt(args[1]);

                    if(comtpe - retret >= 0){

                        try {

                            PreparedStatement preparedStaatement = connection.prepareStatement("UPDATE `banque` SET `balance` = (`balance` - ?) WHERE uuid = ?");
                            preparedStaatement.setString(1, args[1]);
                            preparedStaatement.setString(2, args[2].toString());
                            System.out.println(preparedStaatement);
                            int rrs = preparedStaatement.executeUpdate();
                            preparedStaatement.close();

                            sender.sendMessage(ChatColor.RED + "Retrait de " + ChatColor.DARK_RED +  args[1] + ChatColor.RED + " pour le joueur " + ChatColor.DARK_RED + args[2]);

                            String p = args[2];

                            Player player1 = Bukkit.getPlayer(p);

                            player1.sendMessage(ChatColor.RED + "Retrait de " + ChatColor.DARK_RED +  args[1] + ChatColor.RED + " par " + ChatColor.DARK_RED + sender.getName());


                        } catch (SQLException e) {
                            sender.sendMessage("/banque remove amount pseudo");
                        }

                    } else{
                        sender.sendMessage(ChatColor.DARK_RED + "Retrait Impossible, Le compte sera dans le négatif.");
                    }


                } catch (SQLException e) {
                    System.out.println(e);
                }


            }
        }
        return false;
    }
}
