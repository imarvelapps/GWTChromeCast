package com.cast.gwt.TicTacToe.client;

public enum WINNING_LOCATION
{
	ROW_0(0), ROW_1(1), ROW_2(2), COLUMN_0(3), COLUMN_1(4), COLUMN_2(5), DIAGONAL_TOPLEFT(
			6), DIAGONAL_BOTTOMLEFT(7);

	int value;

	WINNING_LOCATION(int value)
	{
		this.value = value;
	}
}
