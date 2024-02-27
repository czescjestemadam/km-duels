package czescjestemadas.kmduels.players;

import czescjestemadas.kmduels.Duels;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

public final class PlayerManager
{
	private final Duels duels;
	private final File playersDir;
	private final List<DuelPlayer> players = new ArrayList<>();

	public PlayerManager(Duels duels)
	{
		this.duels = duels;
		playersDir = new File(duels.getDataFolder(), "players");
	}

	public void loadOnlinePlayers()
	{
		for (Player player : duels.getServer().getOnlinePlayers())
		{
			final DuelPlayer duelPlayer = new DuelPlayer(player.getUniqueId());
			loadPlayer(duelPlayer);
		}
	}

	public void savePlayers()
	{
		for (DuelPlayer player : players)
			savePlayer(player);
	}

	public DuelPlayer getPlayer(UUID id)
	{
		for (DuelPlayer player : players)
		{
			if (player.getOwner().equals(id))
				return player;
		}

		return null;
	}

	public void loadPlayer(DuelPlayer player)
	{
		final File file = getPlayerdataFile(player);
		if (!file.exists())
			return;

		final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);


	}

	public void savePlayer(DuelPlayer player)
	{
		final YamlConfiguration cfg = new YamlConfiguration();



		try
		{
			cfg.save(getPlayerdataFile(player));
		}
		catch (IOException e)
		{
			duels.getLogger().log(Level.SEVERE, "cant save player", e);
		}
	}

	public void unloadPlayer(DuelPlayer player)
	{
		players.remove(player);
	}


	private File getPlayerdataFile(DuelPlayer player)
	{
		return new File(playersDir, player.getOwner().toString() + ".yml");
	}
}
