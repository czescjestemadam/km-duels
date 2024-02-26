package czescjestemadas.kmduels.maps;

import czescjestemadas.kmduels.Duels;
import net.kyori.adventure.text.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class MapManager
{
	private final Duels duels;
	private final File mapsDir;
	private final List<DuelMap> maps = new ArrayList<>();

	public MapManager(Duels duels)
	{
		this.duels = duels;
		mapsDir = new File(duels.getDataFolder(), "maps");
		mapsDir.mkdirs();
	}

	public void loadMaps()
	{

	}

	public void saveMaps()
	{

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
