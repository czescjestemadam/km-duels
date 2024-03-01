package czescjestemadas.kmduels.config;

import net.kyori.adventure.text.Component;
import org.bukkit.configuration.ConfigurationSection;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class KitsConfig implements Config
{
	public Component msgHelp;
	public Component msgListPrefix;
	public String msgListItem;
	public String msgCreate;
	public String msgRemove;
	public String msgSetDisplayname;
	public String msgSetItems;
	public String msgInfo;
	public String msgGet;
	public String msgBindMaps;
	public String msgUnbindMaps;
	public String msgSetIcon;
	public Component msgAlreadyExists;
	public Component msgNotFound;

	@Override
	public void load(ConfigurationSection cfg)
	{
		final ConfigurationSection msg = cfg.getConfigurationSection("messages");
		msgHelp = miniMessage().deserialize(msg.getString("help"));
		msgListPrefix = miniMessage().deserialize(msg.getString("list.prefix"));
		msgListItem = msg.getString("list.item");
		msgCreate = msg.getString("create");
		msgRemove = msg.getString("remove");
		msgSetDisplayname = msg.getString("set-displayname");
		msgSetItems = msg.getString("set-items");
		msgInfo = msg.getString("info");
		msgGet = msg.getString("get");
		msgBindMaps = msg.getString("bind-maps");
		msgUnbindMaps = msg.getString("unbind-maps");
		msgSetIcon = msg.getString("set-icon");
		msgAlreadyExists = miniMessage().deserialize(msg.getString("already-exists"));
		msgNotFound = miniMessage().deserialize(msg.getString("not-found"));
	}
}
