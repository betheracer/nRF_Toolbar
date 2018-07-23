package com.example.devin.nrf_toolbar;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private UARTConfigurationsAdapter mConfigurationsAdapter;


    private boolean mEditMode;

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        button = (Button) findViewById(R.id.button);

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

        button.setActivated(mEditMode);

        final TransitionDrawable transition = (TransitionDrawable) getResources()
                .getDrawable(editmode ? R.drawable.start_edit_mode : R.drawable.stop_edit_mode);

        transition.setCrossFadeEnabled(true);
        getSupportActionBar().setBackgroundDrawable(transition);
        transition.startTransition(500);


        // Since Lollipop the status bar color may also be changed
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final int colorFrom = ContextCompat.getColor(this, editmode ? R.color.actionBarColorDark : R.color.dark_orange);
            final int colorTo = ContextCompat.getColor(this, !editmode ? R.color.actionBarColorDark : R.color.dark_orange);

            final ValueAnimator anim = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            anim.setDuration(200);
            anim.addUpdateListener(animation -> getWindow().setStatusBarColor((Integer) animation.getAnimatedValue()));
            anim.start();
        }

    }



}
