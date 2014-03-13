/**
 * Enthält verschiedene Methoden um zb die Fonts von TextViews oder Buttons zuenden.
 * Äuerdem ist hier die Methode setPressedEfect(View v) implementiert, was bewirkt,
 * dass der Button-Press-Efect auftaucht.
 * 
 * @author Robert Hein
 */
package com.faebuk.lifestory.help;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class Manager {
	// Globale Variable
	public static Typeface fontstyle;

	// Globale Variable

	public static void TypeFaceTextView(TextView tv, AssetManager asm) {
		fontstyle = Typeface.createFromAsset(asm, "whatever it takes.ttf");
		tv.setTypeface(fontstyle);
	}
	
	public static void TypeFaceEditText(EditText et, AssetManager asm) {
		fontstyle = Typeface.createFromAsset(asm, "whatever it takes.ttf");
		et.setTypeface(fontstyle);
	}
	
	public static void TypeFaceCheckBox(CheckBox cb, AssetManager asm) {
		fontstyle = Typeface.createFromAsset(asm, "whatever it takes.ttf");
		cb.setTypeface(fontstyle);
	}

	public static void TypeFaceButton(Button btn, AssetManager asm) {
		fontstyle = Typeface.createFromAsset(asm, "whatever it takes.ttf");
		btn.setTypeface(fontstyle);
	}

	public static void setPressedEfect(View v) {
		AlphaAnimation btn_anim = new AlphaAnimation(0.8F, 0F);
		v.startAnimation(btn_anim);
	}
}