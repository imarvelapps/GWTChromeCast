package com.cast.gwt.TicTacToe.client;

public enum STATE
{
	EMPTY(0), CROSS(1), NAUGHT(2);

	Integer value;

	private STATE(Integer i)
	{
		this.value = i;
	}
}
