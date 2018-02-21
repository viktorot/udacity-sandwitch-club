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

    private static String PROP_NAME = "name";
    private static String PROP_MAIN_NAME = "mainName";
    private static String PROP_AKA = "alsoKnownAs";
    private static String PROP_ORIGIN = "placeOfOrigin";
    private static String PROP_DESC = "description";
    private static String PROP_IMAGE = "image";
    private static String PROP_ING = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject object = new JSONObject(json);
            JSONObject nameObj = object.getJSONObject(PROP_NAME);

            String name = nameObj.optString(PROP_MAIN_NAME);
            List<String> altNames = getList(nameObj.optJSONArray(PROP_AKA));
            String birth = object.optString(PROP_ORIGIN);
            String desc = object.optString(PROP_DESC);
            String img = object.optString(PROP_IMAGE);
            List<String> ingredients = getList(object.optJSONArray(PROP_ING));
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
