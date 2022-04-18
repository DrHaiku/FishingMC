package fr.drhaiku.fish;

import fr.drhaiku.fish.commands.VivierCommand;
import fr.drhaiku.fish.database.Account;
import fr.drhaiku.fish.database.DataBaseManager;
import fr.drhaiku.fish.event.EpuisetteEvent;
import fr.drhaiku.fish.event.FishingEvent;
import fr.drhaiku.fish.sop.Poissonier;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Main extends JavaPlugin {

    public DataBaseManager database;
    private Set<Account> accountSet;
    public static List<Player> epuisette;
    public static Main instance;
    public static int i = 0;
    public static Main getInstance() {return instance; }

    @Override
    public void onEnable() {
        epuisette = new ArrayList<>();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () ->{

            if(i >= 40){
                i = 0;
            }

            i++;
            for (Player player : epuisette) {
                if (hasEpuisette(player)) {
                    if (i >= 40) {
                        // ajouter item
                        player.getInventory().addItem(new ItemStack(new ItemBuilder(Material.COD).setName(ChatColor.GOLD + "Eperlans Crues").toItemStack()));
                    }
                } else {
                    epuisette.remove(player);
                }
            }
        }, 10L, 5L);


        loadConfig();

        System.out.println("Plugin Opérationnel !");

        getServer().getPluginManager().registerEvents(new FishingEvent(), this);
        getServer().getPluginManager().registerEvents(new EpuisetteEvent(), this);

        getServer().getPluginManager().registerEvents(new Poissonier(), this);

        getCommand("vivier").setExecutor(new VivierCommand());


        database = new DataBaseManager("jdbc:mysql://", "51.178.8.74", "minesr_129s8two", "minesr_129s8two", "SAo7JJMBcfcwnhMY");
        accountSet = new HashSet<>();


        database.connexion();

        database = this.database;


        instance = this;

    }

    private void loadConfig(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {

    }

    public static boolean hasEpuisette(Player pLayer) {
        if(!pLayer.isInWater()) return false;
        ItemStack mainhand = pLayer.getInventory().getItemInMainHand();
        if(!mainhand.getItemMeta().getDisplayName().equalsIgnoreCase("Epuisette du Pêcheur")) return false;
        return true;
    }
}
