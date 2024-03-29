package czescjestemadas.kmduels.config;

import czescjestemadas.kmduels.Duels;
import org.bukkit.configuration.file.FileConfiguration;

public final class ConfigManager
{
	private final Duels duels;

	private final GuiConfig guiConfig = new GuiConfig();
	private final KitsConfig kitsConfig = new KitsConfig();
	private final MapsConfig mapsConfig = new MapsConfig();
	private final PartyConfig partyConfig = new PartyConfig();
	private final QueueConfig queueConfig = new QueueConfig();
	private final MessagesConfig messagesConfig = new MessagesConfig();

	public ConfigManager(Duels duels)
	{
		this.duels = duels;
		duels.saveDefaultConfig();
	}

	public void load()
	{
		duels.reloadConfig();
		final FileConfiguration cfg = duels.getConfig();

		guiConfig.load(cfg.getConfigurationSection("gui"));
		kitsConfig.load(cfg.getConfigurationSection("kits"));
		mapsConfig.load(cfg.getConfigurationSection("maps"));
		partyConfig.load(cfg.getConfigurationSection("party"));
		queueConfig.load(cfg.getConfigurationSection("queue"));
		messagesConfig.load(cfg.getConfigurationSection("messages"));
	}

	public GuiConfig getGuiConfig()
	{
		return guiConfig;
	}

	public KitsConfig getKitsConfig()
	{
		return kitsConfig;
	}

	public MapsConfig getMapsConfig()
	{
		return mapsConfig;
	}

	public PartyConfig getPartyConfig()
	{
		return partyConfig;
	}

	public QueueConfig getQueueConfig()
	{
		return queueConfig;
	}

	public MessagesConfig getMessagesConfig()
	{
		return messagesConfig;
	}
}
