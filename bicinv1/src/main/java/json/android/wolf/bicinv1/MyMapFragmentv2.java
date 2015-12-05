package json.android.wolf.bicinv1;

import android.app.admin.SystemUpdatePolicy;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by wolf on 10/28/2015.
 */
public class MyMapFragmentv2 extends SupportMapFragment {



   private static final String TAG = "MainTagName";           //TAG para debuggear
    LatLng myCoordinates = new LatLng(41.387128, 2.168565);   //coordenadas de prueba
    final LatLng WTC = new LatLng(41.372203, 2.180496);       //Coordenadas de la estación bicing de mi trabajo

    GoogleMap googleMap;                                       //objeto de tipo googleMap que es el mapa que voy a mostrar

    SupportMapFragment mSupportMapFragment;                     //Fragmento que envolvera al googleMap
    CameraUpdate yourLocation;                                  //Para colocar el area de visión

    //El constructor normal de un objeto que extiende SupportMapFragment no deja crear un objeto es porque no deja pasarle argumentos mientras se construye porque deben tener un constructor vacio por defecto
    //pass the arguments to the fragment while constructing it aswel: static method. REASON why we not pass arguments to construcot its because they must have a empty constructor by default
    public static MyMapFragmentv2 newInstance()                 //Con un constructor metodo static si que se puede pasar argumentos
    {
        MyMapFragmentv2 frag = new MyMapFragmentv2();


        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {       //Metodo que forma parte del lifecycle de un fragmento. Aqui se da forma al fragmento.
        super.onCreateView(inflater, container, savedInstanceState);

        View root = inflater.inflate(R.layout.activity_maps_2, container, false); //Se debe retornar un objeto view

        initMap();  //Esta función realiza todo sobre el mapa dentro de la vista.

        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {   //De momento no hacemos nada.
        super.onActivityCreated(savedInstanceState);
    }


    public void initMap() {

        //ESTE Codigo me daba nullpointer:  Posiblemente porque se llamaba antes de fragmentTransaction.commit()??
/*
        mSupportMapFragment = (SupportMapFragment) getFragmentManager().findFragmentById(R.id.map);
        if (mSupportMapFragment == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            mSupportMapFragment = SupportMapFragment.newInstance(); //?????
            //aqui hacer cosas con el fragmento supongo..?
            if (mSupportMapFragment != null) {
                googleMap = mSupportMapFragment.getMap();
                Log.d(TAG,"Hello from mSupportMapFragment null");
            }
            int test = googleMap.getMapType();
            Log.d(TAG,"INT test tag:" + test);
*/
          //  googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLon, 16));

        FragmentManager fManager= getChildFragmentManager();        //Se necesita un FragmentManager que gestione los fragmentos
        mSupportMapFragment = (SupportMapFragment) fManager         //Le decimos que este tipo de vista le pertenece
                .findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {                 //Forma de llamar al mapa en la nueva API
                if (map == null) {

                } else {                                            //Si tengo el mapa ya instanciado , procedo a llamar metodos para jugar con el mapa
                    googleMap = map;
                    googleMap.getUiSettings().setCompassEnabled(true);
                    googleMap.getUiSettings().setZoomControlsEnabled(true);
                    googleMap.getUiSettings().setMyLocationButtonEnabled(false);
                    googleMap.getUiSettings().setRotateGesturesEnabled(true);
                    yourLocation = CameraUpdateFactory.newLatLngZoom(myCoordinates, 12);
                    //googleMap.moveCamera(CameraUpdateFactory.newLatLng(myCoordinates));
                    googleMap.animateCamera(yourLocation);                  //Localiza el area de visión
                    googleMap.addMarker(new MarkerOptions().position(WTC).title("WTC Station 1"));
                }
            }
        });
        FragmentTransaction fragmentTransaction = fManager.beginTransaction();

            fragmentTransaction.commit();       //El que permite que se muestre el fragmento


        }


    }

