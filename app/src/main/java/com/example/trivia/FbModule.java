package com.example.trivia;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FbModule {



    private Context context;   // לשמור את הכתובת

    public FbModule(Context context) {
        this.context = context;

        FirebaseDatabase database = FirebaseDatabase.getInstance(); // הפנייה ל FB
        DatabaseReference reference = database.getReference("color"); // הפנייה לצומת
        // לשים מאזין
        // כאשר הצבע משתנה
        reference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String str = snapshot.getValue(String.class);
                        // קוראים את הצבע
                        ((MainActivity)context).setNewColorFromFb(str);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

    public void writeBackgroundColorToFb(String str)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // הפנייה ל FB
        DatabaseReference reference = database.getReference("color"); // הפנייה לצומת
        reference.setValue(str);
    }
}
