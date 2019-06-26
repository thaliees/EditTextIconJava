package com.thaliees.edittexticon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

interface OnIconHideAction {
    void doAction();
}

public class EditTextIconHide extends AppCompatEditText {
    private Drawable icon;
    private OnIconHideAction onIconHideAction;

    public EditTextIconHide(Context context) {
        super(context);
        initEditTextIcon();
    }

    public EditTextIconHide(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEditTextIcon();
    }

    public EditTextIconHide(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEditTextIcon();
    }

    private void initEditTextIcon(){
        // Indicate icon
        icon = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_enviar, null);
        // To know whether or not to show the icon
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Set icon to the EditText
                if (s.length() > 0)
                    setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
                else setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                EditText editText = (EditText) v;
                if (event.getAction() == MotionEvent.ACTION_UP){
                    // Identify the position where the EditText is played
                    // Launch the event
                    if (event.getRawX() >= editText.getRight() - editText.getCompoundPaddingRight()) {
                        onIconHideAction.doAction();
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void setOnIconHideAction(OnIconHideAction eventListener){
        onIconHideAction = eventListener;
    }
}
