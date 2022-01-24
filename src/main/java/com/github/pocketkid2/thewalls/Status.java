package com.github.pocketkid2.thewalls;

public enum Status {

	// When the arena has been created but all its values have not yet been
	// initialized
	INCOMPLETE,

	// When all the values have been initialized and the arena is idling
	READY,

	// When the game is doing the countdown and the game is about to enter play
	STARTING,

	// When the game is in play
	INGAME,

	// When the arena is restoring blocks
	RESETTING,
}
