#include "stdafx.h"
#include "TestGameBoard.h"


CTestGameBoard::CTestGameBoard()
//initializes variables
:boardArray(NULL), boardColumns(15), boardRows(15), boardHeight(35), boardWidth(35), numRemaining(0), numColors(3)
{
	//Sets the 4 colors used in the game, 0 being the background and 1-3 being the block colors
	gameColors[0] = RGB( 0, 0, 0 );
	gameColors[1] = RGB( 255, 0, 0 );
	gameColors[2] = RGB( 255, 255, 64 );
	gameColors[3] = RGB( 0, 0, 255 );

	gameColors[4] = RGB( 0, 255, 0 );
	gameColors[5] = RGB( 0, 255, 255 );
	gameColors[6] = RGB( 255, 0, 128 );
	gameColors[7] = RGB( 0, 64, 0 );

	SetBoard();
}

//Deletes the board using the private function
CTestGameBoard::~CTestGameBoard()
{
	DestroyBoard();
}

void CTestGameBoard::SetBoard()
{
	//if the board does not exist, create it first
	if (boardArray == NULL)
		CreateBoard();
	//sets each spot in the board to a random color
	for (int i = 0; i < boardRows; i++)
		for (int j = 0; j < boardColumns; j++)
			boardArray[i][j] = (rand() % numColors) + 1;
	numRemaining = boardRows * boardColumns;
}

//returns the color of a particular block in the board
COLORREF CTestGameBoard::GetColor(int row, int col)
{
	//out of bounds
	if (row < 0 || row > boardRows || col < 0 || col > boardColumns)
		return gameColors[0];
	return gameColors[boardArray[row][col]];
}

//Deletes the board to avoid memory leaks
void CTestGameBoard::DestroyBoard()
{
	//stop if the board is already null
	if (boardArray != NULL)
	{
		for (int i = 0; i < boardRows; i++)
		{
			if (boardArray[i] != NULL)
			{
				//deletes each row
				delete[] boardArray[i];
				boardArray[i] = NULL;
			}
		}
		//deletes array of rows
		delete[] boardArray;
		boardArray = NULL;
	}

}

void CTestGameBoard::CreateBoard()
{
	//deletes board if it already exists
	if (boardArray != NULL)
	{
		DestroyBoard();
	}
	//Create array of rows
	boardArray = new int*[boardRows];
	//Create each row
	for (int i = 0; i < boardRows; i++)
	{
		boardArray[i] = new int[boardColumns];
		for (int j = 0; j < boardColumns; j++)
		{
			//Every square becomes black
			boardArray[i][j] = 0;
		}
	}
}

int CTestGameBoard::Delete(int row, int col)
{
	//row and column are in game
	if (row < 0 || row >= boardRows || col < 0 || col >= boardColumns)
	{
		return -1;
	}
	//do not delete background blocks
	int Color = boardArray[row][col];
	if (Color == 0)
	{
		return -1;
	}
	//check adjacent sides for same color
	int count = -1;
	if ((row - 1 >= 0 && boardArray[row - 1][col] == Color) ||
		(row + 1 < boardRows && boardArray[row + 1][col] == Color) ||
		(col - 1 >= 0 && boardArray[row][col - 1] == Color) ||
		(col + 1 < boardColumns && boardArray[row][col + 1] == Color))
	{
		//call recursive function to destroy same colored blocks
		boardArray[row][col] = 0;
		count = 1;
		//Recursive Up
		count += NeighborBlocks(row - 1, col, Color, DIRECTION_DOWN);
		//Recursive Down
		count += NeighborBlocks(row + 1, col, Color, DIRECTION_UP);
		//Recursive Right
		count += NeighborBlocks(row, col + 1, Color, DIRECTION_LEFT);
		//Recursive Left
		count += NeighborBlocks(row, col - 1, Color, DIRECTION_RIGHT);
		//Compact board
		SqueezeBoard();
		//remove count from number remaining
		numRemaining -= count;
	}

	return count;
}

int CTestGameBoard::NeighborBlocks(int row, int col, int color, Direction direction)
{
	//check if it's on board
	if (row < 0 || row >= boardRows || col < 0 || col >= boardColumns)
		return 0;
	//check if it's same color
	if (boardArray[row][col] != color)
		return 0;
	int count = 1;
	boardArray[row][col] = 0;
	//check up
	if (direction != DIRECTION_UP)
		count += NeighborBlocks(row - 1, col, color, DIRECTION_DOWN);
	//check down
	if (direction != DIRECTION_DOWN)
		count += NeighborBlocks(row + 1, col, color, DIRECTION_UP);
	//check right
	if (direction != DIRECTION_RIGHT)
		count += NeighborBlocks(row, col + 1, color, DIRECTION_LEFT);
	//check left
	if (direction != DIRECTION_LEFT)
		count += NeighborBlocks(row, col - 1, color, DIRECTION_RIGHT);
	return count;
}

void CTestGameBoard::SqueezeBoard()
{
	//move everything down
	for (int j = 0; j < boardColumns; j++)
	{
		int nextEmptyRow = boardRows - 1;
		int nextOccupiedRow = nextEmptyRow;
		while (nextOccupiedRow >= 0 && nextEmptyRow >= 0)
		{
			//find empty row
			while (nextEmptyRow >= 0 && boardArray[nextEmptyRow][j] != 0)
			{
				nextEmptyRow--;
			}
			if (nextEmptyRow >= 0)
			{
				nextOccupiedRow = nextEmptyRow - 1;
				while (nextOccupiedRow >= 0 && boardArray[nextOccupiedRow][j] == 0)
				{
					nextOccupiedRow--;
				}
				if (nextOccupiedRow >= 0)
				{
					//next occupied row
					boardArray[nextEmptyRow][j] = boardArray[nextOccupiedRow][j];
					boardArray[nextOccupiedRow][j] = 0;
				}
			}
		}
	}

	//move everything from right to left
	int nextEmptyColumn = 0;
	int nextOccupiedColumn = nextEmptyColumn;
	while (nextEmptyColumn < boardColumns && nextOccupiedColumn < boardColumns)
	{
		//find next empty column
		while (nextEmptyColumn < boardColumns && boardArray[boardRows - 1][nextEmptyColumn] != 0)
		{
			nextEmptyColumn++;
		}
		if (nextEmptyColumn < boardColumns)
		{
			//find next occupied column
			nextOccupiedColumn = nextEmptyColumn + 1;
			while (nextOccupiedColumn < boardColumns && boardArray[boardRows - 1][nextOccupiedColumn] == 0)
			{
				nextOccupiedColumn++;
			}
			if (nextOccupiedColumn < boardColumns)
			{
				for (int i = 0; i < boardRows; i++)
				{
					boardArray[i][nextEmptyColumn] = boardArray[i][nextOccupiedColumn];
					boardArray[i][nextOccupiedColumn] = 0;
				}
			}
		}
	}
}

bool CTestGameBoard::IsFinished() const
{
	//left to right
	for (int j = 0; j < boardColumns; j++)
	{
		//bottom to top
		for (int i = boardRows - 1; i >= 0; i--)
		{
			int color = boardArray[i][j];
			//stop when black
			if (color == 0)
			{
				break;
			}
			else
			{
				//check up and right
				if (i - 1 >= 0 && boardArray[i - 1][j] == color)
				{
					return false;
				}
				else if (j + 1 < boardColumns && boardArray[i][j + 1] == color)
				{
					return false;
				}
			}
		}
	}
	return true;
}