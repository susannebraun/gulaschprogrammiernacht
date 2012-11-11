package com.github.susannebraun.gulasch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class GulaschItemListActivity extends FragmentActivity
        implements GulaschItemListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gulaschitem_list);

        if (findViewById(R.id.gulaschitem_detail_container) != null) {
            mTwoPane = true;
            ((GulaschItemListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.gulaschitem_list))
                    .setActivateOnItemClick(true);
        }
    }

    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(GulaschItemDetailFragment.ARG_ITEM_ID, id);
            GulaschItemDetailFragment fragment = new GulaschItemDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.gulaschitem_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, GulaschItemDetailActivity.class);
            detailIntent.putExtra(GulaschItemDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
