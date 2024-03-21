package czescjestemadas.kmduels.commands.utils.parsers.paper;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ComponentArgParser implements ArgParser<Component>
{
	@Override
	public Component get(String arg)
	{
		return MiniMessage.miniMessage().deserialize(arg);
	}
}
