package czescjestemadas.kmduels.players;

import czescjestemadas.kmduels.Duels;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
		playersDir.mkdirs();
	}

	public void loadOnlinePlayers()
	{
		for (Player player : duels.getServer().getOnlinePlayers())
		{
			final DuelPlayer duelPlayer = createPlayer(player.getUniqueId());
			loadPlayer(duelPlayer);
		}
	}

	public void savePlayers()
	{
		for (DuelPlayer player : players)
			savePlayer(player);
	}

	public List<DuelPlayer> getPlayers()
	{
		return Collections.unmodifiableList(players);
	}

	public @NotNull DuelPlayer getPlayer(UUID id)
	{
		for (DuelPlayer player : players)
		{
			if (player.getOwner().equals(id))
				return player;
		}

		throw new IllegalStateException("no DuelPlayer found with uuid " + id);
	}

	public void loadPlayer(DuelPlayer player)
	{
		final File file = getPlayerdataFile(player);
		if (!file.exists())
			return;

		final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

		player.getStats().load(cfg.getConfigurationSection("stats"));
	}

	public DuelPlayer createPlayer(UUID id)
	{
		final DuelPlayer player = new DuelPlayer(id);
		players.add(player);
		return player;
	}

	public void savePlayer(DuelPlayer player)
	{
		final YamlConfiguration cfg = new YamlConfiguration();

		player.getStats().save(cfg.createSection("stats"));

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
