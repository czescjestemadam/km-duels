package czescjestemadas.kmduels.commands.utils.parsers.generic;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;

public class LongArgParser implements ArgParser<Long>
{
	@Override
	public Long get(String arg)
	{
		return Long.parseLong(arg);
	}
}
