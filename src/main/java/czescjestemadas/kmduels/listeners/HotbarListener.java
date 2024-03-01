package czescjestemadas.kmduels.listeners;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.hotbar.HotbarState;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class HotbarListener implements Listener
{
	private final Duels duels;

	public HotbarListener(Duels duels)
	{
		this.duels = duels;
	}

	@EventHandler
	private void onClick(InventoryClickEvent e)
	{
		final DuelPlayer player = duels.getPlayerManager().getPlayer(e.getWhoClicked().getUniqueId());

		final HotbarState state = duels.getHotbarManager().getState(player);
		if (state.handleItems() /*&& todo read config prevent-moving-hotbar-items */)
			e.setCancelled(true);
	}

	@EventHandler
	private void onInteract(PlayerInteractEvent e)
	{
		if (!e.getAction().isRightClick())
			return;

		final DuelPlayer player = duels.getPlayerManager().getPlayer(e.getPlayer().getUniqueId());

		final HotbarState state = duels.getHotbarManager().getState(player);
		if (!state.handleItems())
			return;

		state.clickItem(duels, player, e.getPlayer().getInventory().getHeldItemSlot());
		e.setCancelled(true);
	}
}
