package czescjestemadas.kmduels.kits;

import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class DuelKit
{
	private final String name;
	private Component displayname;
	private ItemStack[] items;

	public DuelKit(String name, Component displayname, ItemStack[] items)
	{
		this.name = name;
		this.displayname = displayname;
		this.items = items;
	}

	public String getName()
	{
		return name;
	}

	public Component getDisplayname()
	{
		return displayname;
	}

	public void setDisplayname(Component displayname)
	{
		this.displayname = displayname;
	}

	public ItemStack[] getItems()
	{
		return items;
	}

	public void setItems(ItemStack[] items)
	{
		this.items = items;
	}


	@Override
	public String toString()
	{
		return "DuelKit{" +
				"name='" + name + '\'' +
				", displayname=" + displayname +
				", items=" + Arrays.toString(items) +
				'}';
	}
}
