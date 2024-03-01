package czescjestemadas.kmduels.hotbar;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public enum HotbarState
{
	LOBBY(List.of()),
	PARTY(List.of()),
	QUEUED(List.of()),
	FIGHT(List.of());

	private final List<HotbarItem> items;

	HotbarState(List<HotbarItem> items)
	{
		this.items = items;
	}

	public void setItems(PlayerInventory inventory)
	{
		inventory.clear();
		for (HotbarItem item : items)
			inventory.setItem(item.getSlot(), item.getItem());
	}

	public void clickItem(Duels duels, DuelPlayer player, int slot)
	{
		for (HotbarItem item : items)
		{
			if (item.getSlot() != slot)
				continue;

			final HotbarItemAction action = item.getAction();
			if (action == HotbarItemAction.COMMAND)
				item.getCommand().ifPresent(player::executeCommand);
			else
				action.execute(duels, player);
		}
	}

	public boolean handleItems()
	{
		return this != FIGHT;
	}
}
