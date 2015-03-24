
// TestGameView.cpp : implementation of the CTestGameView class
//

#include "stdafx.h"
// SHARED_HANDLERS can be defined in an ATL project implementing preview, thumbnail
// and search filter handlers and allows sharing of document code with that project.
#ifndef SHARED_HANDLERS
#include "TestGame.h"
#endif

#include "TestGameDoc.h"
#include "TestGameView.h"

#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// CTestGameView

IMPLEMENT_DYNCREATE(CTestGameView, CView)

BEGIN_MESSAGE_MAP(CTestGameView, CView)
	ON_WM_LBUTTONDOWN()
	ON_WM_ERASEBKGND()
	ON_COMMAND(ID_LEVEL_3COLORS, &CTestGameView::OnLevel3colors)
	ON_UPDATE_COMMAND_UI(ID_LEVEL_3COLORS, &CTestGameView::OnUpdateLevel3colors)
	ON_COMMAND(ID_LEVEL_4COLORS, &CTestGameView::OnLevel4colors)
	ON_UPDATE_COMMAND_UI(ID_LEVEL_4COLORS, &CTestGameView::OnUpdateLevel4colors)
	ON_COMMAND(ID_LEVEL_5COLORS, &CTestGameView::OnLevel5colors)
	ON_UPDATE_COMMAND_UI(ID_LEVEL_5COLORS, &CTestGameView::OnUpdateLevel5colors)
	ON_COMMAND(ID_Menu, &CTestGameView::OnMenu)
	ON_UPDATE_COMMAND_UI(ID_Menu, &CTestGameView::OnUpdateMenu)
	ON_COMMAND(ID_LEVEL_7COLORS, &CTestGameView::OnLevel7colors)
	ON_UPDATE_COMMAND_UI(ID_LEVEL_7COLORS, &CTestGameView::OnUpdateLevel7colors)
END_MESSAGE_MAP()

// CTestGameView construction/destruction

CTestGameView::CTestGameView()
{
	// TODO: add construction code here
}

CTestGameView::~CTestGameView()
{
}

BOOL CTestGameView::PreCreateWindow(CREATESTRUCT& cs)
{
	// TODO: Modify the Window class or styles here by modifying
	//  the CREATESTRUCT cs

	return CView::PreCreateWindow(cs);
}

// CTestGameView drawing

void CTestGameView::OnDraw(CDC* pDC)
{
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;

	// TODO: add draw code for native data here
	// save the current state
	int nDCSave = pDC->SaveDC();
	//get the client rectangle
	CRect rcClient;
	GetClientRect(&rcClient);
	//get background color of board
	COLORREF boardColor = pDoc->GetColor(-1, -1);
	//draw background
	pDC->FillSolidRect(&rcClient, boardColor);
	//creates brush
	CBrush br;
	br.CreateStockObject(HOLLOW_BRUSH);
	CBrush* pbrOld = pDC->SelectObject(&br);
	//draw squares
	for (int i = 0; i < pDoc->GetRows(); i++)
	{
		for (int j = 0; j < pDoc->GetColumns(); j++)
		{
			//get the color
			boardColor = pDoc->GetColor(i, j);
			//size and position
			CRect gameBlock;
			gameBlock.top = i * pDoc->GetHeight();
			gameBlock.left = j * pDoc->GetWidth();
			gameBlock.right = gameBlock.left + pDoc->GetWidth();
			gameBlock.bottom = gameBlock.top + pDoc->GetHeight();
			//fill in with color
			pDC->FillSolidRect(&gameBlock, boardColor);
			//draw outline
			pDC->Rectangle(&gameBlock);
		}
	}
	//restore device settings
	pDC->RestoreDC(nDCSave);
	br.DeleteObject();
}


// CTestGameView diagnostics

#ifdef _DEBUG
void CTestGameView::AssertValid() const
{
	CView::AssertValid();
}

void CTestGameView::Dump(CDumpContext& dc) const
{
	CView::Dump(dc);
}

void CTestGameView::OnInitialUpdate()
{
	CView::OnInitialUpdate();

	ResizeWindow();
}

CTestGameDoc* CTestGameView::GetDocument() const // non-debug version is inline
{
	ASSERT(m_pDocument->IsKindOf(RUNTIME_CLASS(CTestGameDoc)));
	return (CTestGameDoc*)m_pDocument;
}
#endif //_DEBUG

void CTestGameView::ResizeWindow()
{
	//pointer to the document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//get size of window and area
	CRect rcClient, rcWindow;
	GetClientRect(&rcClient);
	GetParentFrame()->GetWindowRect(&rcWindow);
	//difference
	int diffWidth = rcWindow.Width() - rcClient.Width();
	int diffHeight = rcWindow.Height() - rcClient.Height();
	//change window size
	rcWindow.right = rcWindow.left + pDoc->GetWidth() * pDoc->GetColumns() + diffWidth;
	rcWindow.bottom = rcWindow.top + pDoc->GetHeight() * pDoc->GetRows() + diffHeight;
	GetParentFrame()->MoveWindow(&rcWindow);
}

void CTestGameView::SetColorNum(int numColors)
{
	//get a pointer to the document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set number of colors
	pDoc->SetNumColors(numColors);
	//redraw
	Invalidate();
	UpdateWindow();
}

void CTestGameView::OnLButtonDown(UINT nFlags, CPoint point)
{
	//get a pointer to the document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//get row and col of block
	int row = point.y / pDoc->GetHeight();
	int col = point.x / pDoc->GetWidth();
	//delete blocks from document
	int count = pDoc->Delete(row, col);
	//check if blocks were deleted
	if (count > 0)
	{
		//force the view to redraw
		Invalidate();
		UpdateWindow();
		//check if game is over
		if (pDoc->IsFinished())
		{
			//get the count remaining
			int remaining = pDoc->GetCount();
			CString message;
			message.Format(_T("No more moves left\nBlocks remaining: %d"), remaining);
			//display results to user
			MessageBox(message, _T("Game Over"), MB_OK | MB_ICONINFORMATION);
		}
	}
	// Default OnButtonDown
	CView::OnLButtonDown(nFlags, point);
}
// CTestGameView message handlers


void CTestGameView::OnLevel3colors()
{
	//pointer to document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set number of colors
	pDoc->SetNumColors(3);
	//redraw
	Invalidate();
	UpdateWindow();
}


void CTestGameView::OnUpdateLevel3colors(CCmdUI *pCmdUI)
{
	//get a pointer to the document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set check
	pCmdUI->SetCheck(pDoc->GetNumColors() == 3);
}


void CTestGameView::OnLevel4colors()
{
	//pointer to document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set number of colors
	pDoc->SetNumColors(4);
	//redraw
	Invalidate();
	UpdateWindow();
}


void CTestGameView::OnUpdateLevel4colors(CCmdUI *pCmdUI)
{
	//get a pointer to the document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set check
	pCmdUI->SetCheck(pDoc->GetNumColors() == 4);
}


void CTestGameView::OnLevel5colors()
{
	//pointer to document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set number of colors
	pDoc->SetNumColors(5);
	//redraw
	Invalidate();
	UpdateWindow();
}


void CTestGameView::OnUpdateLevel5colors(CCmdUI *pCmdUI)
{
	//get a pointer to the document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set check
	pCmdUI->SetCheck(pDoc->GetNumColors() == 5);
}


void CTestGameView::OnMenu()
{
	//pointer to document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set number of colors
	pDoc->SetNumColors(6);
	//redraw
	Invalidate();
	UpdateWindow();
}


void CTestGameView::OnUpdateMenu(CCmdUI *pCmdUI)
{
	//get a pointer to the document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set check
	pCmdUI->SetCheck(pDoc->GetNumColors() == 6);
}


void CTestGameView::OnLevel7colors()
{
	//pointer to document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set number of colors
	pDoc->SetNumColors(7);
	//redraw
	Invalidate();
	UpdateWindow();
}


void CTestGameView::OnUpdateLevel7colors(CCmdUI *pCmdUI)
{
	//get a pointer to the document
	CTestGameDoc* pDoc = GetDocument();
	ASSERT_VALID(pDoc);
	if (!pDoc)
		return;
	//set check
	pCmdUI->SetCheck(pDoc->GetNumColors() == 7);
}
