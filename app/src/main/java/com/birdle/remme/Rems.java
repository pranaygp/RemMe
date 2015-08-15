package com.birdle.remme;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class Rems extends ListActivity {

    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rems);


        String[] fromColumns = {remsContract.remSchema.COLUMN_NAME_REM_DATA};
        int[] toViews = {android.R.id.text1};
        Log.d("RemMe", Rem.init("0000", this).saveToDB().getData() );
        Log.d("RemMe", String.valueOf(Rem.all(this).getCount()));

        mAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, Rem.all(this), fromColumns, toViews, 0);
        setListAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rems, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
