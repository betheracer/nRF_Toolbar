package com.example.devin.nrf_toolbar;

import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private UARTConfigurationsAdapter mConfigurationsAdapter;


    private boolean mEditMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.uart_menu_configurations, menu);
        getMenuInflater().inflate(mEditMode ? R.menu.uart_menu_config : R.menu.uart_menu, menu);

        getMenuInflater().inflate(R.menu.help, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.action_about:
                Toast.makeText(this, "어바웃", Toast.LENGTH_SHORT).show();
                break;

            case R.id.action_configure:
                setEditMode(!mEditMode);
                break;
        }


        return super.onOptionsItemSelected(item);
    }



    public void setEditMode(final boolean editMode) {
        setEditMode(editMode, true);
        invalidateOptionsMenu();
    }


    private void setEditMode(final boolean editmode, final boolean change) {

        mEditMode = editmode;

        final TransitionDrawable transition = (TransitionDrawable) getResources()
                .getDrawable(editmode ? R.drawable.start_edit_mode : R.drawable.stop_edit_mode);

        transition.setCrossFadeEnabled(true);
        getSupportActionBar().setBackgroundDrawable(transition);
        transition.startTransition(500);

    }



}
