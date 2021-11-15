package com.example.dtt_test.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.dtt_test.DetailsActivity;
import com.bumptech.glide.Glide;
import com.example.dtt_test.Details;
import com.example.dtt_test.Entity.Data;

import com.example.dtt_test.Home;
import com.example.dtt_test.R;
import com.example.dtt_test.Retrofit.INodeJS;
import com.example.dtt_test.Retrofit.RetrofitClient;



import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.mapbox.mapboxsdk.Mapbox.getApplicationContext;
import static java.lang.Math.acos;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class HomeAdapter extends RecyclerView.Adapter<com.example.dtt_test.Adapter.HomeAdapter.myViewHolder> {

    Context mContext;
    private List<Data> mData;
    private List<Data> data_list = new ArrayList<>();



    SharedPreferences sharedPreferences;



    TextView bedrooms, bathrooms, latitude, price, sizee,zip,city;
    ImageView houseimg;

    public static INodeJS iNodeJS;
    int mylatitude;
    int mylongitude;





    public HomeAdapter(Context mContext, List<Data> mDataa) {
        this.mContext = mContext;
        this.mData = mDataa;
    }

    @NonNull
    @Override
    public com.example.dtt_test.Adapter.HomeAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.card_item, parent, false);
        com.example.dtt_test.Adapter.HomeAdapter.myViewHolder vv = new com.example.dtt_test.Adapter.HomeAdapter.myViewHolder(v);
        SharedPreferences sh = parent.getContext().getSharedPreferences("myposition",MODE_PRIVATE );
         mylatitude = sh.getInt("mylatitude", 0);
          mylongitude = sh.getInt("mylongitude", 0);

        return vv;
    }

    @Override
    public void onBindViewHolder(@NonNull com.example.dtt_test.Adapter.HomeAdapter.myViewHolder holder, int position) {
        Data houses = mData.get(position);
            int distance = (int) getKilometers(houses.getLatitude(),houses.getLongitude(),mylatitude,mylongitude);
            bedrooms.setText(Integer.toString(houses.getBedrooms()));
            bathrooms.setText(Integer.toString(houses.getBathrooms()));
            price.setText("$ "+Integer.toString(houses.getPrice()));
            city.setText(houses.getCity());
            zip.setText(houses.getZip());
            sizee.setText(Integer.toString(houses.getSize()));
            latitude.setText(distance +" KM");
            System.out.println("there is Data");
        int idim=houses.getId()-1;
        String imgurl ="https://intern.docker-dev.d-tt.nl/uploads/house"+idim+".jpg";
        Glide.with(holder.itemView).load(imgurl).centerCrop().placeholder(R.drawable.ic_launcher_foreground)
                .into(houseimg);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void filterList(ArrayList<Data> filteredList){
        data_list = filteredList;
        notifyDataSetChanged();
    }
    public double getKilometers(int lat1, int long1, int lat2, int long2) {
        double PI_RAD = Math.PI / 180.0;
        double phi1 = lat1 * PI_RAD;
        double phi2 = lat2 * PI_RAD;
        double lam1 = long1 * PI_RAD;
        double lam2 = long2 * PI_RAD;

        return 6371.01 * acos(sin(phi1) * sin(phi2) + cos(phi1) * cos(phi2) * cos(lam2 - lam1));}


    public class myViewHolder extends RecyclerView.ViewHolder {
        public myViewHolder(@NonNull final View itemView) {
            super(itemView);
            iNodeJS = RetrofitClient.getInstance().create(INodeJS.class);
            bathrooms = itemView.findViewById(R.id.bathrooms);
            bedrooms = itemView.findViewById(R.id.bedrooms);
            price= itemView.findViewById(R.id.price);
            city= itemView.findViewById(R.id.city);
            zip= itemView.findViewById(R.id.zip);
            sizee= itemView.findViewById(R.id.size);
            latitude= itemView.findViewById(R.id.latitude);
            houseimg = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition(); // gets item position
                    if (position != RecyclerView.NO_POSITION) {
                        sharedPreferences = v.getContext().getSharedPreferences("House", MODE_PRIVATE);
                        Data ce = mData.get(position);
                        int detaildist = (int) getKilometers(ce.getLatitude(),ce.getLongitude(),mylatitude,mylongitude);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("bathrooms", ce.getBathrooms());
                        editor.putInt("id", ce.getId());
                        editor.putInt("price", ce.getPrice());
                        editor.putInt("bedrooms", ce.getBedrooms());
                        editor.putInt("bedrooms", ce.getBedrooms());
                        editor.putString("description", ce.getDescription());
                        editor.putInt("size", ce.getSize());
                        editor.putInt("latitude", ce.getLatitude());
                        editor.putInt("longitude", ce.getLongitude());
                        editor.putInt("dist", detaildist);

                        editor.apply();
                    }
                    itemView.getContext().startActivity(new Intent(itemView.getContext(), Details.class));
                }
            });


        }
    }

}