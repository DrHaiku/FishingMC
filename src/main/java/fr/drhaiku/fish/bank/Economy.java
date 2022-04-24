package fr.drhaiku.fish.bank;

import fr.drhaiku.fish.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Economy {

    public void addMoney(Player player, int i){

        try {
            Connection connection = Main.getInstance().database.getCo();

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `banque` SET `balance` = (`balance` + ?) WHERE uuid = ?");
            preparedStatement.setString(1, String.valueOf(i));
            preparedStatement.setString(2, player.getName());
            System.out.println(preparedStatement);
            int rs = preparedStatement.executeUpdate();
            preparedStatement.close();

            player.sendMessage(ChatColor.GREEN + "Ajout de " + ChatColor.DARK_GREEN + i + ChatColor.GREEN + " €" );


        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void remmoveMoney(Player player, int i){

        Connection connection = Main.getInstance().database.getCo();


        try {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT `uuid`, `balance` FROM `banque` WHERE uuid = (?)");
            preparedStatement.setString(1, player.getName());
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();

            int comtpe = rs.getInt(2);

            int retret = i;

            if(comtpe - retret >= 0){

                try {

                    PreparedStatement preparedStaatement = connection.prepareStatement("UPDATE `banque` SET `balance` = (`balance` - ?) WHERE uuid = ?");
                    preparedStaatement.setString(1, String.valueOf(i));
                    preparedStaatement.setString(2, player.getName());
                    System.out.println(preparedStaatement);
                    int rrs = preparedStaatement.executeUpdate();
                    preparedStaatement.close();


                    player.sendMessage(ChatColor.RED + "Retrait de " + ChatColor.DARK_RED +  i + ChatColor.RED + " €");


                } catch (SQLException e) {
                    System.out.println(e);
                }

            } else{

            }


        } catch (SQLException e) {
            System.out.println(e);
        }


    }


}
