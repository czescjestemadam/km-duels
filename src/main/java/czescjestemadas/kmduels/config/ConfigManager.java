package czescjestemadas.kmduels.config;

import czescjestemadas.kmduels.Duels;
import org.bukkit.configuration.file.FileConfiguration;

public final class ConfigManager
{
	private final Duels duels;

	private final GuiConfig guiConfig = new GuiConfig();
	private final KitsConfig kitsConfig = new KitsConfig();
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

	public QueueConfig getQueueConfig()
	{
		return queueConfig;
	}

	public MessagesConfig getMessagesConfig()
	{
		return messagesConfig;
	}
}
