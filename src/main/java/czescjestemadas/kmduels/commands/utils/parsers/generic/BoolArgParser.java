package czescjestemadas.kmduels.commands.utils.parsers.generic;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import czescjestemadas.kmduels.utils.StrUtils;

public class BoolArgParser implements ArgParser<Boolean>
{
	@Override
	public Boolean get(String arg)
	{
		return StrUtils.argEquals(arg, "true", "yes");
	}
}
