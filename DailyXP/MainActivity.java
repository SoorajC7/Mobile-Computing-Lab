package com.example.dailyxp;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText taskInput;
    Button addBtn, resetBtn;
    ListView listView;
    ProgressBar progressBar;
    TextView xpText, levelText;

    ArrayList<String> tasks;
    TaskAdapter adapter;

    int xp = 0;
    int level = 1;
    int completedTasks = 0;

    SharedPreferences prefs;

    String[] messages = {
            "Keep going 🔥",
            "You're on fire 🚀",
            "Small wins matter 💪",
            "Consistency = Success 🧠"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskInput = findViewById(R.id.taskInput);
        addBtn = findViewById(R.id.addBtn);
        resetBtn = findViewById(R.id.resetBtn);
        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);
        xpText = findViewById(R.id.xpText);
        levelText = findViewById(R.id.levelText);

        prefs = getSharedPreferences("XP_DATA", MODE_PRIVATE);

        xp = prefs.getInt("xp", 0);
        level = prefs.getInt("level", 1);

        tasks = new ArrayList<>();
        adapter = new TaskAdapter(this, tasks);
        listView.setAdapter(adapter);

        updateUI();

        addBtn.setOnClickListener(v -> {
            String task = taskInput.getText().toString().trim();

            if (!task.isEmpty() && task.length() > 2) {
                tasks.add(task);
                adapter.notifyDataSetChanged();
                taskInput.setText("");
            } else {
                Toast.makeText(this, "Enter valid task", Toast.LENGTH_SHORT).show();
            }
        });

        listView.setOnItemClickListener((parent, view, position, id) -> {
            tasks.remove(position);
            adapter.notifyDataSetChanged();

            addXP(10);

            MediaPlayer mp = MediaPlayer.create(this, R.raw.click);
            mp.start();

            Random r = new Random();
            Toast.makeText(this, "+10 XP 🚀 " + messages[r.nextInt(messages.length)], Toast.LENGTH_SHORT).show();
        });

        resetBtn.setOnClickListener(v -> {
            xp = 0;
            level = 1;
            completedTasks = 0;
            tasks.clear();
            adapter.notifyDataSetChanged();
            saveData();
            updateUI();
        });
    }

    private void addXP(int value) {
        xp += value;
        completedTasks++;

        if (completedTasks == 5) {
            xp += 20;
            Toast.makeText(this, "Bonus +20 XP 🎉", Toast.LENGTH_SHORT).show();
        }

        if (xp >= 100) {
            level++;
            xp = 0;
        }

        saveData();
        updateUI();
    }

    private void updateUI() {
        xpText.setText("XP: " + xp);
        levelText.setText("Level: " + level + " (" + getLevelTitle(level) + ")");
        progressBar.setProgress(xp);
    }

    private void saveData() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("xp", xp);
        editor.putInt("level", level);
        editor.apply();
    }

    private String getLevelTitle(int level) {
        if (level == 1) return "Beginner 🌱";
        else if (level == 2) return "Warrior ⚔️";
        else if (level == 3) return "Champion 🏆";
        else return "Legend 🔥";
    }
}
