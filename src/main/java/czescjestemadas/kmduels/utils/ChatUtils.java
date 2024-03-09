package czescjestemadas.kmduels.utils;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.kits.DuelKit;
import czescjestemadas.kmduels.maps.DuelMap;
import czescjestemadas.kmduels.party.DuelParty;
import czescjestemadas.kmduels.players.DuelPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Location;
import org.bukkit.entity.Player;

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

	public static Component mmParty(String str, DuelParty party)
	{
		return mm(str, partyResolver(party));
	}


	public static TagResolver kitResolver(DuelKit kit)
	{
		return TagResolver.resolver(
				Placeholder.unparsed("kit_name", kit.getName()),
				Placeholder.component("kit_displayname", kit.getDisplayname()),
				Placeholder.unparsed("kit_items", Arrays.toString(kit.getItems())),
				Placeholder.unparsed("kit_binded_maps", kit.getBindedMaps() + ""),
				Placeholder.unparsed("kit_icon", kit.getIcon() + "")
		);
	}

	public static TagResolver kitSelectorResolver(Duels duels, DuelKit kit)
	{
		final String queued = duels.getQueueManager().getOrCreateQueue(kit).getEntries().size() + "";
		final String fighting = duels.getFightManager().getFights().stream().filter(f -> f.getKit().equals(kit)).count() + "";

		return TagResolver.resolver(
				Placeholder.unparsed("kit_players_queued", queued),
				Placeholder.unparsed("kit_players_fighting", fighting)
		);
	}

	public static TagResolver mapResolver(DuelMap map)
	{
		final List<Location> spawnPos = map.getSpawnPositions();
		return TagResolver.resolver(
				Placeholder.unparsed("map_name", map.getName()),
				Placeholder.component("map_displayname", map.getDisplayname()),
				Placeholder.unparsed("map_world", map.getWorldName()),
				Placeholder.unparsed("map_point_a", map.getPointA() + ""),
				Placeholder.unparsed("map_point_b", map.getPointB() + ""),
				Placeholder.unparsed("map_spawn_positions", spawnPos + ""),
				Placeholder.unparsed("map_last_spawn_position", spawnPos.isEmpty() ? "null" : spawnPos.get(spawnPos.size() - 1).toString()),
				Placeholder.unparsed("map_icon", map.getIcon() + "")
		);
	}

	public static TagResolver partyResolver(DuelParty party)
	{
		final String members = String.join(", ", party.getPlayers().stream().map(DuelPlayer::getPlayer).map(Player::getName).toList());
		return TagResolver.resolver(
				Placeholder.unparsed("party_owner", party.getOwner().getPlayer().getName()),
				Placeholder.unparsed("party_membercount", party.getPlayers().size() + ""),
				Placeholder.unparsed("party_members", members),
				Placeholder.unparsed("party_name", party.getName())
		);
	}
}
