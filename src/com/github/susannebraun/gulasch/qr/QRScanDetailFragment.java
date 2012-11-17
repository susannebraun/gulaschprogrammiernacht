package com.github.susannebraun.gulasch.qr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.susannebraun.gulasch.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScanDetailFragment extends Fragment {

	
    private TextView scanResultView;
	private TextView scanResultLabel;

	public QRScanDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_qr_scan_detail, container, false);
        
        Button scanButton = (Button) rootView.findViewById(R.id.qr_scan_button);
        scanButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	IntentIntegrator integrator = new IntentIntegrator(getActivity());
            	integrator.initiateScan();
            }
        });
        
        scanResultLabel = (TextView) rootView.findViewById(R.id.label_qr_scan_result);
        scanResultLabel.setVisibility(View.INVISIBLE);
        
        scanResultView = (TextView) rootView.findViewById(R.id.text_qr_scan_result);
        scanResultView.setVisibility(View.INVISIBLE);
        
        
        return rootView;
    }

    public void displayScanResult(String scanContents) {
    	if(scanResultLabel != null) {
    		scanResultLabel.setVisibility(View.VISIBLE);
    	}
    	if(scanResultView != null) {
    		scanResultView.setText(scanContents);
    		scanResultView.setVisibility(View.VISIBLE);
    	}
    	
    }
	
}
