package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonUtils {

    public static String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject nameObj = object.getJSONObject("name");

            String name = nameObj.getString("mainName");
            List<String> altNames = getList(nameObj.getJSONArray("alsoKnownAs"));
            String birth = object.getString("placeOfOrigin");
            String desc = object.getString("description");
            String img = object.getString("image");
            List<String> ingredients = getList(object.getJSONArray("ingredients"));
            return new Sandwich(name, altNames, birth, desc, img, ingredients);
        } catch (JSONException e) {
            Log.e(TAG, "error parsing sandwich JSON", e);
            return null;
        }
    }

    @NonNull
    private static List<String> getList(JSONArray jsonArray) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            result.add(jsonArray.optString(i));
        }
        return result;
    }
}
