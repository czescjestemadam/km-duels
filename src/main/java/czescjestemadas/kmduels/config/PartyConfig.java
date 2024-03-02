package czescjestemadas.kmduels.config;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class PartyConfig implements Config
{
	public int playerLimit;
	public String chatPrefix;

	public Component msgCreated;
	public Component msgRemoved;

	@Override
	public void load(ConfigurationSection cfg)
	{
		playerLimit = cfg.getInt("player-limit");
		chatPrefix = cfg.getString("chat-prefix");

		final ConfigurationSection msg = cfg.getConfigurationSection("messages");
		msgCreated = miniMessage().deserialize(msg.getString("created"));
		msgRemoved = miniMessage().deserialize(msg.getString("removed"));
	}
}
