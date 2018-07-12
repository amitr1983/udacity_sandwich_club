package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        final String SANDWICH_NAME = "name";
        final String SANDWICH_MAIN_NAME = "mainName";
        final String SANDWICH_ALSO_KNOWN_AS = "alsoKnownAs";
        final String SANDWICH_PLACE_OF_ORIGIN = "placeOfOrigin";
        final String SANDWICH_DESCRIPTION = "description";
        final String SANDWICH_IMAGE = "image";
        final String SANDWICH_INGREDIENTS = "ingredients";

        Sandwich sandWich = null;

        try {
            JSONObject sandwichDetailsJSON = new JSONObject(json);
            JSONObject sandwichNameObject = sandwichDetailsJSON.optJSONObject(SANDWICH_NAME);
            String sandwichMainName = sandwichNameObject.getString(SANDWICH_MAIN_NAME);
            JSONArray sandwichKnownAs = sandwichNameObject.getJSONArray(SANDWICH_ALSO_KNOWN_AS);
            List<String> listOfSandwichKnownAs = new ArrayList<>();

            for (int i = 0; i < sandwichKnownAs.length(); i++) {
                listOfSandwichKnownAs.add(sandwichKnownAs.getString(i));
            }

            String sandwichPlaceOfOrigin = sandwichDetailsJSON.getString(SANDWICH_PLACE_OF_ORIGIN);
            String sandwichDescription = sandwichDetailsJSON.getString(SANDWICH_DESCRIPTION);
            String sandwichImage = sandwichDetailsJSON.getString(SANDWICH_IMAGE);
            JSONArray sandwichIngredients = sandwichDetailsJSON.getJSONArray(SANDWICH_INGREDIENTS);
            List<String> listOfIngredients = new ArrayList<>();

            for (int i = 0; i < sandwichIngredients.length(); i++) {
                listOfIngredients.add(sandwichIngredients.getString(i));
            }

            // Debugging : Print values of strings
            Log.v("TAG", sandwichMainName);
            Log.v("TAG", sandwichImage);
            Log.v("TAG", sandwichPlaceOfOrigin);
            Log.v("TAG", sandwichDescription);
            Log.v("TAG", listOfIngredients.toString());
            Log.v("TAG", listOfSandwichKnownAs.toString());
            return new Sandwich(sandwichMainName, listOfSandwichKnownAs, sandwichPlaceOfOrigin, sandwichDescription, sandwichImage, listOfIngredients);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return sandWich;
    }
}
