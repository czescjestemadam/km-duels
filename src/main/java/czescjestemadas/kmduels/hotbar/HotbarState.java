package czescjestemadas.kmduels.hotbar;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;

public enum HotbarState
{
	LOBBY(List.of(
			new HotbarItem(0, new ItemStack(Material.DIAMOND_SWORD), HotbarItemAction.OPEN_RANKED_FIGHT_SELECTOR),
			new HotbarItem(1, new ItemStack(Material.BOOK), HotbarItemAction.OPEN_KIT_EDITOR),
			new HotbarItem(4, new ItemStack(Material.END_CRYSTAL), "/say open player panel command"),
			new HotbarItem(7, new ItemStack(Material.ENDER_PEARL), HotbarItemAction.OPEN_SPECTATOR_SELECTOR),
			new HotbarItem(8, new ItemStack(Material.NOTE_BLOCK), HotbarItemAction.CREATE_PARTY)
	)),
	PARTY(List.of()),
	QUEUED(List.of(new HotbarItem(8, new ItemStack(Material.BARRIER), HotbarItemAction.LEAVE_QUEUE))),
	FIGHT(List.of()),
	UNHANDLED(List.of());

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

			break;
		}
	}

	public boolean handleItems()
	{
		return !items.isEmpty();
	}
}
