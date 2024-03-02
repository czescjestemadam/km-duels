package czescjestemadas.kmduels.players;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class DuelPlayer
{
	private final UUID owner;
	private final PlayerStats stats = new PlayerStats();
	private final Map<String, Double> elos = new HashMap<>();

	public DuelPlayer(UUID owner)
	{
		this.owner = owner;
	}

	public void executeCommand(String cmd)
	{
		getPlayer().performCommand(cmd);
	}

	public UUID getOwner()
	{
		return owner;
	}

	public OfflinePlayer getOfflinePlayer()
	{
		return Bukkit.getOfflinePlayer(owner);
	}

	public @NotNull Player getPlayer()
	{
		return Objects.requireNonNull(Bukkit.getPlayer(owner));
	}

	public PlayerStats getStats()
	{
		return stats;
	}

	public Map<String, Double> getElos()
	{
		return elos;
	}

	public int getPing()
	{
		return getPlayer().spigot().getPing();
	}


	@Override
	public boolean equals(Object obj)
	{
		return obj instanceof DuelPlayer player ? player.owner.equals(owner) : super.equals(obj);
	}

	@Override
	public String toString()
	{
		return "DuelPlayer{" +
				"owner=" + owner +
				", stats=" + stats +
				", elos=" + elos +
				'}';
	}
}
