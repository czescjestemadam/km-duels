package czescjestemadas.kmduels.listeners;

import czescjestemadas.kmduels.Duels;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener
{
	private final Duels duels;

	public InventoryListener(Duels duels)
	{
		this.duels = duels;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	private void onClick(InventoryClickEvent e)
	{

	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	private void onClose(InventoryCloseEvent e)
	{

	}
}
