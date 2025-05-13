package com.example.tutorial_102;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🧠 Hook into your TextView and Button
        textView = findViewById(R.id.textView); // make sure this ID matches your layout
        button = findViewById(R.id.button);     // same for this
    }

    // 🚀 Called when button is clicked
    public void startCountdown(View view) {
        Handler handler = new Handler();
        final int[] count = {5};

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (count[0] > 0) {
                    animateText(String.valueOf(count[0]));
                    count[0]--;
                    handler.postDelayed(this, 1000);
                } else {
                    animateText("HURRAY!");
                    handler.postDelayed(() -> textView.setText(""), 1000);
                }
            }
        };

        handler.post(runnable);
    }

    private void animateText(String text) {
        textView.setText(text);
        textView.setScaleX(3f); // start BIG
        textView.setScaleY(3f);

        // Animate it back to normal scale
        textView.animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(700); // shrink time in milliseconds
    }


}
