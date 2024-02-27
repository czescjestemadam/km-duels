package czescjestemadas.kmduels.listeners;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import czescjestemadas.kmduels.players.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class JoinQuitListener implements Listener
{
	private final Duels duels;

	public JoinQuitListener(Duels duels)
	{
		this.duels = duels;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	private void onJoin(PlayerJoinEvent e)
	{
		final DuelPlayer player = new DuelPlayer(e.getPlayer().getUniqueId());
		duels.getPlayerManager().loadPlayer(player);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	private void onQuit(PlayerQuitEvent e)
	{
		final PlayerManager playerManager = duels.getPlayerManager();

		final DuelPlayer player = playerManager.getPlayer(e.getPlayer().getUniqueId());
		if (player == null)
			return;

		playerManager.savePlayer(player);
		playerManager.unloadPlayer(player);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onJoinLocation(PlayerSpawnLocationEvent e)
	{

	}
}
