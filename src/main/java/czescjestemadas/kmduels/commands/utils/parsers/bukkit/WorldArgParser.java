package czescjestemadas.kmduels.commands.utils.parsers.bukkit;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class WorldArgParser implements ArgParser<World>
{
	@Override
	public World get(String arg)
	{
		return Bukkit.getWorld(arg);
	}
}
