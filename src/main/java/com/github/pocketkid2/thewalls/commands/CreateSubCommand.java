package com.github.pocketkid2.thewalls.commands;

import java.util.Arrays;
import java.util.List;

import com.github.pocketkid2.thewalls.TheWallsPlugin;

public class CreateSubCommand extends TheWallsSubCommand {

	public CreateSubCommand(TheWallsPlugin p) {
		super(p);
	}

	@Override
	public boolean mustBePlayer() {
		return false;
	}

	@Override
	public List<String> names() {
		return Arrays.asList("create");
	}

	@Override
	public int minArguments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int maxArguments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isAdminCommand() {
		return true;
	}

	@Override
	public void execute(String[] args) {

	}

}
