package czescjestemadas.kmduels.kits;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DuelKit
{
	private final String name;
	private Component displayname;
	private ItemStack[] items;
	private List<String> bindedMaps = new ArrayList<>();
	private Material icon = Material.DIAMOND_SWORD;

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

	public List<String> getBindedMaps()
	{
		return bindedMaps;
	}

	public void setBindedMaps(List<String> bindedMaps)
	{
		this.bindedMaps = bindedMaps;
	}

	public Material getIcon()
	{
		return icon;
	}

	public void setIcon(Material icon)
	{
		this.icon = icon;
	}


	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof DuelKit kit ? kit.getName().equals(name) : super.equals(obj);
	}

	@Override
	public String toString()
	{
		return "DuelKit{" +
				"name='" + name + '\'' +
				", displayname=" + displayname +
				", items=" + Arrays.toString(items) +
				", bindedMaps=" + bindedMaps +
				", icon=" + icon +
				'}';
	}
}
