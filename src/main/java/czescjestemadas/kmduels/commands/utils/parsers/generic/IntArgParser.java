package czescjestemadas.kmduels.commands.utils.parsers.generic;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;

public class IntArgParser implements ArgParser<Integer>
{
	@Override
	public Integer get(String arg)
	{
		return Integer.parseInt(arg);
	}
}
