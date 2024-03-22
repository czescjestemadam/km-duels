package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.config.PartyConfig;
import czescjestemadas.kmduels.party.DuelParty;
import czescjestemadas.kmduels.players.DuelPlayer;
import czescjestemadas.kmduels.utils.ChatUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static czescjestemadas.kmduels.utils.StrUtils.argEquals;
import static czescjestemadas.kmduels.utils.StrUtils.retMatches;

public class PartyCommand implements TabExecutor
{
	private final Duels duels;
	private final PartyConfig cfg;

	public PartyCommand(Duels duels)
	{
		this.duels = duels;
		this.cfg = duels.getConfigManager().getPartyConfig();
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 0)
		{
			sender.sendMessage(cfg.msgHelp);
			return true;
		}

		final DuelPlayer player = sender instanceof Player mcplayer ? duels.getPlayerManager().getPlayer(mcplayer.getUniqueId()) : null;

		if (args.length == 2 && args[0].equalsIgnoreCase(cfg.argBan) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			if (!party.getOwner().equals(player))
				sender.sendMessage(cfg.msgNotPartyOwner);
			else
			{
				final Player mctarget = duels.getServer().getPlayer(args[1]);
				if (mctarget == null)
				{
					sender.sendMessage(cfg.msgNoPlayer);
					return false;
				}

				final DuelPlayer target = duels.getPlayerManager().getPlayer(mctarget.getUniqueId());
				if (player.equals(target) || party.getOwner().equals(target))
				{
					sender.sendMessage(cfg.msgNoPlayer);
					return false;
				}

				party.getPlayers().remove(target);
				party.banPlayer(mctarget.getUniqueId());
				target.getPlayer().sendMessage(cfg.msgBannedSelf);

				final Component msgBanned = ChatUtils.mm(cfg.msgBanned, Placeholder.unparsed("player", target.getPlayer().getName()));
				for (DuelPlayer partyPlayer : party.getPlayers())
					partyPlayer.getPlayer().sendMessage(msgBanned);
			}
			return true;
		}

		if (args.length == 2 && args[0].equalsIgnoreCase(cfg.argUnban) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			if (!party.getOwner().equals(player))
				sender.sendMessage(cfg.msgNotPartyOwner);
			else
			{
				final Player mctarget = duels.getServer().getPlayer(args[1]);
				if (mctarget == null)
				{
					sender.sendMessage(cfg.msgNoPlayer);
					return false;
				}

				party.unbanPlayer(mctarget.getUniqueId());

				final Component msgUnbanned = ChatUtils.mm(cfg.msgUnbanned, Placeholder.unparsed("player", mctarget.getName()));
				for (DuelPlayer partyPlayer : party.getPlayers())
					partyPlayer.getPlayer().sendMessage(msgUnbanned);
			}
			return true;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase(cfg.argClose) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			if (!party.getOwner().equals(player))
				sender.sendMessage(cfg.msgNotPartyOwner);
			else
			{
				party.setOpen(false);
				sender.sendMessage(cfg.msgClosed);
			}
			return true;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase(cfg.argCreate) && player != null)
		{
			if (duels.getPartyManager().getParty(player) != null)
				sender.sendMessage(cfg.msgAlreadyInParty);
			else
			{
				duels.getPartyManager().createParty(player);
				sender.sendMessage(cfg.msgCreated);
			}
			return true;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase(cfg.argRemove) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			else if (!party.getOwner().equals(player))
				sender.sendMessage(cfg.msgNotPartyOwner);
			else
			{
				for (DuelPlayer partyPlayer : party.getPlayers())
					partyPlayer.getPlayer().sendMessage(cfg.msgRemoved);

				duels.getPartyManager().removeParty(party);
			}
			return true;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase(cfg.argInfo) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			sender.sendMessage(party == null ? cfg.msgNotInParty : ChatUtils.mmParty(cfg.msgInfo, party));
			return true;
		}

		if (args.length == 2 && args[0].equalsIgnoreCase(cfg.argInfo))
		{
			final Player mctarget = duels.getServer().getPlayer(args[1]);
			if (mctarget == null)
			{
				sender.sendMessage(cfg.msgNoPlayer);
				return false;
			}

			final DuelPlayer target = duels.getPlayerManager().getPlayer(mctarget.getUniqueId());
			final DuelParty party = duels.getPartyManager().getParty(target);
			sender.sendMessage(party == null ? cfg.msgPlayerNotInParty : ChatUtils.mmParty(cfg.msgInfo, party));
			return true;
		}

		if (args.length == 2 && args[0].equalsIgnoreCase(cfg.argInvite) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			else if (!party.getOwner().equals(player))
				sender.sendMessage(cfg.msgNotPartyOwner);
			else
			{
				final Player mctarget = duels.getServer().getPlayer(args[1]);
				if (mctarget == null)
				{
					sender.sendMessage(cfg.msgNoPlayer);
					return false;
				}

				final DuelPlayer target = duels.getPlayerManager().getPlayer(mctarget.getUniqueId());

				// todo
			}
			return true;
		}

		if (args.length == 2 && args[0].equalsIgnoreCase(cfg.argJoin) && player != null)
		{
			// todo
			return true;
		}

		if (args.length == 2 && args[0].equalsIgnoreCase(cfg.argKick) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			else if (!party.getOwner().equals(player))
				sender.sendMessage(cfg.msgNotPartyOwner);
			else
			{
				final Player mctarget = duels.getServer().getPlayer(args[1]);
				if (mctarget == null)
				{
					sender.sendMessage(cfg.msgNoPlayer);
					return false;
				}

				final DuelPlayer target = duels.getPlayerManager().getPlayer(mctarget.getUniqueId());
				if (player.equals(target) || party.getOwner().equals(target))
				{
					sender.sendMessage(cfg.msgNoPlayer);
					return false;
				}

				party.getPlayers().remove(target);
				target.getPlayer().sendMessage(cfg.msgKickedSelf);

				final Component msgKicked = ChatUtils.mm(cfg.msgKicked, Placeholder.unparsed("player", target.getPlayer().getName()));
				for (DuelPlayer partyPlayer : party.getPlayers())
					partyPlayer.getPlayer().sendMessage(msgKicked);
			}
			return true;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase(cfg.argLeave) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			else if (party.getOwner().equals(player))
				sender.sendMessage(cfg.msgOwnerTriedLeave);
			else
			{
				party.getPlayers().remove(player);
				sender.sendMessage(cfg.msgLeftSelf);

				final Component msgLeft = ChatUtils.mm(cfg.msgLeft, Placeholder.unparsed("player", player.getPlayer().getName()));
				for (DuelPlayer partyPlayer : party.getPlayers())
					partyPlayer.getPlayer().sendMessage(msgLeft);
			}
			return true;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase(cfg.argList))
		{
			sender.sendMessage(cfg.msgListPrefix.append(
					Component.join(JoinConfiguration.commas(true),
							duels.getPartyManager().getParties().stream()
									.filter(DuelParty::isOpen)
									.map(party -> ChatUtils.mmParty(cfg.msgListItem, party))
									.toList())
			));
			return true;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase(cfg.argOpen) && player != null)
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			if (!party.getOwner().equals(player))
				sender.sendMessage(cfg.msgNotPartyOwner);
			else
			{
				party.setOpen(true);
				sender.sendMessage(cfg.msgOpened);
			}
			return true;
		}

		if (args.length == 1 && args[0].equalsIgnoreCase(cfg.argSettings))
		{
			final DuelParty party = duels.getPartyManager().getParty(player);
			if (party == null)
				sender.sendMessage(cfg.msgNotInParty);
			else if (!party.getOwner().equals(player))
				sender.sendMessage(cfg.msgNotPartyOwner);
			else
			{
				// todo
			}
			return true;
		}

		sender.sendMessage(cfg.msgHelp);
		return true;
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args)
	{
		if (args.length == 1)
			return retMatches(args[0], cfg.argBan, cfg.argUnban, cfg.argClose, cfg.argCreate, cfg.argRemove, cfg.argHelp, cfg.argInfo, cfg.argInvite, cfg.argJoin, cfg.argKick, cfg.argLeave, cfg.argList, cfg.argOpen, cfg.argSettings);

		// todo
		if (args.length == 2 && argEquals(args[0], cfg.argBan, cfg.argUnban, cfg.argInfo))
			return null;

		return List.of();
	}
}
