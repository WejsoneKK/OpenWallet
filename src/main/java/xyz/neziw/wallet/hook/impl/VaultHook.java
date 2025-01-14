package xyz.neziw.wallet.hook.impl;

import lombok.AllArgsConstructor;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.ServicePriority;
import xyz.neziw.wallet.WalletPlugin;
import xyz.neziw.wallet.hook.IHook;
import xyz.neziw.wallet.manager.DataManager;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class VaultHook implements IHook, Economy {

    private WalletPlugin plugin;
    private DataManager dataManager;

    @Override
    public void hook(WalletPlugin plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getServicesManager().register(Economy.class, this, this.plugin, ServicePriority.High);
    }

    @Override
    public void unHook() {
        throw new UnsupportedOperationException("Not supported yet."); // TODO : maybe do something with this ? !
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public String currencyNamePlural() {
        return "Balance"; // TODO - maybe configurable ? !
    }

    @Override
    public String currencyNameSingular() {
        return "Balance;"; // TODO - change this to something better
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        return this.depositPlayer(Bukkit.getOfflinePlayer(playerName), amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        this.dataManager.depositBalance(player.getName(), amount);
        return new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, "Success");
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        return null;
    }

    @Override
    public String format(double amount) {
        return null;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        return this.dataManager.getBalance(player.getName());
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        return this.dataManager.getBalance(player.getName());
    }

    @Override
    public double getBalance(String playerName) {
        return (Bukkit.getPlayer(playerName) != null) ? this.dataManager.getBalance(playerName) : 0.0D;
    }

    @Override
    public double getBalance(String playerName, String world) {
        return 0;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean has(String playerName, double amount) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return this.dataManager.getBalance(player.getName()) >= amount;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return false;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return false;
    }

    @Override
    public boolean hasAccount(String playerName) {
        return false;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return this.plugin != null;
    }

    @Override
    public String getName() {
        return this.plugin.getName();
    }

    @SuppressWarnings("DataFlowIssue")
    @Override
    public EconomyResponse withdrawPlayer(String string, double amount) {
        return withdrawPlayer(Bukkit.getPlayer(string), amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        this.dataManager.withDrawBalance(player.getName(), amount);
        return (getBalance(player) >= amount ? new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, "Success") : new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.FAILURE, "Error"));
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        this.dataManager.withDrawBalance(player.getName(), amount);
        return (getBalance(player) >= amount ? new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.SUCCESS, "Success") : new EconomyResponse(amount, getBalance(player), EconomyResponse.ResponseType.FAILURE, "Error"));
    }
}