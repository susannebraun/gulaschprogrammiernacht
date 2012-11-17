package com.github.susannebraun.gulasch;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

public class LEDDetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_detail);

//        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
//            Bundle arguments = new Bundle();
//            arguments.putString(LEDDetailFragment.ARG_ITEM_ID,
//                    getIntent().getStringExtra(LEDDetailFragment.ARG_ITEM_ID));
            LEDDetailFragment fragment = new LEDDetailFragment();
//            fragment.setArguments(arguments);
            
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.gulaschitem_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            NavUtils.navigateUpTo(this, new Intent(this, GulaschItemListActivity.class));
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }
}
