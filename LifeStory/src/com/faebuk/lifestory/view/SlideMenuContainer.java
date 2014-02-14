package com.faebuk.lifestory.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class SlideMenuContainer extends LinearLayout {

	private View vMenu;
	private View vContent;

	protected static final int MENU_MARGIN = 150;

	public enum MenuStates {
		OPEN, CLOSED
	};

	protected int currentContentOffSet = 0;
	protected MenuStates currentMenuState = MenuStates.CLOSED;

	public SlideMenuContainer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SlideMenuContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SlideMenuContainer(Context context) {
		super(context);
	}

	protected void onAttachedWindow() {
		super.onAttachedToWindow();

		this.vMenu = this.getChildAt(0);
		this.vContent = this.getChildAt(1);

		this.vMenu.setVisibility(View.GONE);
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		if (changed)
			this.calculateChildDimensions();

		this.vMenu.layout(left, top, right - MENU_MARGIN, bottom);

		this.vContent.layout(left + this.currentContentOffSet, top, right
				+ this.currentContentOffSet, bottom);
	}

	public void toggleMenu() {
		switch (this.currentMenuState) {
		case CLOSED:
			this.vMenu.setVisibility(View.VISIBLE);
			this.currentContentOffSet = this.getMenuWidth();
			this.vContent.offsetLeftAndRight(currentContentOffSet);
			this.currentMenuState = MenuStates.OPEN;
			break;
		case OPEN:
			this.vContent.offsetLeftAndRight(-currentContentOffSet);
			this.currentContentOffSet = 0;
			this.currentMenuState = MenuStates.CLOSED;
			this.vMenu.setVisibility(View.GONE);
			break;
		}
		this.invalidate();
	}

	private int getMenuWidth() {
		return this.vMenu.getLayoutParams().width;
	}

	private void calculateChildDimensions() {
		this.vContent.getLayoutParams().height = this.getHeight();
		this.vContent.getLayoutParams().width = this.getWidth();

		this.vMenu.getLayoutParams().width = this.getWidth() - MENU_MARGIN;
		this.vMenu.getLayoutParams().height = this.getHeight();
	}

}
