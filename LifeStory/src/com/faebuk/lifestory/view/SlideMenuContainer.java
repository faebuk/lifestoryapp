package com.faebuk.lifestory.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class SlideMenuContainer extends LinearLayout {

	// Referenzen auf die in dieser View verwendeten Groups
	private View vMenu;
	private View vContent;

	// Animationenkonstanten
	private static final int menuAnimationDuration = 500;
	private static final int menuAnimationPollingInterval = 16;

	// Objekte für die Animation
	protected Scroller menuAnimationScroller = new Scroller(this.getContext(),
			new LinearInterpolator());
	protected Runnable menuAnimationRunnable = new AnimationRunnable();
	protected Handler menuAnimationHandler = new Handler();

	// Layoutkonstante
	protected static final int MENU_MARGIN = 150;

	// Zustände des Menüs
	public enum MenuStates {
		OPEN, CLOSED, OPENING, CLOSING
	};

	// Informationsattribute der Position
	protected int currentContentOffSet = 0;
	protected MenuStates currentMenuState = MenuStates.CLOSED;

	// Konstruktor
	public SlideMenuContainer(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onAttachedToWindow() {
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
			this.currentMenuState = MenuStates.OPENING;
			this.vMenu.setVisibility(View.VISIBLE);
			this.menuAnimationScroller.startScroll(0, 0, this.getMenuWidth(),
					0, menuAnimationDuration);
			break;
		case OPEN:
			this.currentMenuState = MenuStates.CLOSING;
			this.menuAnimationScroller.startScroll(this.currentContentOffSet,
					0, -this.currentContentOffSet, 0, menuAnimationDuration);
			break;
		default:
			return;

		}
		this.menuAnimationHandler.postDelayed(this.menuAnimationRunnable,
				menuAnimationPollingInterval);

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

	private void adjustContentPosition(boolean isAnimationOngoing) {
		int scrollerOffset = this.menuAnimationScroller.getCurrX();

		this.vContent.offsetLeftAndRight(scrollerOffset
				- this.currentContentOffSet);

		this.currentContentOffSet = scrollerOffset;

		this.invalidate();

		if (isAnimationOngoing)
			this.menuAnimationHandler.postDelayed(this.menuAnimationRunnable,
					menuAnimationPollingInterval);
		else
			this.onMenuTransitionComplete();
	}

	private void onMenuTransitionComplete() {
		switch (this.currentMenuState) {
		case OPENING:
			this.currentMenuState = MenuStates.OPEN;
			break;
		case CLOSING:
			this.currentMenuState = MenuStates.CLOSED;
			this.vMenu.setVisibility(View.GONE);
			break;
		default:
			return;
		}
	}

	protected class SmoothInterpolator implements Interpolator {

		@Override
		public float getInterpolation(float t) {
			return (float) Math.pow(t - 1, 5) + 1;
		}

	}

	protected class AnimationRunnable implements Runnable {
		@Override
		public void run() {
			SlideMenuContainer.this
					.adjustContentPosition(SlideMenuContainer.this.menuAnimationScroller
							.computeScrollOffset());
		}

	}
}