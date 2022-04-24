package fr.drhaiku.fish;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CustomCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(label.equalsIgnoreCase("custom")){

            int num = Integer.parseInt(args[1]);
            String material = args[0];

            Player player = (Player) sender;

            player.getInventory().addItem(new ItemBuilder(Material.getMaterial(material)).setCustomModelData(num).toItemStack());
        }

        return false;
    }
}
