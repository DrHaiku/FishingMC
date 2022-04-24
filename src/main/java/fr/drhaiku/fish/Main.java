package fr.drhaiku.fish;

import fr.drhaiku.fish.bank.BanqueCommand;
import fr.drhaiku.fish.bank.Economy;
import fr.drhaiku.fish.bank.PlayerJoin;
import fr.drhaiku.fish.commands.FishCommand;
import fr.drhaiku.fish.commands.VivierCommand;
import fr.drhaiku.fish.database.Account;
import fr.drhaiku.fish.database.DataBaseManager;
import fr.drhaiku.fish.event.EpuisetteEvent;
import fr.drhaiku.fish.event.FishingEvent;
import fr.drhaiku.fish.sop.FishSell;
import fr.drhaiku.fish.sop.Kit;
import fr.drhaiku.fish.sop.Poissonier;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class Main extends JavaPlugin {

    public static Main instance;
    public static List<Player> epuisette;
    public static int i = 0;
    public DataBaseManager database;
    public Economy eco;
    public FishSell fishSell;
    private Set<Account> accountSet;

    public static Main getInstance() {
        return instance;
    }

    public static boolean hasEpuisette(Player pLayer) {
        if (!pLayer.isInWater()) return false;
        ItemStack mainhand = pLayer.getInventory().getItemInMainHand();
        if (!mainhand.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Kit Epuisette")) return false;
        return true;
    }


    @Override
    public void onEnable() {


        epuisette = new ArrayList<>();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {

            if (i >= 40) {
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

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

        getCommand("banque").setExecutor(new BanqueCommand());

        getCommand("custom").setExecutor(new CustomCommand());


        getCommand("truitegive").setExecutor(new FishCommand());

        getServer().getPluginManager().registerEvents(new Poissonier(), this);
        getServer().getPluginManager().registerEvents(new Kit(), this);




        getCommand("vivier").setExecutor(new VivierCommand());


        database = new DataBaseManager("jdbc:mysql://", "87.106.169.47", "fishingmc", "fishingmc", "422nEsg*");
        accountSet = new HashSet<>();


        database.connexion();

        database = this.database;

        instance = this;

    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    @Override
    public void onDisable() {

    }

    public boolean HasAccount(String Pseudo){
        Connection connection = Main.getInstance().database.getCo();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT `uuid` FROM `banque` WHERE uuid = (?)");
            preparedStatement.setString(1, Pseudo.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createAccount(Player player) {
        Connection connection = Main.getInstance().database.getCo();
        if (!HasAccount(player.getName())) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `banque`(`uuid`) VALUES (?)");
                preparedStatement.setString(1, player.getName());
                preparedStatement.execute();

            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
