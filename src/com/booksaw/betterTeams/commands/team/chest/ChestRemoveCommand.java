package com.booksaw.betterTeams.commands.team.chest;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;

import com.booksaw.betterTeams.CommandResponse;
import com.booksaw.betterTeams.PlayerRank;
import com.booksaw.betterTeams.Team;
import com.booksaw.betterTeams.TeamPlayer;
import com.booksaw.betterTeams.commands.presets.TeamSubCommand;

public class ChestRemoveCommand extends TeamSubCommand {

	@Override
	public CommandResponse onCommand(TeamPlayer player, String label, String[] args, Team team) {
		if (player.getRank() == PlayerRank.DEFAULT) {
			return new CommandResponse("needAdmin");
		}

		Location loc = player.getPlayer().getPlayer().getLocation();

		Block block = loc.getBlock();
		loc = Team.getClaimingLocation(block);
		if (block == null || block.getType() != Material.CHEST) {
			return new CommandResponse("chest.remove.noChest");
		} else if (loc == null || Team.getClamingTeam(loc) != team) {
			return new CommandResponse("chest.remove.notClaimed");
		}

		// they can claim the chest
		team.removeClaim(loc);

		return new CommandResponse(true, "chest.remove.success");
	}

	@Override
	public String getCommand() {
		return "remove";
	}

	@Override
	public String getNode() {
		return "chest.remove";
	}

	@Override
	public String getHelp() {
		return "Removes your teams claim from the chest you are standing on";
	}

	@Override
	public String getArguments() {
		return "";
	}

	@Override
	public int getMinimumArguments() {
		return 0;
	}

	@Override
	public int getMaximumArguments() {
		return 0;
	}

	@Override
	public void onTabComplete(List<String> options, CommandSender sender, String label, String[] args) {
	}

}
