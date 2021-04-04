
        package com.example.furnished;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.squareup.picasso.Picasso;

        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.HashMap;

        import Models.Products;
        import Prevalent.Prevalent;

public class product_details extends AppCompatActivity {
    public ImageView product_img_details,back,cartBtn;
    public TextView product_name_details,product_desc_details,product_price_details;
    private Button addtoCart;
    private ImageButton ArBTN;
    private String Productid, imgUri,Product_Price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        product_img_details = findViewById(R.id.product_image_page);
        product_name_details = findViewById(R.id.product_name_page);
        product_desc_details = findViewById(R.id.product_description_page);
        product_price_details = findViewById(R.id.product_price_page);
        addtoCart = findViewById(R.id.AddToCart);
        cartBtn = findViewById(R.id.cart_btn);
        back = findViewById(R.id.backBtndetails);
        ArBTN = findViewById(R.id.ARButton);
        Productid = getIntent().getStringExtra("pid");



        ArBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(product_details.this,Camera.class);
                intent.putExtra("pid",Productid);
                startActivity(intent);
            }
        });
        cartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(product_details.this,cart_activity.class);
                startActivity(intent);
            }
        });

        addtoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToCart();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getProductDetails(Productid);

    }

    private void AddToCart() {

        String SavecurrentDate, SavecurrentTime;

        Calendar callforDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        SavecurrentDate = currentDate.format(callforDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        SavecurrentTime = currentTime.format(callforDate.getTime());



        final DatabaseReference CartRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("pid",Productid);
        cartMap.put("pname",product_name_details.getText().toString());
        cartMap.put("price",Product_Price);
        cartMap.put("date",SavecurrentDate);
        cartMap.put("time",SavecurrentTime);
        cartMap.put("img",imgUri);

        CartRef.child("User View").child(Prevalent.currentOnlineUser.getPhone()).child("Products")
                .child(Productid).updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            CartRef.child("Admin View").child(Prevalent.currentOnlineUser.getPhone()).child("Products")
                                    .child(Productid).updateChildren(cartMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(product_details.this,"Product added to Cart",Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                        }
                    }
                });




    }

    public void getProductDetails(String productid) {
        DatabaseReference Rootref = FirebaseDatabase.getInstance().getReference().child("Products");

        Rootref.child(Productid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    Products products = snapshot.getValue(Products.class);
                    product_name_details.setText(products.getPname());
                    product_price_details.setText(String.format("₹%s", products.getPrice()));
                    Product_Price = products.getPrice();
                    product_desc_details.setText(products.getDescription());
                    imgUri = products.getImage();
                    Picasso.get().load(products.getImage()).into(product_img_details);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
