package czescjestemadas.kmduels.config;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class GuiConfig implements Config
{
	public final Map<String, Integer> hotbarSlots = new HashMap<>();

	public final Selector selectorKit = new Selector();
	public final Selector selectorMap = new Selector();
	public final Selector selectorFight = new Selector();


	@Override
	public void load(ConfigurationSection cfg)
	{
		final ConfigurationSection cfgHotbarSlots = cfg.getConfigurationSection("hotbar-slots");
		for (String key : cfgHotbarSlots.getKeys(false))
			hotbarSlots.put(key, cfgHotbarSlots.getInt(key));

		selectorKit.load(cfg.getConfigurationSection("selector.kit"));
		selectorMap.load(cfg.getConfigurationSection("selector.map"));
		selectorFight.load(cfg.getConfigurationSection("selector.fight"));
	}

	public static class Selector
	{
		public int size;
		public Component title;

		public void load(ConfigurationSection cfg)
		{
			size = cfg.getInt("size");
			title = MiniMessage.miniMessage().deserialize(cfg.getString("title"));
		}
	}
}
