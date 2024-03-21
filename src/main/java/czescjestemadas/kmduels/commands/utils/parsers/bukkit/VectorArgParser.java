package czescjestemadas.kmduels.commands.utils.parsers.bukkit;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import org.bukkit.util.Vector;

public class VectorArgParser implements ArgParser<Vector>
{
	@Override
	public Vector get(String arg)
	{
		if (!arg.contains(","))
			throw new IllegalArgumentException("<x,y,z>");

		final String[] args = arg.split(",");
		if (args.length != 3)
			throw new IllegalArgumentException("<x,y,z>");

		return new Vector(Double.parseDouble(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]));
	}
}
