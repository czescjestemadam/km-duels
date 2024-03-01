package czescjestemadas.kmduels.hotbar;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.gui.selectors.RankedFightSelector;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.function.BiConsumer;

public enum HotbarItemAction
{
	OPEN_RANKED_FIGHT_SELECTOR((duels, player) -> duels.getGuiManager().open(player, new RankedFightSelector(duels))),
	OPEN_KIT_EDITOR((duels, player) -> {player.getPlayer().sendMessage("editor");}),
	OPEN_SPECTATOR_SELECTOR((duels, player) -> {player.getPlayer().sendMessage("spectator");}),
	LEAVE_QUEUE((duels, player) -> {player.getPlayer().sendMessage("leave q");}),
	CREATE_PARTY((duels, player) -> {player.getPlayer().sendMessage("create party");}),
	LEAVE_PARTY((duels, player) -> {player.getPlayer().sendMessage("leave party");}),
	OPEN_PARTY_FIGHT_SELECTOR((duels, player) -> {player.getPlayer().sendMessage("party fight");}),
	OPEN_PARTY_SETTING_SELECTOR((duels, player) -> {player.getPlayer().sendMessage("party setting");}),
	SHOW_PARTY_INFO((duels, player) -> {player.getPlayer().sendMessage("party info");}),

	COMMAND((duels, player) -> {player.getPlayer().sendMessage("command");});

	private final BiConsumer<Duels, DuelPlayer> func;

	HotbarItemAction(BiConsumer<Duels, DuelPlayer> func)
	{
		this.func = func;
	}

	public void execute(Duels duels, DuelPlayer player)
	{
		func.accept(duels, player);
	}
}
