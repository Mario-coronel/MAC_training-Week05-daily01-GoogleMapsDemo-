package com.example.admin.googlemaps;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.JointType;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.g_map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMinZoomPreference(5);

        LatLng issaquah = new LatLng(47.5301011, -122.0326191);
        LatLng seattle = new LatLng(47.6062095, -122.3320708);
        LatLng bellevue = new LatLng(47.6101497, -122.2015159);
        LatLng sammamish = new LatLng(47.6162683, -122.0355736);
        LatLng redmond = new LatLng(47.6739881, -122.121512);


//Circle
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(seattle);
        circleOptions.radius(8500);
        circleOptions.fillColor(Color.TRANSPARENT);
        circleOptions.strokeColor(Color.RED);
        circleOptions.strokeWidth(4);
        mMap.addCircle(circleOptions);


        //Line
        PolylineOptions plo = new PolylineOptions();
        plo.add(seattle);
        plo.add(bellevue);
        plo.color(Color.RED);
        plo.geodesic(true);
        plo.startCap(new RoundCap());
        plo.width(20);
        plo.jointType(JointType.BEVEL);
        mMap.addPolyline(plo);

        //Poligon
        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(issaquah, seattle, bellevue, sammamish, redmond);
        polygonOptions.strokeJointType(JointType.ROUND);
        polygonOptions.strokeColor(Color.YELLOW);
        polygonOptions.strokeWidth(10);
        mMap.addPolygon(polygonOptions);

        /*/Custom marker
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(47.6101497, -122.2015159))
                .title("Bellevue")
                .icon( BitmapDescriptorFactory.fromResource(R.drawable.tasking))
                .rotation(20)
                .draggable(false);
        mMap.addMarker(markerOptions);  */

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(bellevue)
                .title("Bellevue")
                .icon(BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_GREEN));

        Marker m = mMap.addMarker(markerOptions);
        m.showInfoWindow();

        //Map types
        GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions ();
        groundOverlayOptions.position(bellevue, 100, 100 )
                .image( BitmapDescriptorFactory.fromResource(R.drawable.tasking));

        mMap.addGroundOverlay(groundOverlayOptions);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);



        mMap.addMarker(new MarkerOptions().position(seattle).title("Seattle"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(seattle));
    }
}
