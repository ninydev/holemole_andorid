package com.itstep.holemole.novaPoshta;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class AreasHelpers {

    public static ArrayList<Areas> areasJsonToArray(JSONArray array) throws JSONException {
        ArrayList<Areas> res= new ArrayList<>();
        for (int i=0; i < array.length(); i++) {

            res.add( new Areas(
                    array.getJSONObject(i).getString("Ref") ,
                    array.getJSONObject(i).getString("AreasCenter") ,
                    array.getJSONObject(i).getString("DescriptionRu") ,
                    array.getJSONObject(i).getString("Description")
            ));
        }
        return  res;
    }

}
