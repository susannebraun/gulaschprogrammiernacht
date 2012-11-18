package com.github.susannebraun.gulasch.park.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.github.susannebraun.gulasch.R;

public class DummyMyParkingLots {

	private static List<MyParkingLot> LOTS = new ArrayList<MyParkingLot>();
    private static Map<Long, MyParkingLot> LOTS_BY_ID = new HashMap<Long, MyParkingLot>();

    static {
    	addLot(1l, "Parkplatz 1", "Kiefernstr.", "1f", "55218", "Ingelheim", true);
    	addLot(2l, "Parkplatz 2", "Kiefernstr.", "1f", "55218", "Ingelheim", true);
    	addLot(3l, "Parkplatz 3", "Kiefernstr.", "1f", "55218", "Ingelheim", false);
    }

    private static void addLot(long id, String name, String street, String streetNumber,
			String zip, String city, boolean parked) {
    	MyParkingLot lot = new MyParkingLot(id, name, street, streetNumber, zip, city);
    	lot.parked = parked;
    	
        LOTS.add(lot);
        LOTS_BY_ID.put(lot.id, lot);
    }
    
    public static List<Map<String, Object>> getListDummyData(Context ctx) {
    	List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
    	Drawable parked = ctx.getResources().getDrawable(R.drawable.red_button);
    	Drawable free = ctx.getResources().getDrawable(R.drawable.green_button);
    	
    	for(MyParkingLot lot: LOTS) {
    		Map<String, Object> keyValueRep = new HashMap<String, Object>();
    		keyValueRep.put("id", lot.id);
    		keyValueRep.put("name", lot.name);
    		keyValueRep.put("adress", lot.getAdress());
    		if(lot.parked) {
    			keyValueRep.put("parked", R.drawable.red_button);
    		} else {
    			keyValueRep.put("parked", R.drawable.green_button);
    		}
   
    		data.add(keyValueRep);
    	}
    	
    	return data;
    }

    
    public static MyParkingLot getMyParkingLotById(Long id) {
    	if(id == null) {
    		return null;
    	}
    	
    	return LOTS_BY_ID.get(id);
    }
}
