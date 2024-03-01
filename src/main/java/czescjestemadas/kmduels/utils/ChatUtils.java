package czescjestemadas.kmduels.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;

public class ChatUtils
{
	public static Component mm(String str, TagResolver... resolvers)
	{
		return MiniMessage.miniMessage().deserialize(str, TagResolver.standard(), TagResolver.resolver(resolvers));
	}
}
