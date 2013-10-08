package com.cast.gwt.TicTacToe.client;

public enum GAME_RESULT
{
	X_WON("X-won"), O_WON("O-won"), DRAW("draw"), ABANDONED("abandoned");

	String value;

	private GAME_RESULT(String value)
	{
		this.value = value;
	}
}
