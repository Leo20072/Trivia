package com.example.trivia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btna1,btna2,btna3,btna4;
    private TextView tvQuestion;
    private TextView tvQuestionNumber,tvPoints,tvGameOver;

    private Collection collection;
    private Question q;
    private int Points = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // הפניות
        btna1 = findViewById(R.id.btna1);
        btna2 = findViewById(R.id.btna2);
        btna3 = findViewById(R.id.btna3);
        btna4 = findViewById(R.id.btna4);

        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuestionNumber = findViewById(R.id.tvQuestionNumber);
        tvPoints = findViewById(R.id.tvPoints);
        tvGameOver = findViewById(R.id.tvGameOver);

        // מאזינים
        btna1.setOnClickListener(this);
        btna2.setOnClickListener(this);
        btna3.setOnClickListener(this);
        btna4.setOnClickListener(this);

        tvGameOver.setVisibility(View.INVISIBLE); // הופך את זה לבלתי נראה

        collection = new Collection();
        collection.initQuestion();

        nextQuestion();
    }

    private void nextQuestion() {
        if (collection.isNotLastQuestion())
        {
            q = collection.getNextQuestion();

            tvQuestion.setText(q.getQuestion());
            btna1.setText(q.getA1());
            btna2.setText(q.getA2());
            btna3.setText(q.getA3());
            btna4.setText(q.getA4());
        }

        else
        {
            tvGameOver.setVisibility(View.VISIBLE);
            createDialog();
        }

    }

    private void createDialog() {
        CustomDialog customDialog = new CustomDialog(this);
        customDialog.show();

    }

    @Override
    public void onClick(View v) {
        if(v == btna1)
        {
            if(q.getCorrect() == 1)
                Points++;
        }
        if(v == btna2)
        {
            if(q.getCorrect() == 2)
                Points++;
        }
        if(v == btna3)
        {
            if(q.getCorrect() == 3)
                Points++;
        }
        if(v == btna4)
        {
            if(q.getCorrect() == 4)
                Points++;
        }

        tvPoints.setText("Points: "+ Points);
        if(collection.isNotLastQuestion())
        {
            tvQuestionNumber.setText("Question number: " + (collection.getIndex() + 1));
        }

        nextQuestion();


    }

    public void reset() {
        this.Points = 0;
        collection.initQuestion();
        tvPoints.setText("Points: "+ 0);
        tvQuestionNumber.setText("Question number: "+0);
        tvGameOver.setVisibility(View.INVISIBLE);
        this.nextQuestion();

    }
}