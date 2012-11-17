package com.github.susannebraun.gulasch.qr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.github.susannebraun.gulasch.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScanDetailActivity extends FragmentActivity {
	
	private QRScanDetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan_detail);

        if (savedInstanceState == null) {
        	fragment = new QRScanDetailFragment();
            
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.qr_scan_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		
		if (scanResult != null) {
			String contents = scanResult.getContents();
			if(fragment != null) {
				fragment.displayScanResult(contents);
			}
		}
	}

	
}
