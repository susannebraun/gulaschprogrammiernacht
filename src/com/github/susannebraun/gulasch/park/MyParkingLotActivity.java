package com.github.susannebraun.gulasch.park;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.github.susannebraun.gulasch.R;
import com.github.susannebraun.gulasch.park.data.DummyMyParkingLots;
import com.github.susannebraun.gulasch.park.data.MyParkingLot;

public class MyParkingLotActivity extends Activity {
	
	public static final String ARG_LOT_ID = "lot_id";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_my_parking_lot);
        
        Long id = 0l;
        String name = "";
        String adress = "";
        
        // TODO: SB do i need this???
//        if (savedInstanceState == null) {
//        	Bundle arguments = new Bundle();
//        	arguments.putString(ARG_LOT_ID,
//                  getIntent().getStringExtra(ARG_LOT_ID));
//        }
        
        if(getIntent() != null) {
        	id = getIntent().getLongExtra(ARG_LOT_ID, 0l);
        	
        	MyParkingLot lot = DummyMyParkingLots.getMyParkingLotById(id);
        	if(lot != null) {
        		if(lot.name != null) {
        			name = lot.name;
        		}
        		
        		adress = lot.getAdress();
        	}
        }
        
        ((TextView) findViewById(R.id.text_my_parking_lot_name)).setText(name);
        ((TextView) findViewById(R.id.text_my_parking_lot_adress)).setText(adress);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_parking_lot, menu);
        return true;
    }
}
