package czescjestemadas.kmduels.listeners;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.hotbar.HotbarState;
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
		final PlayerManager playerManager = duels.getPlayerManager();
		final DuelPlayer player = playerManager.createPlayer(e.getPlayer().getUniqueId());
		playerManager.loadPlayer(player);

		duels.getHotbarManager().setState(player, HotbarState.LOBBY);
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	private void onQuit(PlayerQuitEvent e)
	{
		final PlayerManager playerManager = duels.getPlayerManager();
		final DuelPlayer player = playerManager.getPlayer(e.getPlayer().getUniqueId());
		playerManager.savePlayer(player);
		playerManager.unloadPlayer(player);

		duels.getHotbarManager().unsetState(player);

		duels.getQueueManager().leaveQueue(player, null, false);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onJoinLocation(PlayerSpawnLocationEvent e)
	{

	}
}
