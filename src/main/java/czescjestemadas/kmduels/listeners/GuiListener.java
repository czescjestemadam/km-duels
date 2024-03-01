package czescjestemadas.kmduels.listeners;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class GuiListener implements Listener
{
	private final Duels duels;

	public GuiListener(Duels duels)
	{
		this.duels = duels;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	private void onClick(InventoryClickEvent e)
	{
		if (e.getClick().isKeyboardClick())
			return;

		final DuelPlayer player = duels.getPlayerManager().getPlayer(e.getWhoClicked().getUniqueId());
		if (duels.getGuiManager().tryClick(player, e.getSlot()))
			e.setCancelled(true);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	private void onClose(InventoryCloseEvent e)
	{
		final DuelPlayer player = duels.getPlayerManager().getPlayer(e.getPlayer().getUniqueId());
		duels.getGuiManager().onClose(player);
	}
}
