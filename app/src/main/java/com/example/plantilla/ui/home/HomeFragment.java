package com.example.plantilla.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.plantilla.R;
import com.example.plantilla.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment {
    private static final LatLng inmobiliaria= new LatLng(-33.271259,-66.297822);
    private MapView mapaInmo;
    private GoogleMap googleMap;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        mapaInmo=root.findViewById(R.id.map);
        mapaInmo.onCreate(savedInstanceState);
        mapaInmo.onResume();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }
        mapaInmo.getMapAsync(new MapaActual());
        return root;
    }
    public class MapaActual implements OnMapReadyCallback{
        @Override
        public void onMapReady(GoogleMap mapaG) {
            googleMap=mapaG;
            //hace foco en un determinado punto
            CameraPosition camPos= new CameraPosition.Builder()
                    .target(inmobiliaria)
                    .zoom(19)
                    .bearing(45)
                    .tilt(70)
                    .build();
            CameraUpdate camUpd= CameraUpdateFactory.newCameraPosition(camPos);
            mapaG.animateCamera(camUpd);
            mapaG.addMarker(new MarkerOptions().position(inmobiliaria).title("Inmobiliaria De Angelis"));
        }
    }
}