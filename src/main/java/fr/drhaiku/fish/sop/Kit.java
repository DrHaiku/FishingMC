package fr.drhaiku.fish.sop;

import fr.drhaiku.fish.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Kit implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent e) {

        Player player = e.getPlayer();
        Entity entity = e.getRightClicked();

        if (entity instanceof ArmorStand) {
            if (entity.getName().equalsIgnoreCase("kit")) {

                Inventory kit = Bukkit.createInventory(null, 54, "Menu des Kits");

                player.openInventory(kit);

                kit.setItem(10, new ItemBuilder(Material.CARROT_ON_A_STICK).setCustomModelData(10).setName(ChatColor.BLUE + "Kit Epuisette").setLore(ChatColor.RED + "Click Here").toItemStack());
                kit.setItem(12, new ItemBuilder(Material.FISHING_ROD).setCustomModelData(1).setName(ChatColor.BLUE + "Kit Canne à Pêche [Débutant]").setLore(ChatColor.RED + "Click Here").toItemStack());

            }
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        ItemStack current = e.getCurrentItem();

        if (current == null) return;

        if (player.getOpenInventory().getTitle().equalsIgnoreCase("Menu des Kits")) {
            if (current.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Kit Epuisette")) {

                e.setCancelled(true);

                player.closeInventory();

                if (player.getInventory().contains(new ItemBuilder(Material.CARROT_ON_A_STICK).setCustomModelData(10).setName(ChatColor.BLUE + "Kit Epuisette").toItemStack())) {

                    player.sendMessage(ChatColor.RED + "Tu as déjà ce kit dans ton inventaire ! ");

                } else {
                    player.getInventory().addItem(new ItemBuilder(Material.CARROT_ON_A_STICK).setCustomModelData(10).setName(ChatColor.BLUE + "Kit Epuisette").toItemStack());
                }


            }

            if (current.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Kit Canne à Pêche [Débutant]")) {

                e.setCancelled(true);

                if (player.hasPermission("fishing.kit.debutant")) {

                    player.closeInventory();

                    if (player.getInventory().contains(new ItemBuilder(Material.FISHING_ROD).setCustomModelData(1).setName(ChatColor.BLUE + "Kit Canne à Pêche [Débutant]").toItemStack())) {

                        player.sendMessage(ChatColor.RED + "Tu as déjà ce kit dans ton inventaire ! ");

                    } else {
                        player.getInventory().addItem(new ItemBuilder(Material.FISHING_ROD).setCustomModelData(1).setName(ChatColor.BLUE + "Canne à Pêche [Débutant]").toItemStack());
                    }

                }

            }

        }
    }
}
