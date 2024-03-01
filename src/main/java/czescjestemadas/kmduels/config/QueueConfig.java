package czescjestemadas.kmduels.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class QueueConfig implements Config
{
	public int matchFinderInterval;
	public int maxPing;

	public final List<EloMatching> eloMatchings = new ArrayList<>();

	public Component msgEnter;
	public Component msgLeave;
	public String msgSearching;

	@Override
	public void load(ConfigurationSection cfg)
	{
		matchFinderInterval = cfg.getInt("match-finder-interval");
		maxPing = cfg.getInt("max-ping");

		final ConfigurationSection cfgEloMatching = cfg.getConfigurationSection("elo-matching");
		for (String key : cfgEloMatching.getKeys(false))
			eloMatchings.add(EloMatching.load(cfg.getConfigurationSection(key)));

		final ConfigurationSection msg = cfg.getConfigurationSection("messages");
		msgEnter = miniMessage().deserialize(msg.getString("enter"));
		msgLeave = miniMessage().deserialize(msg.getString("leave"));
		msgSearching = msg.getString("searching");
	}


	public static class EloMatching
	{
		public int minTime;
		public int range;

		public EloMatching(int minTime, int range)
		{
			this.minTime = minTime;
			this.range = range;
		}

		public static EloMatching load(ConfigurationSection cfg)
		{
			return new EloMatching(cfg.getInt("min-time"), cfg.getInt("range"));
		}
	}
}
