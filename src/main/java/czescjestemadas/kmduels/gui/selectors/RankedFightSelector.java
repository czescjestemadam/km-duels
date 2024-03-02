package czescjestemadas.kmduels.gui.selectors;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.gui.KitPreviewGui;
import czescjestemadas.kmduels.gui.utils.DuelGuiButton;
import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.utils.ChatUtils;
import czescjestemadas.kmduels.utils.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

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

			final TagResolver resolver = ChatUtils.kitSelectorResolver(duels, kit);
			final List<Component> lore = cfg.itemsLore.orElse(List.of()).stream()
					.map(line -> ChatUtils.mm(line, resolver))
					.toList();

			final DuelGuiButton button = new DuelGuiButton(
					new ItemBuilder(kit.getIcon()).displayname(kit.getDisplayname()).lore(lore).build(),
					(d, player) -> duels.getQueueManager().queue(player, kit, true),
					(d, player) -> duels.getGuiManager().open(player, new KitPreviewGui(kit))
			);
			elements.put(idx, button);
			inventory.setItem(idx, button.getItem());
			idx++;
		}
	}
}
