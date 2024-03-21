package czescjestemadas.kmduels.commands.utils.parsers;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.commands.utils.parsers.bukkit.*;
import czescjestemadas.kmduels.commands.utils.parsers.duels.DuelKitArgParser;
import czescjestemadas.kmduels.commands.utils.parsers.duels.DuelMapArgParser;
import czescjestemadas.kmduels.commands.utils.parsers.duels.DuelPlayerArgParser;
import czescjestemadas.kmduels.commands.utils.parsers.generic.*;
import czescjestemadas.kmduels.commands.utils.parsers.paper.ComponentArgParser;
import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.maps.DuelMap;
import czescjestemadas.kmduels.players.DuelPlayer;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class ArgParsers
{
	private static final Map<Class<?>, ArgParser<?>> PARSERS = new HashMap<>();

	public static <T> void register(Class<T> cls, ArgParser<T> parser)
	{
		PARSERS.put(cls, parser);
	}

	public static void register(Duels duels)
	{
		// bukkit
		register(Material.class, new MaterialArgParser());
		register(OfflinePlayer.class, new OfflinePlayerArgParser());
		register(Particle.class, new ParticleArgParser());
		register(Player.class, new PlayerArgParser());
		register(Sound.class, new SoundArgParser());
		register(Vector.class, new VectorArgParser());
		register(World.class, new WorldArgParser());

		// duels
		register(DuelKit.class, new DuelKitArgParser(duels));
		register(DuelMap.class, new DuelMapArgParser(duels));
		register(DuelPlayer.class, new DuelPlayerArgParser(duels));

		// generic
		register(Boolean.class, new BoolArgParser());
		register(Double.class, new DoubleArgParser());
		register(Float.class, new FloatArgParser());
		register(Integer.class, new IntArgParser());
		register(Long.class, new LongArgParser());

		// paper
		register(Component.class, new ComponentArgParser());
	}

	public static <T> ArgParser<T> getFor(Class<T> cls)
	{
		return (ArgParser<T>)PARSERS.get(cls);
	}
}
