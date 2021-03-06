package com.booksaw.betterTeams;

import com.booksaw.betterTeams.message.MessageManager;

/**
 * This class is used to define all the possible ranks that a player can be
 * within a team
 * 
 * @author booksaw
 *
 */
public enum PlayerRank {
	/**
	 * The starting rank with the lowest number of permissions
	 */
	DEFAULT,

	/**
	 * The highest rank, this rank has full permissions over the team
	 */
	OWNER,

	/**
	 * This player has permissions to invite new players to the team and kick
	 * default users, but cannot ban people or change team settings
	 */
	ADMIN;

	/**
	 * @return the prefix for that player rank
	 */
	public String getPrefix() {
		return MessageManager.getMessage("prefix." + this.toString().toLowerCase());
	}

}
