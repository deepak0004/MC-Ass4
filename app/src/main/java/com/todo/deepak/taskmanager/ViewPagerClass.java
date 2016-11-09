package com.todo.deepak.taskmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewPagerClass extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    int get_val;

    SQLiteDatabase database;

    ArrayList<String> t = new ArrayList<String>();
    ArrayList<String> c = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        get_val = getIntent().getExtras().getInt("item_position");

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
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), t, c);
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(get_val);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_pager_class, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            TextView tv1, tv2;
            View rootView = inflater.inflate(R.layout.fragment_view_pager_class, container, false);
            tv1 = (TextView) rootView.findViewById(R.id.pager_title);
            tv2 = (TextView) rootView.findViewById(R.id.pager_content);
            Bundle bundle = getArguments();
            String t = bundle.getString("get_value_title");
            String c = bundle.getString("get_value_content");
            tv1.setText(t);
            tv2.setText(c);
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        ArrayList<String> t, c;

        public SectionsPagerAdapter(FragmentManager fm, ArrayList<String> t, ArrayList<String> c) {
            super(fm);
            this.t = t;
            this.c = c;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new PlaceholderFragment();
            Bundle bundle = new Bundle();
            bundle.putString("get_value_title", t.get(position));
            bundle.putString("get_value_content", c.get(position));
            f.setArguments(bundle);
            return f;
        }

        @Override
        public int getCount() {
            return t.size();
        }

    }
}
