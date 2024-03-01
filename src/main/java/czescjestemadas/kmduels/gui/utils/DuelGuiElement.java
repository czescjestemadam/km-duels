package czescjestemadas.kmduels.gui.utils;

import org.bukkit.inventory.ItemStack;

public class DuelGuiElement
{
	protected final ItemStack item;

	public DuelGuiElement(ItemStack item)
	{
		this.item = item;
	}

	public ItemStack getItem()
	{
		return item;
	}


	@Override
	public String toString()
	{
		return "DuelGuiElement{" +
				"item=" + item +
				'}';
	}
}
