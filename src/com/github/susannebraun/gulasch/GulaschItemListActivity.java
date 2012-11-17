package com.github.susannebraun.gulasch;

import com.github.susannebraun.gulasch.qr.QRScanDetailActivity;
import com.github.susannebraun.gulasch.qr.QRScanDetailFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

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
    	if("1".equals(id)) {
    		// LED
    		if (mTwoPane) {
//                Bundle arguments = new Bundle();
//                arguments.putString(LEDDetailFragment.ARG_ITEM_ID, id);
                
                LEDDetailFragment fragment = new LEDDetailFragment();
//                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.gulaschitem_detail_container, fragment)
                        .commit();

            } else {
                Intent detailIntent = new Intent(this, LEDDetailActivity.class);
//                detailIntent.putExtra(LEDDetailFragment.ARG_ITEM_ID, id);
                startActivity(detailIntent);
            }
    	} else if("2".equals(id)) {
    		if (mTwoPane) {
//              Bundle arguments = new Bundle();
//              arguments.putString(LEDDetailFragment.ARG_ITEM_ID, id);
              
    			QRScanDetailFragment fragment = new QRScanDetailFragment();
//              fragment.setArguments(arguments);
              getSupportFragmentManager().beginTransaction()
                      .replace(R.id.gulaschitem_detail_container, fragment)
                      .commit();

          } else {
              Intent detailIntent = new Intent(this, QRScanDetailActivity.class);
//              detailIntent.putExtra(LEDDetailFragment.ARG_ITEM_ID, id);
              startActivity(detailIntent);
          }
    	}
        
    }
    
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);

		if (scanResult != null) {
			String contents = scanResult.getContents();
			Fragment fragment = getSupportFragmentManager().findFragmentById(
					R.id.gulaschitem_detail_container);
			if (fragment != null && fragment instanceof QRScanDetailFragment) {

				((QRScanDetailFragment) fragment).displayScanResult(contents);

			}
		}
	}
}
