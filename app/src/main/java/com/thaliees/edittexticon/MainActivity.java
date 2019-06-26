package com.thaliees.edittexticon;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditTextIcon editText;
    private EditTextIconHide editText2;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText.setOnIconAction(updateText);
        editText2 = findViewById(R.id.editText2);
        editText2.setOnIconHideAction(updateEditText);

        text = findViewById(R.id.textView);

        // This will be for when any part of the screen is touched, the keyboard is hidden
        ConstraintLayout screen = findViewById(R.id.screen);
        screen.setOnClickListener(hideKeyboard);
    }

    private OnIconAction updateText = new OnIconAction() {
        @Override
        public void doAction() {
            if (editText.getText().length() > 0) {
                text.setText(editText.getText());
                editText.setText("");
                // Hide keyboard after of update the first EditText
                hideKeyboard(editText);
            }
        }
    };

    private OnIconHideAction updateEditText = new OnIconHideAction() {
        @Override
        public void doAction() {
            if (editText2.getText().length() > 0) {
                editText.setText(editText2.getText());
                editText2.setText("");
                // Hide keyboard after of update the TextView
                hideKeyboard(editText2);
            }
        }
    };

    private View.OnClickListener hideKeyboard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.clearFocus();
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    };

    private void hideKeyboard(View v){
        v.clearFocus();
        InputMethodManager service = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        service.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
