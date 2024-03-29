package czescjestemadas.kmduels.commands;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.commands.utils.parsers.ArgParsers;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;

public final class CommandManager
{
	private final Duels duels;

	public CommandManager(Duels duels)
	{
		this.duels = duels;
	}

	public void registerCommands()
	{
		ArgParsers.register(duels);

		register("duels", new DuelsCommand(duels));
		register("dkit", new DKitCommand(duels));
		register("dmap", new DMapCommand(duels));
		register("party", new PartyCommand(duels));
	}

	private void register(String name, TabExecutor cmd)
	{
		final PluginCommand command = duels.getCommand(name);
		if (command == null)
		{
			duels.getLogger().warning("command not registered: " + name);
			return;
		}

		command.setExecutor(cmd);
		command.setTabCompleter(cmd);
	}
}
