package xyz.neziw.wallet.listener;

import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import xyz.neziw.wallet.manager.DatabaseManager;
import xyz.neziw.wallet.manager.UserManager;
import xyz.neziw.wallet.object.WalletUser;

@AllArgsConstructor
public class PlayerQuitListener implements Listener {

    private final UserManager userManager;
    private final DatabaseManager databaseManager;

    @SuppressWarnings("unused")
    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        final WalletUser user = this.userManager.getUser(player.getUniqueId());
        this.databaseManager.saveUser(user);
        this.userManager.removeUser(player.getUniqueId());
    }
}