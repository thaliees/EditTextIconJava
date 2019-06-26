package com.thaliees.edittexticon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

interface OnIconAction {
    void doAction();
}

public class EditTextIcon extends AppCompatEditText {
    private OnIconAction onIconAction;

    public EditTextIcon(Context context) {
        super(context);
        initEditTextIcon();
    }

    public EditTextIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEditTextIcon();
    }

    public EditTextIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEditTextIcon();
    }

    private void initEditTextIcon(){
        // Indicate icon
        Drawable icon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_enviar, null);
        // Set icon to the EditText
        setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText editText = (EditText) v;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    // Identify the position where the EditText is played
                    // Launch the event
                    if (event.getRawX() >= editText.getRight() - editText.getCompoundPaddingRight()) {
                        onIconAction.doAction();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void setOnIconAction(OnIconAction eventListener){
        onIconAction = eventListener;
    }
}
