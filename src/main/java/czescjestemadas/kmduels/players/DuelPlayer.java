package czescjestemadas.kmduels.players;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
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

	public UUID getOwner()
	{
		return owner;
	}

	public OfflinePlayer getPlayer()
	{
		return Bukkit.getOfflinePlayer(owner);
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
		return getPlayer().getPlayer().spigot().getPing();
	}
}
