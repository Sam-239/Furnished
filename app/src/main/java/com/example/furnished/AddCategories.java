package com.example.furnished;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class AddCategories extends AppCompatActivity {
    private RelativeLayout ADDbed,ADDTableset,ADDChairs,ADDSofa,ADDDesk,ADDWardrobe,ADDCoffeetable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_categories);

        ADDbed = findViewById(R.id.AddBed);
        ADDTableset = findViewById(R.id.AddTableSet);
        ADDChairs = findViewById(R.id.AddChair);
        ADDSofa = findViewById(R.id.AddSofaset);
        ADDDesk = findViewById(R.id.AddDesk);
        ADDWardrobe = findViewById(R.id.AddWardrobe);
        ADDCoffeetable = findViewById(R.id.AddCoffeeTable);
        ADDbed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategories.this,AdminAddNewProduct.class);
                intent.putExtra("categories","addBed");
                startActivity(intent);
            }
        });

        ADDTableset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategories.this,AdminAddNewProduct.class);
                intent.putExtra("categories","addTableSet");
                startActivity(intent);
            }
        });

        ADDChairs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategories.this,AdminAddNewProduct.class);
                intent.putExtra("categories","addChair");
                startActivity(intent);
            }
        });
        ADDSofa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategories.this,AdminAddNewProduct.class);
                intent.putExtra("categories","addSofa");
                startActivity(intent);
            }
        });
        ADDDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategories.this,AdminAddNewProduct.class);
                intent.putExtra("categories","addDesk");
                startActivity(intent);
            }
        });
        ADDWardrobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategories.this,AdminAddNewProduct.class);
                intent.putExtra("categories","addWardrobe");
                startActivity(intent);
            }
        });
        ADDCoffeetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddCategories.this,AdminAddNewProduct.class);
                intent.putExtra("categories","addCoffeetable");
                startActivity(intent);
            }
        });
    }
}





