package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;
import java.util.List;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import android.util.Log;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //Populate values of paresed data into UI elements
        TextView foodDescription, foodIngredients, foodKnownAs, foodOrigin;
        foodDescription = findViewById(R.id.description_tv);
        foodIngredients = findViewById(R.id.ingredients_tv);
        foodKnownAs = findViewById(R.id.also_known_tv);
        foodOrigin = findViewById(R.id.origin_tv);
        foodDescription.setText(sandwich.getDescription());
        foodIngredients.setText(listToString(sandwich.getIngredients()));
        foodKnownAs.setText(listToString(sandwich.getAlsoKnownAs()));
        foodOrigin.setText(replaceEmptyString(sandwich.getPlaceOfOrigin()));
    }

    // Convert list into comma seperated String
    private String listToString(List<String> list) {

        String string = "";
        for(int i = 0; i < list.size(); i++) {
            string += list.get(i);
            if (i < list.size() - 1) string += ", ";
        }
        return string;
    }

    private String replaceEmptyString(String str) {

        if (str == null || str.length() == 0 ) return "No Information";
        return str;
    }
}
