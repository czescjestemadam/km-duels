package czescjestemadas.kmduels.utils;

import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.maps.DuelMap;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Location;

import java.util.Arrays;
import java.util.List;

public class ChatUtils
{
	public static Component mm(String str, TagResolver... resolvers)
	{
		return MiniMessage.miniMessage().deserialize(str, TagResolver.standard(), TagResolver.resolver(resolvers));
	}

	public static Component mmKit(String str, DuelKit kit)
	{
		return mm(str, kitResolver(kit));
	}

	public static Component mmMap(String str, DuelMap map)
	{
		return mm(str, mapResolver(map));
	}


	public static TagResolver kitResolver(DuelKit kit)
	{
		return TagResolver.resolver(
				Placeholder.unparsed("kit_name", kit.getName()),
				Placeholder.component("kit_displayname", kit.getDisplayname()),
				Placeholder.unparsed("kit_items", Arrays.toString(kit.getItems())),
				Placeholder.unparsed("kit_binded_maps", kit.getBindedMaps().toString()),
				Placeholder.unparsed("kit_icon", kit.getIcon().name())
		);
	}

	public static TagResolver mapResolver(DuelMap map)
	{
		final List<Location> spawnPos = map.getSpawnPositions();
		return TagResolver.resolver(
				Placeholder.unparsed("map_name", map.getName()),
				Placeholder.component("map_displayname", map.getDisplayname()),
				Placeholder.unparsed("map_world", map.getWorld().getName()),
				Placeholder.unparsed("map_point_a", map.getPointA().toString()),
				Placeholder.unparsed("map_point_b", map.getPointB().toString()),
				Placeholder.unparsed("map_spawn_positions", spawnPos.toString()),
				Placeholder.unparsed("map_last_spawn_position", spawnPos.isEmpty() ? "null" : spawnPos.get(spawnPos.size() - 1).toString())
		);
	}
}
