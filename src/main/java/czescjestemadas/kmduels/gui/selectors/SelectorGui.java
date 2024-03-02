package czescjestemadas.kmduels.gui.selectors;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.GuiConfig;
import czescjestemadas.kmduels.gui.utils.DuelGui;
import czescjestemadas.kmduels.gui.utils.DuelGuiButton;
import czescjestemadas.kmduels.gui.utils.DuelGuiElement;
import czescjestemadas.kmduels.players.DuelPlayer;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public abstract class SelectorGui implements DuelGui
{
	protected final Duels duels;
	protected final GuiConfig.Selector cfg;
	protected final Inventory inventory;
	protected final Map<Integer, DuelGuiElement> elements = new HashMap<>();

	public SelectorGui(Duels duels, GuiConfig.Selector cfg)
	{
		this.duels = duels;
		this.cfg = cfg;
		this.inventory = duels.getServer().createInventory(null, cfg.size, cfg.title);
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
