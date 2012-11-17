package com.github.susannebraun.gulasch.park.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.susannebraun.gulasch.dummy.DummyContent.DummyItem;

public class DummyMyParkingLots {

	private static List<MyParkingLot> LOTS = new ArrayList<MyParkingLot>();
    private static Map<Long, MyParkingLot> LOTS_BY_ID = new HashMap<Long, MyParkingLot>();

    static {
    	addLot(1l, "Parkplatz 1", "Kiefernstr.", "1f", "55218", "Ingelheim");
    	addLot(2l, "Parkplatz 2", "Kiefernstr.", "1f", "55218", "Ingelheim");
    	addLot(3l, "Parkplatz 3", "Kiefernstr.", "1f", "55218", "Ingelheim");
    }

    private static void addLot(long id, String name, String street, String streetNumber,
			String zip, String city) {
    	MyParkingLot lot = new MyParkingLot(id, name, street, streetNumber, zip, city);
    	
        LOTS.add(lot);
        LOTS_BY_ID.put(lot.id, lot);
    }
    
    public static List<Map<String, Object>> getListDummyData() {
    	List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
    	
    	for(MyParkingLot lot: LOTS) {
    		Map<String, Object> keyValueRep = new HashMap<String, Object>();
    		keyValueRep.put("id", lot.id);
    		keyValueRep.put("name", lot.name);
    		keyValueRep.put("adress", lot.getAdress());
   
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
