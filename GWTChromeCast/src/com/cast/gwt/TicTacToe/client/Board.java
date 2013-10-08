package com.cast.gwt.TicTacToe.client;

import com.cast.gwt.receiver.client.console;
import com.google.gwt.canvas.dom.client.Context;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.Window;

public class Board
{
	private Context2d mContext;
	private String mGameResult;
	private int mWinningLocation;
	Integer mBoard[][];
	private int dimension;
	private int margin = 50;
	private int pieceMargin = 20;
	private int X;
	private int Y;
	private int cellWidth;;

	public Board(Context context)
	{
		this.mContext = (Context2d) context;
		this.mBoard = new Integer[3][3];

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				this.mBoard[i][j] = STATE.EMPTY.value;
			}
		}

	}

	public void clear()
	{
		this.boardCalcDimensions();
	}

	private void boardCalcDimensions()
	{
		if (this.mContext.getCanvas().getWidth() > this.mContext.getCanvas()
				.getHeight())
		{
			this.dimension = this.mContext.getCanvas().getHeight() - 2 * this.margin;
		}
		else
		{
			this.dimension = this.mContext.getCanvas().getWidth() - 2 * this.margin;
		}
		this.X = (this.mContext.getCanvas().getWidth() - this.dimension) / 2;
		this.Y = (this.mContext.getCanvas().getHeight() - this.dimension) / 2;
		this.cellWidth = this.dimension / 3;

	}

	public void drawGrid()
	{
		this.boardDrawGrid();

	}

	private void boardDrawGrid()
	{
		this.mContext.setFillStyle("#BDBDBD");
		this.mContext.setStrokeStyle("#000000");
		this.mContext.fillRect(this.X, this.Y, this.dimension, this.dimension);
		// draw grid
		this.mContext.setLineWidth(5);
		this.mContext.moveTo(this.X + this.cellWidth, this.Y);
		this.mContext.lineTo(this.X + this.cellWidth, this.Y + this.dimension);
		this.mContext.stroke();
		this.mContext.moveTo(this.X + this.cellWidth * 2, this.Y);
		this.mContext.lineTo(this.X + this.cellWidth * 2, this.Y + this.dimension);
		this.mContext.stroke();
		this.mContext.moveTo(this.X, this.Y + this.cellWidth);
		this.mContext.lineTo(this.X + this.dimension, this.Y + this.cellWidth);
		this.mContext.stroke();
		this.mContext.moveTo(this.X, this.Y + this.cellWidth * 2);
		this.mContext.lineTo(this.X + this.dimension, this.Y + this.cellWidth * 2);
		this.mContext.stroke();

	}

	/**
	 * Draws an O symbol in the given row and column.
	 * 
	 * @param {number} row the row the piece should be placed in.
	 * @param {number} col the column the piece should be placed in.
	 * @this {board}
	 * @return {boolean} true if the selected row and column is a valid square to
	 *         put a piece in.
	 */
	public boolean boardDrawNaught(int row, int col)
	{
		if (this.mBoard[row][col] != STATE.EMPTY.value)
		{
			console.info("Invalid position: " + row + " " + col + " val:"
					+ this.mBoard[row][col]);
			return false;
		}
		this.mBoard[row][col] = STATE.NAUGHT.value;
		this.mContext.setLineWidth(8);
		this.mContext.setStrokeStyle("#FFFF00");
		this.mContext.beginPath();
		this.mContext.arc(this.X + this.cellWidth * (col + 0.5), this.Y
				+ this.cellWidth * (row + 0.5), this.cellWidth / 2 - this.pieceMargin,
				0, 360);
		this.mContext.stroke();
		return true;
	}

	/**
	 * Draws an X symbol in the given row and column.
	 * 
	 * @param {number} row the row the piece should be placed in.
	 * @param {number} col the column the piece should be placed in.
	 * @this {board}
	 * @return {boolean} true if the selected row and column is a valid square to
	 *         put a piece in.
	 */
	public boolean boardDrawCross(int row, int col)
	{
		if (this.mBoard[row][col] != STATE.EMPTY.value)
		{
			console.info("Invalid position: " + row + " " + col + " val:"
					+ this.mBoard[row][col]);
			return false;
		}
		this.mBoard[row][col] = STATE.CROSS.value;
		this.mContext.setLineWidth(8);
		this.mContext.setStrokeStyle("#FFFF00");
		this.mContext.beginPath();
		this.mContext.moveTo(this.X + this.cellWidth * col + this.pieceMargin,
				this.Y + this.cellWidth * row + this.pieceMargin);
		this.mContext.lineTo(
				this.X + this.cellWidth * (col + 1) - this.pieceMargin, this.Y
						+ this.cellWidth * (row + 1) - this.pieceMargin);
		this.mContext.stroke();
		this.mContext.moveTo(
				this.X + this.cellWidth * (col + 1) - this.pieceMargin, this.Y
						+ this.cellWidth * row + this.pieceMargin);
		this.mContext.lineTo(this.X + this.cellWidth * col + this.pieceMargin,
				this.Y + this.cellWidth * (row + 1) - this.pieceMargin);
		this.mContext.stroke();
		return true;
	}

	public boolean drawCross(double row, double col)
	{
		return boardDrawCross((int) row, (int) col);
	}

	public boolean drawNaught(double row, double col)
	{
		return boardDrawNaught((int) row, (int) col);
	}

	public boolean isGameOver()
	{
		return boardIsGameOver();
	}

	private boolean boardIsGameOver()
	{ boolean isBoardFull = true;
  this.printBoard();
  // Check the rows
  for (int i = 0; i < this.mBoard.length; i++) {
    if ((this.mBoard[i][0] != STATE.EMPTY.value) &&
        (this.mBoard[i][1] == this.mBoard[i][0]) &&
        (this.mBoard[i][2] == this.mBoard[i][0])) {
      this.mGameResult = GAME_RESULT.O_WON.value;
      if (this.mBoard[i][0] == STATE.CROSS.value) {
        this.mGameResult = GAME_RESULT.X_WON.value;
      }
      if (i == 0) {
        this.mWinningLocation = WINNING_LOCATION.ROW_0.value;
      } else if (i == 1) {
        this.mWinningLocation = WINNING_LOCATION.ROW_1.value;
      } else {
        this.mWinningLocation = WINNING_LOCATION.ROW_2.value;
      }
    }
    if ((isBoardFull == true) && ((this.mBoard[i][0] == STATE.EMPTY.value) ||
        (this.mBoard[i][1] == STATE.EMPTY.value) ||
        (this.mBoard[i][2] == STATE.EMPTY.value))) {
      isBoardFull = false;
    }
  }
  this.printBoard();

  // Check the columns
  for (int j = 0; j < this.mBoard[0].length; j++) {
    if ((this.mBoard[0][j] != STATE.EMPTY.value) &&
        (this.mBoard[1][j] == this.mBoard[0][j]) &&
        (this.mBoard[2][j] == this.mBoard[0][j])) {
      this.mGameResult = GAME_RESULT.O_WON.value;
      if (this.mBoard[0][j] == STATE.CROSS.value) {
        this.mGameResult = GAME_RESULT.X_WON.value;
      }
      if (j == 0) {
        this.mWinningLocation = WINNING_LOCATION.COLUMN_0.value;
      } else if (j == 1) {
        this.mWinningLocation = WINNING_LOCATION.COLUMN_1.value;
      } else {
        this.mWinningLocation = WINNING_LOCATION.COLUMN_2.value;
      }
      break;
    }
  }
  this.printBoard();

  // Check diagonals
  if ((this.mBoard[0][0] != STATE.EMPTY.value) &&
      (this.mBoard[1][1] == this.mBoard[0][0]) &&
      (this.mBoard[2][2] == this.mBoard[0][0])) {
    this.mWinningLocation = WINNING_LOCATION.DIAGONAL_TOPLEFT.value;
    this.mGameResult = GAME_RESULT.O_WON.value;
    if (this.mBoard[0][0] == STATE.CROSS.value) {
      this.mGameResult = GAME_RESULT.X_WON.value;
    }
  } else if ((this.mBoard[0][2] != STATE.EMPTY.value) &&
      (this.mBoard[1][1] == this.mBoard[0][2]) &&
      (this.mBoard[2][0] == this.mBoard[0][2])) {
    this.mWinningLocation = WINNING_LOCATION.DIAGONAL_BOTTOMLEFT.value;
    this.mGameResult = GAME_RESULT.O_WON.value;
    if (this.mBoard[0][2] == STATE.CROSS.value) {
      this.mGameResult = GAME_RESULT.X_WON.value;
    }
  }

  // Check whether the game was won or drawn
  if ((this.mGameResult == GAME_RESULT.X_WON.value) ||
      (this.mGameResult == GAME_RESULT.O_WON.value)) {
    this.drawWinningLocation();
    return true;
  }
  if (isBoardFull == true) {
    this.mGameResult = GAME_RESULT.DRAW.value;
    return true;
  }
  return false;
	}

	private void drawWinningLocation()
	{
		boardDrawWinningLocation();
		
	}

	private void boardDrawWinningLocation()
	{
		 double xStart= -1, yStart= -1,xEnd= -1 , yEnd = -1;
		  if (this.mWinningLocation == WINNING_LOCATION.ROW_0.value) {
		    xStart = 0.05;
		    xEnd = 2.95;
		    yStart = yEnd = 0.5;
		  } else if (this.mWinningLocation == WINNING_LOCATION.ROW_1.value) {
		    xStart = 0.05;
		    xEnd = 2.95;
		    yStart = yEnd = 1.5;
		  } else if (this.mWinningLocation == WINNING_LOCATION.ROW_2.value) {
		    xStart = 0.05;
		    xEnd = 2.95;
		    yStart = yEnd = 2.5;
		  } else if (this.mWinningLocation == WINNING_LOCATION.COLUMN_0.value) {
		    yStart = 0.05;
		    yEnd = 2.95;
		    xStart = xEnd = 0.5;
		  } else if (this.mWinningLocation == WINNING_LOCATION.COLUMN_1.value) {
		    yStart = 0.05;
		    yEnd = 2.95;
		    xStart = xEnd = 1.5;
		  } else if (this.mWinningLocation == WINNING_LOCATION.COLUMN_2.value) {
		    yStart = 0.05;
		    yEnd = 2.95;
		    xStart = xEnd = 2.5;
		  } else if (this.mWinningLocation == WINNING_LOCATION.DIAGONAL_TOPLEFT.value) {
		    xStart = yStart = 0.05;
		    xEnd = yEnd = 2.95;
		  } else if (this.mWinningLocation == WINNING_LOCATION.DIAGONAL_BOTTOMLEFT.value) {
		    xStart = yEnd = 2.95;
		    yStart = xEnd = 0.05;
		  } else {
		    console.log("Unknown winning location: " + this.mWinningLocation);
		  }
		  this.mContext.setLineWidth(10);
		  this.mContext.setStrokeStyle("#FF0000"); 
		  this.mContext.beginPath();
		  this.mContext.moveTo(this.X + this.cellWidth * xStart,
		      this.Y + this.cellWidth * yStart);
		  this.mContext.lineTo(this.X + this.cellWidth * xEnd,
		      this.Y + this.cellWidth * yEnd);
		  this.mContext.stroke();
		
	}

	private void printBoard()
	{
		boardPrintBoard();
		
	}

	private void boardPrintBoard()
	{
	  for (int i = 0; i < this.mBoard.length; i++) {
	    console.log("[ " + this.mBoard[i][0] + ", " + this.mBoard[i][1] + ", " +
	                this.mBoard[i][2] + " ]");
	  }
	  console.log("gameResult: " + this.mGameResult);
	  console.log("winningLoc: " + this.mWinningLocation);
		
	}

	public int getWinningLocation()
	{
		return this.mWinningLocation;
	}

	public String getGameResult()
	{
	  return this.mGameResult;
	}

	public void setGameAbandoned()
	{
	  this.mGameResult = GAME_RESULT.ABANDONED.value;
	}

	public void reset()
	{
		 this.mGameResult = "";
		  this.mWinningLocation = -1;
		  this.mContext.beginPath();
		  this.mContext.clearRect(this.X, this.Y, this.mContext.getCanvas().getWidth(),
		      this.mContext.getCanvas().getHeight());
		  this.drawGrid();
		  for (int i = 0; i < this.mBoard.length; i++) {
		    for (int j = 0; j < this.mBoard[0].length; j++) {
		      this.mBoard[i][j] = STATE.EMPTY.value;
		    }
		  }
		
	}
}
