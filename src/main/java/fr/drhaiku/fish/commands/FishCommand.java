package fr.drhaiku.fish.commands;

import fr.drhaiku.fish.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("truitegive")){

            Player player = (Player) sender;

            player.getInventory().addItem(new ItemBuilder(Material.COD).setName("Truite").setCustomModelData(1).toItemStack());

        }

        return false;
    }
}
