package czescjestemadas.kmduels.config;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class PartyConfig implements Config
{
	public int playerLimit;
	public String chatPrefix;
	public String chatFormat;

	public String argBan;
	public String argUnban;
	public String argClose;
	public String argCreate;
	public String argRemove;
	public String argHelp;
	public String argInfo;
	public String argInvite;
	public String argJoin;
	public String argKick;
	public String argLeave;
	public String argList;
	public String argOpen;
	public String argSettings;

	public Component msgHelp;
	public Component msgCreated;
	public Component msgRemoved;
	public Component msgOpened;
	public Component msgClosed;
	public String msgInfo;
	public Component msgInviteSent;
	public String msgInviteReceived;
	public String msgJoined;
	public Component msgJoinedSelf;
	public String msgKicked;
	public Component msgKickedSelf;
	public String msgBanned;
	public Component msgBannedSelf;
	public String msgUnbanned;
	public String msgLeft;
	public Component msgLeftSelf;
	public Component msgOwnerTriedLeave;
	public Component msgListPrefix;
	public String msgListItem;
	public Component msgNotInParty;
	public Component msgPlayerNotInParty;
	public Component msgNotPartyOwner;
	public Component msgAlreadyInParty;
	public Component msgNoPlayer;

	@Override
	public void load(ConfigurationSection cfg)
	{
		playerLimit = cfg.getInt("player-limit");
		chatPrefix = cfg.getString("chat.prefix");
		chatFormat = cfg.getString("chat.format");

		final ConfigurationSection args = cfg.getConfigurationSection("command-args");
		argBan = args.getString("ban");
		argUnban = args.getString("unban");
		argClose = args.getString("close");
		argCreate = args.getString("create");
		argRemove = args.getString("remove");
		argHelp = args.getString("help");
		argInfo = args.getString("info");
		argInvite = args.getString("invite");
		argJoin = args.getString("join");
		argKick = args.getString("kick");
		argLeave = args.getString("leave");
		argList = args.getString("list");
		argOpen = args.getString("open");
		argSettings = args.getString("settings");

		final ConfigurationSection msg = cfg.getConfigurationSection("messages");
		msgHelp = miniMessage().deserialize(msg.getString("help"));
		msgCreated = miniMessage().deserialize(msg.getString("created"));
		msgRemoved = miniMessage().deserialize(msg.getString("removed"));
		msgOpened = miniMessage().deserialize(msg.getString("opened"));
		msgClosed = miniMessage().deserialize(msg.getString("closed"));
		msgInfo = msg.getString("info");
		msgInviteSent = miniMessage().deserialize(msg.getString("invite.sent"));
		msgInviteReceived = msg.getString("invite.received");
		msgJoined = msg.getString("joined");
		msgJoinedSelf = miniMessage().deserialize(msg.getString("joined-self"));
		msgKicked = msg.getString("kicked");
		msgKickedSelf = miniMessage().deserialize(msg.getString("kicked-self"));
		msgBanned = msg.getString("banned");
		msgBannedSelf = miniMessage().deserialize(msg.getString("banned-self"));
		msgUnbanned = msg.getString("unbanned");
		msgLeft = msg.getString("left");
		msgLeftSelf = miniMessage().deserialize(msg.getString("left-self"));
		msgOwnerTriedLeave = miniMessage().deserialize(msg.getString("owner-tried-leave"));
		msgListPrefix = miniMessage().deserialize(msg.getString("list.prefix"));
		msgListItem = msg.getString("list.item");
		msgNotInParty = miniMessage().deserialize(msg.getString("not-in-party"));
		msgPlayerNotInParty = miniMessage().deserialize(msg.getString("player-not-in-party"));
		msgNotPartyOwner = miniMessage().deserialize(msg.getString("not-party-owner"));
		msgAlreadyInParty = miniMessage().deserialize(msg.getString("already-in-party"));
		msgNoPlayer = miniMessage().deserialize(msg.getString("no-player"));
	}
}
