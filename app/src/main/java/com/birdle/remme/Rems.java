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
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Rems extends ListActivity {

    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rems);


        String[] fromColumns = {remsContract.remSchema._ID, remsContract.remSchema.COLUMN_NAME_REM_DATA};
        int[] toViews = {R.id.idTextView, R.id.dataTextView};
        Log.d("RemMe", Rem.init("00001011", this).saveToDB().getData() );
        Log.d("RemMe", String.valueOf(Rem.all(this).getCount()));

        mAdapter = new SimpleCursorAdapter(this, R.layout.rems_list_item, Rem.all(this), fromColumns, toViews, 0);
        setListAdapter(mAdapter);

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Rem.find(id, this).delete();
        mAdapter.swapCursor(Rem.all(this));
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
