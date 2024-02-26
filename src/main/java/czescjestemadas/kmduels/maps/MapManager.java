package czescjestemadas.kmduels.maps;

import czescjestemadas.kmduels.Duels;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public final class MapManager
{
	private final Duels duels;
	private final File mapsFile;
	private final List<DuelMap> maps = new ArrayList<>();

	public MapManager(Duels duels)
	{
		this.duels = duels;
		mapsFile = new File(duels.getDataFolder(), "maps.yml");
	}

	public void loadMaps()
	{
		if (!mapsFile.exists())
			return;

		final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(mapsFile);
		for (String name : cfg.getKeys(false))
		{
			final ConfigurationSection cfgMap = cfg.getConfigurationSection(name);

			final DuelMap map = new DuelMap(name, MiniMessage.miniMessage().deserialize(cfgMap.getString("displayname")));
			map.setWorld(duels.getServer().getWorld(cfgMap.getString("world")));
			map.setPointA(cfgMap.getVector("point-a"));
			map.setPointB(cfgMap.getVector("point-b"));

			maps.add(map);
		}
	}

	public void saveMaps()
	{
		if (maps.isEmpty())
			return;

		final YamlConfiguration cfg = new YamlConfiguration();
		for (DuelMap map : maps)
		{
			final ConfigurationSection cfgMap = cfg.createSection(map.getName());
			cfgMap.set("displayname", MiniMessage.miniMessage().serialize(map.getDisplayname()));
			cfgMap.set("world", map.getWorld().getName());
			cfgMap.set("point-a", map.getPointA());
			cfgMap.set("point-b", map.getPointB());
		}

		try
		{
			cfg.save(mapsFile);
		}
		catch (IOException e)
		{
			duels.getLogger().log(Level.SEVERE, "cant save maps", e);
		}
	}

	public List<String> getMapNames()
	{
		return maps.stream().map(DuelMap::getName).toList();
	}

	public DuelMap getMap(String name)
	{
		for (DuelMap map : maps)
		{
			if (map.getName().equalsIgnoreCase(name))
				return map;
		}

		return null;
	}

	public boolean createMap(String name)
	{
		if (getMap(name) != null)
			return false;

		return maps.add(new DuelMap(name, Component.text(name)));
	}

	public boolean removeMap(String name)
	{
		final DuelMap map = getMap(name);
		if (map == null)
			return false;

		return maps.remove(map);
	}
}
