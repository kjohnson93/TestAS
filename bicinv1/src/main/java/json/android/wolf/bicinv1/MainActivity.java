package json.android.wolf.bicinv1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

//maps

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    MyPagerAdapter mAdapter;
    TabLayout mTabLayout;
    ViewPager mPager;
    Button btest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdapter = new MyPagerAdapter(getSupportFragmentManager(),this);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);

        //NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navegation_drawer);
        //drawerFragment.setUp(R.layout.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setTabsFromPagerAdapter(mAdapter); //to extract the titles from the pager adapter

        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class MyFragment extends Fragment{
        private static final java.lang.String ARG_PAGE = "arg_page";

        public MyFragment(){


        }

        //pass the arguments to the fragment while constructing it aswel: static method. REASON why we not pass arguments to construcot its because they must have a empty constructor by default
        public static MyFragment newInstance(int pageNumber){
            /*
            MyFragment myFragment = new MyFragment();
            Bundle arguments = new Bundle();
            arguments.putInt(ARG_PAGE, pageNumber);
            myFragment.setArguments(arguments);
            return myFragment;*/

            MyFragment myFragment = new MyFragment();

            Bundle arguments = new Bundle();
            arguments.putInt(ARG_PAGE, pageNumber);
            myFragment.setArguments(arguments);
            return myFragment;


        }



        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            Bundle arguments = getArguments();
            int pageNumber = arguments.getInt(ARG_PAGE); //watch out when making a new line if its return its different than zero you need to store it in order to be displayed by android studio IDE.
            TextView myText = new TextView(getActivity());
            myText.setText("Hello im text inside this fragment " + pageNumber);
            myText.setGravity(Gravity.CENTER);
            return myText;
//GITHUB TEST as
        }
    }

    //CREATING MAP FRAGMENT

    public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        @Override
        public void onMapReady(GoogleMap map) {
            // Add a marker in Sydney, Australia, and move the camera.
            LatLng sydney = new LatLng(-34, 151);
            map.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }


    }
}




class MyPagerAdapter extends FragmentStatePagerAdapter{

    private Context context;
    final LatLng    BARCELONA = new LatLng(41.387128, 2.168565);

    public MyPagerAdapter(FragmentManager fm, Context context)
    {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        //MainActivity.MyFragment myFragment = new MainActivity.MyFragment();
        MainActivity.MyFragment myFragment;
        MyMapFragment myMapFragment;
        MyMapFragmentv2 myMapFragmentv2;


        switch (position){


            case 0:
                return myMapFragmentv2 = MyMapFragmentv2.newInstance();
            case 1:
                return MainActivity.MyFragment.newInstance(position);
            //case 0:
               // return myMapFragment= MyMapFragment.newInstance(BARCELONA);
           // case 1:
            //    return  myFragment= MainActivity.MyFragment.newInstance(position);


        }
        return  null;
    }

    @Override
    public int getCount() {

        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch(position){

            case 0:
                return "Map";
            case 1:
                return "Choose your route";
           // case 2:
             //   return "Choose your route";

        }
        return null;

    }
}
