package czescjestemadas.kmduels.gui.utils;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class SimpleGuiBuilder
{
	private final Inventory inv;
	private final Map<Integer, DuelGuiElement> elements = new HashMap<>();

	public SimpleGuiBuilder(Inventory inv)
	{
		this.inv = inv;
	}

	public SimpleGuiBuilder(int size, Component title)
	{
		inv = Bukkit.createInventory(null, size, title);
	}

	public SimpleGuiBuilder addElement(int slot, ItemStack item)
	{
		elements.put(slot, new DuelGuiElement(item));
		return this;
	}

	public SimpleGuiBuilder addButton(int slot, ItemStack item, BiConsumer<Duels, DuelPlayer> onClick)
	{
		elements.put(slot, new DuelGuiButton(item, onClick));
		return this;
	}

	public SimpleGuiBuilder fillElement(int slotMin, int slotMax, ItemStack item)
	{
		for (int i = slotMin; i <= slotMax; i++)
			addElement(i, item);
		return this;
	}

	public SimpleGuiBuilder fillButton(int slotMin, int slotMax, ItemStack item, BiConsumer<Duels, DuelPlayer> onClick)
	{
		for (int i = slotMin; i <= slotMax; i++)
			addButton(i, item, onClick);
		return this;
	}

	public SimpleGui build()
	{
		return new SimpleGui(inv, elements);
	}
}
