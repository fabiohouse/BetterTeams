package com.booksaw.betterTeams.commands.presets;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.booksaw.betterTeams.CommandResponse;
import com.booksaw.betterTeams.Team;
import com.booksaw.betterTeams.TeamPlayer;
import com.booksaw.betterTeams.commands.SubCommand;

/**
 * This class can be extended for any sub commands which require players to be
 * in a team
 * 
 * @author booksaw
 *
 */
public abstract class TeamSubCommand extends SubCommand {

	@Override
	public CommandResponse onCommand(CommandSender sender, String label, String[] args) {
		Player player = (Player) sender;
		Team team = Team.getTeam(player);

		if (team == null) {
			return new CommandResponse("inTeam");
		}
		TeamPlayer teamPlayer = team.getTeamPlayer(player);
		return onCommand(teamPlayer, label, args, team);
	}

	/**
	 * This method is run if the player is in a team
	 * 
	 * @param player the player who is in a team
	 * @param label  the label for the command
	 * @param args   the arguments for the command
	 * @param team   the team that the player is in
	 * @return the message reference to send to the user
	 */
	public abstract CommandResponse onCommand(TeamPlayer player, String label, String[] args, Team team);

	@Override
	public boolean needPlayer() {
		return true;
	}
}
