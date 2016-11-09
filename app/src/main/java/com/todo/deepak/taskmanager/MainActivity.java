package com.todo.deepak.taskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    SQLiteDatabase database;

    ArrayList<String> t = new ArrayList<String>();
    ArrayList<String> c = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recylerView);

        database = this.openOrCreateDatabase("mc_4", MODE_PRIVATE, null);

        try{
            Cursor crs = database.rawQuery("SELECT * FROM todo_app", null);

            int title_index = crs.getColumnIndex("todo_heading");
            int details_index = crs.getColumnIndex("todo_content");

            if(crs.moveToFirst()){
                do{
                    t.add(crs.getString(title_index));
                    c.add(crs.getString(details_index));
                }while(crs.moveToNext());
            }

        }catch (Exception e){

        }

        adapter = new RecylerAdapter(t, c);
        layoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        t.clear();
        c.clear();

        try{
            Cursor crs = database.rawQuery("SELECT * FROM todo_app", null);

            int title_index = crs.getColumnIndex("todo_heading");
            int details_index = crs.getColumnIndex("todo_content");

            if(crs.moveToFirst()){
                do{
                    t.add(crs.getString(title_index));
                    c.add(crs.getString(details_index));
                }while(crs.moveToNext());
            }

        }catch (Exception e){

        }

        adapter = new RecylerAdapter(t, c);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_start:
                Intent i = new Intent(this, StoreData.class);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
