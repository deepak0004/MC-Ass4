package com.todo.deepak.taskmanager;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StoreData extends AppCompatActivity {

    EditText title, content;
    Button save_note;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_data);

        title = (EditText) findViewById(R.id.store_title);
        content = (EditText) findViewById(R.id.store_content);
        save_note = (Button) findViewById(R.id.save_note);
        database = this.openOrCreateDatabase("mc_4", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS todo_app (todo_heading VARCHAR, todo_content VARCHAR)");
        save_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title_data = title.getText().toString();
                String title_content = content.getText().toString();

                if(!TextUtils.isEmpty(title_content) && !TextUtils.isEmpty(title_data)){
                    try{
                        database.execSQL("INSERT INTO todo_app (todo_heading, todo_content) VALUES ('"+title_data+"', '"+title_content+"')");
                        Toast.makeText(getApplicationContext(), "Note Created!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }catch (Exception e){

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Please enter both the fields.", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
