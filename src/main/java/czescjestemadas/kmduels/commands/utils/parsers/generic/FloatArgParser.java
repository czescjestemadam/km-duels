package czescjestemadas.kmduels.commands.utils.parsers.generic;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;

public class FloatArgParser implements ArgParser<Float>
{
	@Override
	public Float get(String arg)
	{
		return Float.parseFloat(arg);
	}
}
