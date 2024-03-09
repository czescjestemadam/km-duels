package czescjestemadas.kmduels.listeners;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.party.DuelParty;
import czescjestemadas.kmduels.players.DuelPlayer;
import czescjestemadas.kmduels.utils.ChatUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PartyChatListener implements Listener
{
	private final Duels duels;

	public PartyChatListener(Duels duels)
	{
		this.duels = duels;
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	private void onPartyChat(AsyncPlayerChatEvent e)
	{
		final String prefix = duels.getConfigManager().getPartyConfig().chatPrefix;
		if (prefix.isEmpty())
			return;

		if (!e.getMessage().startsWith(prefix))
			return;

		final DuelPlayer player = duels.getPlayerManager().getPlayer(e.getPlayer().getUniqueId());
		final DuelParty party = duels.getPartyManager().getParty(player);
		if (party == null)
			return;

		final Component msg = ChatUtils.mm(
				duels.getConfigManager().getPartyConfig().chatFormat,
				Placeholder.unparsed("sender", player.getPlayer().getName()),
				Placeholder.unparsed("message", e.getMessage())
		);

		for (DuelPlayer partyPlayer : party.getPlayers())
			partyPlayer.getPlayer().sendMessage(msg);

		e.setCancelled(true);
	}
}
