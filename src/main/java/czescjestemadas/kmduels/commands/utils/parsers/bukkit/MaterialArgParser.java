package czescjestemadas.kmduels.commands.utils.parsers.bukkit;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import org.bukkit.Material;

public class MaterialArgParser implements ArgParser<Material>
{
	@Override
	public Material get(String arg)
	{
		return Material.matchMaterial(arg);
	}
}
