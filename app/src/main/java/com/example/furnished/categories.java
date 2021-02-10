package com.example.furnished;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class categories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        RelativeLayout Sofacat = findViewById(R.id.sofacat);
        RelativeLayout Bedcat = findViewById(R.id.bed_category);
        RelativeLayout Chaircat = findViewById(R.id.chair_category);
        RelativeLayout Tablecat = findViewById(R.id.category_tableset);
        RelativeLayout Deskcat = findViewById(R.id.category_desk);
        RelativeLayout Wardrobecat = findViewById(R.id.category_wardrobe);
        RelativeLayout CoffeeTablecat = findViewById(R.id.category_coffeetable);
        ImageView back = findViewById(R.id.backCat);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        CoffeeTablecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCoffeeTable();
            }
        });

        Chaircat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChair();
            }
        });

        Sofacat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opensofa();
            }
        });
        Bedcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBed();
            }
        });
        Tablecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTableset();
            }
        });

        Wardrobecat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWardrobe();
            }
        });
        Deskcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDesk();
            }
        });

    }

    private void openCoffeeTable() {
        Intent intent = new Intent(this,browsepcoffeetable.class);
        startActivity(intent);
    }

    private void openDesk() {
        Intent intent = new Intent(this,browsepdesk.class);
        startActivity(intent);
    }

    private void openWardrobe() {
        Intent intent = new Intent(this,browsepwardrobe.class);
        startActivity(intent);
    }

    private void openTableset() {
        Intent intent = new Intent(this,browseptableset.class);
        startActivity(intent);
    }

    private void opensofa(){
    Intent intent = new Intent(this,browsepsofa.class);
    startActivity(intent);
}
    private void openChair(){
        Intent intent = new Intent(this,browsepchair.class);
        startActivity(intent);
    }
    private void openBed(){
        Intent intent = new Intent(this,browsepbed.class);
        startActivity(intent);
    }




}

