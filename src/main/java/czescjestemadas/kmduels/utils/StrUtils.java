package czescjestemadas.kmduels.utils;

import java.util.Arrays;
import java.util.List;

public class StrUtils
{
	public static List<String> retMatches(String arg, List<String> args)
	{
		return args.stream().filter(s -> s.length() >= arg.length() && s.regionMatches(true, 0, arg, 0, arg.length())).toList();
	}

	public static List<String> retMatches(String arg, String... args)
	{
		return retMatches(arg, Arrays.asList(args));
	}
}
