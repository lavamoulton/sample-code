
// TestGameDoc.h : interface of the CTestGameDoc class
//


#pragma once
#include "TestGameBoard.h"

class CTestGameDoc : public CDocument
{
protected: // create from serialization only
	CTestGameDoc();
	DECLARE_DYNCREATE(CTestGameDoc)

// Attributes
public:

// Operations
public:
	//Game Board Functions
	COLORREF GetColor(int row, int col) { return board.GetColor(row, col); }
	void SetBoard() { board.SetBoard(); }
	int GetWidth() { return board.GetWidth(); }
	int GetHeight() { return board.GetHeight(); }
	int GetColumns() { return board.GetColumns(); }
	int GetRows() { return board.GetRows(); }
	void DestroyBoard() { board.DestroyBoard(); }
	bool IsFinished() { return board.IsFinished(); }
	int Delete(int row, int col) { return board.Delete(row, col); }
	int GetCount() { return board.GetCount(); }

	//get colors
	int GetNumColors() { return board.GetNumColors(); }
	void SetNumColors(int colorNum);

// Overrides
public:
	virtual BOOL OnNewDocument();
	virtual void Serialize(CArchive& ar);
#ifdef SHARED_HANDLERS
	virtual void InitializeSearchContent();
	virtual void OnDrawThumbnail(CDC& dc, LPRECT lprcBounds);
#endif // SHARED_HANDLERS

// Implementation
public:
	virtual ~CTestGameDoc();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:
	//Game Board variable
	CTestGameBoard board;

// Generated message map functions
protected:
	DECLARE_MESSAGE_MAP()

#ifdef SHARED_HANDLERS
	// Helper function that sets search content for a Search Handler
	void SetSearchContent(const CString& value);
#endif // SHARED_HANDLERS
};
