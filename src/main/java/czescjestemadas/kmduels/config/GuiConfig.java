package czescjestemadas.kmduels.config;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class GuiConfig implements Config
{
	public Selector selectorsRankedFight;
	public Selector selectorsKitEditor;
	public Selector selectorsSpectate;
	public Selector selectorsPartyFight;
	public Selector selectorsPartySettings;

	@Override
	public void load(ConfigurationSection cfg)
	{
		final ConfigurationSection selectors = cfg.getConfigurationSection("selectors");
		selectorsRankedFight = Selector.load(selectors.getConfigurationSection("ranked-fight"));
		selectorsKitEditor = Selector.load(selectors.getConfigurationSection("kit-editor"));
		selectorsSpectate = Selector.load(selectors.getConfigurationSection("spectate"));
		selectorsPartyFight = Selector.load(selectors.getConfigurationSection("party-fight"));
		selectorsPartySettings = Selector.load(selectors.getConfigurationSection("party-settings"));
	}

	
	public static class Selector
	{
		public int size;
		public Component title;

		public Selector(int size, Component title)
		{
			this.size = size;
			this.title = title;
		}

		private static Selector load(ConfigurationSection cfg)
		{
			return new Selector(cfg.getInt("size"), miniMessage().deserialize(cfg.getString("title")));
		}
	}
}
