package czescjestemadas.kmduels.gui;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.gui.utils.DuelGui;
import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public class KitPreviewGui implements DuelGui
{
	private final DuelKit kit;
	private final Inventory inv;

	public KitPreviewGui(DuelKit kit)
	{
		this.kit = kit;
		this.inv = Bukkit.createInventory(null, 54, kit.getDisplayname());
	}

	@Override
	public void onOpen()
	{
		inv.setContents(kit.getItems());
	}

	@Override
	public void click(Duels duels, DuelPlayer player, int slot, ClickType type)
	{
	}

	@Override
	public Inventory getInventory()
	{
		return inv;
	}
}
