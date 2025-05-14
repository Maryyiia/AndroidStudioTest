package com.example.tutorial_102;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView timerView, outcomeMessage, bombInstruction;
    Button disarmButton, replayButton;
    RelativeLayout bombLayout;

    CountDownTimer countDownTimer;
    boolean isFlashing = false;
    boolean disarmed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerView = findViewById(R.id.timerView);
        bombLayout = findViewById(R.id.bombLayout);
        disarmButton = findViewById(R.id.disarmButton);
        outcomeMessage = findViewById(R.id.outcomeMessage);
        replayButton = findViewById(R.id.replayButton);
        bombInstruction = findViewById(R.id.bombInstruction);

        disarmButton.setOnClickListener(view -> disarmed = true);
        replayButton.setOnClickListener(view -> recreate()); // restart activity

        startBombSequence();
    }

    private void startBombSequence() {
        countDownTimer = new CountDownTimer(10000, 1000) {
            int timeLeft = 10;

            @Override
            public void onTick(long millisUntilFinished) {
                if (disarmed) {
                    showOutcome("Wow. You actually did it.\nYour mom is… mildly proud.");
                    cancel();
                    return;
                }

                timerView.setText(String.format("00:%02d", timeLeft--));
                toggleFlashing();
            }

            @Override
            public void onFinish() {
                if (!disarmed) {
                    showOutcome("💥 BOOM.\nYour life insurance has been notified.\n\nYour mom still doesn't love you.");
                }
            }
        };

        countDownTimer.start();
    }

    private void showOutcome(String message) {
        disarmButton.setVisibility(View.GONE);
        timerView.setVisibility(View.GONE);
        bombInstruction.setVisibility(View.GONE);
        outcomeMessage.setVisibility(View.VISIBLE);
        outcomeMessage.setText(message);
        replayButton.setVisibility(View.VISIBLE);
    }

    private void toggleFlashing() {
        isFlashing = !isFlashing;
        bombLayout.setBackgroundColor(isFlashing ? 0xFFFF0000 : 0xFF000000); // red/black
    }
}
