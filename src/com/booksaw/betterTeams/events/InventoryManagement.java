package com.booksaw.betterTeams.events;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import com.booksaw.betterTeams.Team;
import com.booksaw.betterTeams.message.MessageManager;

public class InventoryManagement implements Listener {

	public static HashMap<Player, Team> adminViewers = new HashMap<>();

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Team t = adminViewers.get((Player) e.getPlayer());

		if (t == null) {
			t = Team.getTeam((Player) e.getPlayer());
			if (t == null) {
				return;
			}
		}

		if (!e.getView().getTitle().equals(MessageManager.getMessage("echest.echest"))) {
			return;
		}

		adminViewers.remove(e.getPlayer());

		t.saveEchest();

	}

}
