package czescjestemadas.kmduels.gui.utils;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.Map;

public class SimpleGui implements DuelGui
{
	private final Inventory inventory;
	private Map<Integer, DuelGuiElement> elements;

	public SimpleGui(Inventory inventory, Map<Integer, DuelGuiElement> elements)
	{
		this.inventory = inventory;
		this.elements = elements;

		for (Map.Entry<Integer, DuelGuiElement> e : elements.entrySet())
			inventory.setItem(e.getKey(), e.getValue().getItem());
	}

	@Override
	public void click(Duels duels, DuelPlayer player, int slot, ClickType type)
	{
		for (Map.Entry<Integer, DuelGuiElement> e : elements.entrySet())
		{
			if (e.getKey() == slot && e.getValue() instanceof DuelGuiButton button)
			{
				button.click(duels, player, type);
				break;
			}
		}
	}

	@Override
	public Inventory getInventory()
	{
		return inventory;
	}
}
