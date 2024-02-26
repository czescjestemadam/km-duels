package czescjestemadas.kmduels.listeners;

import czescjestemadas.kmduels.Duels;
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

	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	private void onQuit(PlayerQuitEvent e)
	{

	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onJoinLocation(PlayerSpawnLocationEvent e)
	{

	}
}
