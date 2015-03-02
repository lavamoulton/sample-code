#pragma once
class CTestGameBoard
{
public:
	//Constructor and Destructor
	CTestGameBoard();
	~CTestGameBoard();

	//Creates the board
	void SetBoard();

	//Get the color of a location
	COLORREF GetColor(int row, int col);

	//Board size functions
	int GetWidth() const { return boardWidth; }
	int GetHeight() const { return boardHeight; }
	int GetColumns() const { return boardColumns; }
	int GetRows() const { return boardRows; }

	//Delete board
	void DestroyBoard();

	//Is game over
	bool IsFinished() const;
	//blocks remaining
	int GetCount() const { return numRemaining; }
	//delete adjacent same colored blocks
	int Delete(int row, int col);

	//get number of colors
	int GetNumColors() { return numColors; }
	void SetNumColors(int colorNum) { numColors = (numColors >= 3 && numColors <= 7) ? colorNum : numColors; }


private:
	//Create board
	void CreateBoard();

	//direction enum for block deletion
	enum Direction
	{
		DIRECTION_UP,
		DIRECTION_DOWN,
		DIRECTION_RIGHT,
		DIRECTION_LEFT
	};
	//recursive deletion function
	int NeighborBlocks(int row, int col, int color, Direction direction);
	//compact board
	void SqueezeBoard();

	//2D array pointer at the board
	int** boardArray;

	//Colors
	COLORREF gameColors[8];

	//Get board dimensions
	int boardWidth;
	int boardHeight;
	int boardColumns;
	int boardRows;
	int numRemaining;

	//number of colors
	int numColors;
};

