package com.booksaw.betterTeams.score;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.booksaw.betterTeams.Main;

public class ScoreChange {

	public static List<ScoreChange> spams = new ArrayList<>();

	public static boolean isSpam(ChangeType type, Player source) {
		return isSpam(type, source, null);
	}

	
	/**
	 * Used to check if an action is a spam action, this will increase the timer if
	 * it is Returns false if it is not a spam kill
	 */
	public static boolean isSpam(ChangeType type, Player source, Player target) {

		ScoreChange change = null;

		for (ScoreChange temp : spams) {
			if (temp.type == type && temp.source == source && temp.target == target) {
				change = temp;
				break;
			}
		}

		if (change == null) {
			return false;
		}

		if (change.hasExpired()) {
			spams.remove(change);
			return false;
		}

		change.update();
		return true;

	}

	public ChangeType type;
	public Player source, target;
	private long expires;

	public ScoreChange(ChangeType type, Player source) {
		this(type, source, null);
	}

	public ScoreChange(ChangeType type, Player source, Player target) {
		this.type = type;
		this.source = source;
		this.target = target;
		update();
		spams.add(this);
	}

	public boolean hasExpired() {
		return expires < System.currentTimeMillis();
	}

	public void update() {
		expires = System.currentTimeMillis() + (Main.plugin.getConfig().getInt("spamThreshold") * 1000);
	}

	public enum ChangeType {
		KILL, DEATH;
	}

}
