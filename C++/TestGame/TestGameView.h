
// TestGameView.h : interface of the CTestGameView class
//

#pragma once


class CTestGameView : public CView
{
protected: // create from serialization only
	CTestGameView();
	DECLARE_DYNCREATE(CTestGameView)

// Attributes
public:
	CTestGameDoc* GetDocument() const;

// Operations
public:

// Overrides
public:
	virtual void OnDraw(CDC* pDC);  // overridden to draw this view
	virtual BOOL PreCreateWindow(CREATESTRUCT& cs);
protected:

// Implementation
public:

	void ResizeWindow();
	void SetColorNum(int numColors);

	virtual ~CTestGameView();
#ifdef _DEBUG
	virtual void AssertValid() const;
	virtual void Dump(CDumpContext& dc) const;
#endif

protected:

// Generated message map functions
protected:
	DECLARE_MESSAGE_MAP()
public:
	virtual void OnInitialUpdate();
	afx_msg void OnLButtonDown(UINT nFlags, CPoint point);
	afx_msg void OnLevel3colors();
	afx_msg void OnUpdateLevel3colors(CCmdUI *pCmdUI);
	afx_msg void OnLevel4colors();
	afx_msg void OnUpdateLevel4colors(CCmdUI *pCmdUI);
	afx_msg void OnLevel5colors();
	afx_msg void OnUpdateLevel5colors(CCmdUI *pCmdUI);
	afx_msg void OnMenu();
	afx_msg void OnUpdateMenu(CCmdUI *pCmdUI);
	afx_msg void OnLevel7colors();
	afx_msg void OnUpdateLevel7colors(CCmdUI *pCmdUI);
};

#ifndef _DEBUG  // debug version in TestGameView.cpp
inline CTestGameDoc* CTestGameView::GetDocument() const
   { return reinterpret_cast<CTestGameDoc*>(m_pDocument); }
#endif

