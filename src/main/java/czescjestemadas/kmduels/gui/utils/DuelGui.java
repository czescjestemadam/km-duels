package czescjestemadas.kmduels.gui.utils;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public interface DuelGui
{
	void click(Duels duels, DuelPlayer player, int slot, ClickType type);

	default void onOpen() {}

	default void onUpdate() {}

	Inventory getInventory();
}
