package json.android.wolf.bicinv1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by wolf on 10/26/2015.
 */
public class MyMapFragment extends SupportMapFragment
{
    private LatLng latLon;
    private LatLng latprueba; //test to git

    public MyMapFragment()
    {
        super();
    }

    public static MyMapFragment newInstance(LatLng position)
    {
        MyMapFragment frag = new MyMapFragment();
        frag.latLon = position;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = super.onCreateView(inflater, container, savedInstanceState);
        initMap();
        return v;
    }

    private void initMap()
    {
        UiSettings settings = getMap().getUiSettings();
        settings.setAllGesturesEnabled(false);
        settings.setMyLocationButtonEnabled(false);

        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(latLon, 16));
        getMap().addMarker(new MarkerOptions().position(latLon).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_next))); //should be ic_launcher
    }
}
