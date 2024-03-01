package czescjestemadas.kmduels.utils;

import czescjestemadas.kmduels.kits.DuelKit;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

import java.util.Arrays;

public class ChatUtils
{
	public static Component mm(String str, TagResolver... resolvers)
	{
		return MiniMessage.miniMessage().deserialize(str, TagResolver.standard(), TagResolver.resolver(resolvers));
	}

	public static Component mmKit(String str, DuelKit kit)
	{
		return mm(
				str,
				Placeholder.unparsed("kit_name", kit.getName()),
				Placeholder.component("kit_displayname", kit.getDisplayname()),
				Placeholder.unparsed("kit_items", Arrays.toString(kit.getItems())),
				Placeholder.unparsed("kit_binded_maps", kit.getBindedMaps().toString()),
				Placeholder.unparsed("kit_icon", kit.getIcon().name())
		);
	}
}
