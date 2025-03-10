package com.example.trivia;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;
    private FbModule fbModule;
    private ConstraintLayout ll; // הפנייה ConstraintLayout
    private String Colorb = "color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.main);

        fbModule = new FbModule(this); // מחלקה נותנים את הכתובת  שיכול לקרוא פעולה שפה

        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == RESULT_OK)
                        {
                            Intent data = o.getData();
                           String str = data.getStringExtra("color");
                            fbModule.writeBackgroundColorToFb(str);

                        }

                    }
                });
    }

    public void onClickStart(View view) {
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("color", Colorb);
        startActivity(intent);
    }

    public void onClickSetting(View view) {
        Intent i = new Intent(this, SettingActivity.class);
        resultLauncher.launch(i);
    }

    public void onClickInstruction(View view) {
        Intent i = new Intent(this, InstructionActivity.class);
        startActivity(i);
    }

    public void setNewColorFromFb(String str) {
        // הפיירבייס קורא לפעולה בפעם הראשונה
        // ואחרי כל פעם שהמשתמש משנה את הצבע
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        setBackroundColor(str);
    }

    public void setBackroundColor(String color)
    {
        Colorb = color;
        switch (color)
        {
            case "Red":
            {
                ll.setBackgroundColor(Color.RED);
                break;
            }
            case "Blue":
            {
                ll.setBackgroundColor(Color.BLUE);
                break;
            }
            case "Pink":
            {
                ll.setBackgroundColor(Color.rgb(255,192,203));
                break;
            }
            case "Yellow":
            {
                ll.setBackgroundColor(Color.YELLOW);

                break;
            }
            default:
                ll.setBackgroundColor(Color.WHITE);

        }
    }
}