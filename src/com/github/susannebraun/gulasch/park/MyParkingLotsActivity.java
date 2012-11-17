package com.github.susannebraun.gulasch.park;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.github.susannebraun.gulasch.R;
import com.github.susannebraun.gulasch.park.data.DummyMyParkingLots;

public class MyParkingLotsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_my_parking_lots);
        
        ListView myParkingLotsListView = (ListView) findViewById(R.id.list_my_parking_lots);
        if(myParkingLotsListView != null) {
        	
        	final List<Map<String, Object>> data = DummyMyParkingLots.getListDummyData();
        	int[] to = new int[] {R.id.two_line_list_item_text1, R.id.two_line_list_item_text2};
        	String[] from = new String[] {"name", "adress"};
        	myParkingLotsListView.setAdapter(new SimpleAdapter(this, data, R.layout.two_line_list_item, from, to));
        	
        	myParkingLotsListView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,
	                    long id) {
					
					if(position >= 0 && position < data.size()) {
						Map<String, Object> lot = data.get(position);
						Long lotId = (Long) lot.get("id");
						if(lotId != null) {
							Intent detailIntent = new Intent(MyParkingLotsActivity.this, MyParkingLotActivity.class);
			              	detailIntent.putExtra(MyParkingLotActivity.ARG_LOT_ID, lotId);
							startActivity(detailIntent);
						}
					}
				}
        		
        		
			});
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_my_parking_lots, menu);
        return true;
    }
}
