package czescjestemadas.kmduels.commands.utils.parsers.bukkit;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import org.bukkit.Sound;

public class SoundArgParser implements ArgParser<Sound>
{
	@Override
	public Sound get(String arg)
	{
		return Sound.valueOf(arg);
	}
}
