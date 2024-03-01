package czescjestemadas.kmduels.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.function.Consumer;

public class ItemBuilder
{
	private final ItemStack item;

	public ItemBuilder(ItemStack item)
	{
		this.item = item;
	}

	public ItemBuilder(Material material)
	{
		item = new ItemStack(material);
	}

	public ItemBuilder displayname(Component component)
	{
		item.editMeta(meta -> meta.displayName(component));
		return this;
	}

	public ItemBuilder lore(List<? extends Component> lore)
	{
		item.editMeta(meta -> meta.lore(lore));
		return this;
	}

	public ItemBuilder meta(Consumer<ItemMeta> func)
	{
		item.editMeta(func);
		return this;
	}

	public ItemStack build()
	{
		return item.clone();
	}


	@Override
	public String toString()
	{
		return "ItemBuilder{" +
				"item=" + item +
				'}';
	}
}
