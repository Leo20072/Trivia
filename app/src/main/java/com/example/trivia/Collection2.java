package com.example.trivia;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Collection2 {
    private Question[] arr;
    private int index; // מספר השאלה הבאה בתור

    public Collection2() {
        Question q1 = new Question("1+10", "7","11","3","100",2);
        Question q2 = new Question("1+2", "8","2","3","100",3);
        Question q3 = new Question("1+3", "6","2","4","100",3);
        Question q4 = new Question("1+4", "5","2","3","100",1);
        Question q5 = new Question("1+0", "1","2","3","100",1);

        arr = new Question[5];
        arr[0] = q1;
        arr[1] = q2;
        arr[2] = q3;
        arr[3] = q4;
        arr[4] = q5;

        List<Question> list = Arrays.asList(arr); // המרה של המערך לרשימה
        Collections.shuffle(list); // ערבוב הרשימה
        arr = list.toArray(new Question[0]); // המרה הרשימה בחזרה למערך

    }
    public void initQuestion()
    {
        index = 0;
    }

    public Question getNextQuestion()
    {
        // הפעולה מחזירה הפנייה לשאלה הבאה
        Question q = arr[index];
        index++;
        return q;
    }

    public boolean isNotLastQuestion(){
        // הפעולה מחזירה אמת אם אנו בשאלה האחרונה
        return (index < arr.length); // אם לא בסוף ArrayList
        // if not at the end of the ArrayList
        // }
    }

    public int getIndex(){return index;}
}
