package kr.ac.tukorea.ge.scgyong.cookierun.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.scgyong.cookierun.R;
import kr.ac.tukorea.ge.scgyong.cookierun.databinding.ActivityMainBinding;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
    }

    public void onBtnStartGame(View view) {
        startGame();
    }

    public void resetHighScore(View view) {
        SharedPreferences prefs = this.getSharedPreferences("HighScore", Context.MODE_PRIVATE);
        prefs.edit().putInt("Score", 0).apply();
        Toast.makeText(this, "Data reset completed.", Toast.LENGTH_SHORT).show();
    }

    private void startGame() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}