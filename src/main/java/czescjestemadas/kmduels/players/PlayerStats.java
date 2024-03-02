package czescjestemadas.kmduels.players;

import org.bukkit.configuration.ConfigurationSection;

public class PlayerStats
{
	public int kills = 0;
	public int deaths = 0;
	public int assists = 0;

	public int wins = 0;
	public int loses = 0;
	public int draws = 0;
	public int playedGames = 0;


	public void load(ConfigurationSection cfg)
	{
		kills = cfg.getInt("kills");
		deaths = cfg.getInt("deaths");
		assists = cfg.getInt("assists");

		wins = cfg.getInt("wins");
		loses = cfg.getInt("loses");
		draws = cfg.getInt("draws");
		playedGames = cfg.getInt("played-games");
	}

	public void save(ConfigurationSection cfg)
	{
		cfg.set("kills", kills);
		cfg.set("deaths", deaths);
		cfg.set("assists", assists);

		cfg.set("wins", wins);
		cfg.set("loses", loses);
		cfg.set("draws", draws);
		cfg.set("played-games", playedGames);
	}

	public void reset()
	{
		kills = 0;
		deaths = 0;
		assists = 0;

		wins = 0;
		loses = 0;
		draws = 0;
		playedGames = 0;
	}


	@Override
	public String toString()
	{
		return "PlayerStats{" +
				"kills=" + kills +
				", deaths=" + deaths +
				", assists=" + assists +
				", wins=" + wins +
				", loses=" + loses +
				", draws=" + draws +
				", playedGames=" + playedGames +
				'}';
	}
}
