package czescjestemadas.kmduels.commands.utils.parsers.generic;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;

public class DoubleArgParser implements ArgParser<Double>
{
	@Override
	public Double get(String arg)
	{
		return Double.parseDouble(arg);
	}
}
