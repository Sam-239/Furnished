package ViewHolder;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.furnished.R;

import Interface.ItemClickListener;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView Product_name, Product_description, Product_price;
    public ImageView Product_image;
    public ItemClickListener listener;

    public ProductViewHolder(@NonNull View itemView) {
        super(itemView);
        Product_image = itemView.findViewById(R.id.product_image);
        Product_name =  itemView.findViewById(R.id.product_name);
        Product_price = itemView.findViewById(R.id.product_price);

    }
    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        listener.onClick(view, getAdapterPosition(), false);

    }
}
