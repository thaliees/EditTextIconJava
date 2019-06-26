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
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText.setOnIconAction(updateText);
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
                editText.clearFocus();
                // Hide keyboard after of update the TextView
                InputMethodManager service = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                service.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }
    };

    private View.OnClickListener hideKeyboard = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            editText.clearFocus();
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    };
}
