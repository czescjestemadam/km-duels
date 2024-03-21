package czescjestemadas.kmduels.commands.utils.parsers.bukkit;

import czescjestemadas.kmduels.commands.utils.parsers.ArgParser;
import org.bukkit.Particle;

public class ParticleArgParser implements ArgParser<Particle>
{
	@Override
	public Particle get(String arg)
	{
		return Particle.valueOf(arg);
	}
}
