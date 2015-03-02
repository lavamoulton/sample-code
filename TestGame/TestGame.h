
// TestGame.h : main header file for the TestGame application
//
#pragma once

#ifndef __AFXWIN_H__
	#error "include 'stdafx.h' before including this file for PCH"
#endif

#include "resource.h"       // main symbols


// CTestGameApp:
// See TestGame.cpp for the implementation of this class
//

class CTestGameApp : public CWinApp
{
public:
	CTestGameApp();


// Overrides
public:
	virtual BOOL InitInstance();

// Implementation
	afx_msg void OnAppAbout();
	DECLARE_MESSAGE_MAP()
};

extern CTestGameApp theApp;
