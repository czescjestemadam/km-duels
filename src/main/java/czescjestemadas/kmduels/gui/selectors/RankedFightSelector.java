package czescjestemadas.kmduels.gui.selectors;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.gui.utils.DuelGuiButton;
import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class RankedFightSelector extends SelectorGui
{
	public RankedFightSelector(Duels duels)
	{
		super(duels, duels.getConfigManager().getGuiConfig().selectorsRankedFight);
	}

	@Override
	public void onOpen()
	{
		onUpdate();
	}

	@Override
	public void onUpdate()
	{
		inventory.clear();

		int idx = 0;
		for (String kitName : duels.getKitManager().getKitNames())
		{
			final DuelKit kit = duels.getKitManager().getKit(kitName);

			final ItemStack item = new ItemBuilder(kit.getIcon())
					.displayname(kit.getDisplayname())
					.lore(List.of(Component.text("lore1"), Component.text("lore2")))
					.build();

			final DuelGuiButton button = new DuelGuiButton(item, (d, p) -> duels.getQueueManager().queue(p, kit, true, true));
			elements.put(idx, button);
			inventory.setItem(idx, button.getItem());
			idx++;
		}
	}
}
