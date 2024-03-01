package czescjestemadas.kmduels.hotbar;

import czescjestemadas.kmduels.Duels;
import czescjestemadas.kmduels.players.DuelPlayer;

import java.util.function.BiConsumer;

public enum HotbarItemAction
{
	OPEN_RANKED_FIGHT_SELECTOR((duels, player) -> {}),
	OPEN_KIT_EDITOR((duels, player) -> {}),
	OPEN_SPECTATOR_SELECTOR((duels, player) -> {}),
	LEAVE_QUEUE((duels, player) -> {}),
	CREATE_PARTY((duels, player) -> {}),
	LEAVE_PARTY((duels, player) -> {}),
	OPEN_PARTY_FIGHT_SELECTOR((duels, player) -> {}),
	OPEN_PARTY_SETTING_SELECTOR((duels, player) -> {}),
	SHOW_PARTY_INFO((duels, player) -> {}),

	COMMAND((duels, player) -> {});

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
