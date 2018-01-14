package kola.com.killtb;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.List;


public class maps extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    LocationManager lm;double lat,lng,alt;double distance=-2.0;
    LocationListener ll;float post; SensorManager sensorManager;Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
       lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //  ll = new MyLocationListener();
      if(lm.isProviderEnabled(lm.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // TextView tx = (TextView) findViewById(R.id.lat);
                    // tx.setText("alok");//req updwtes and main activity done here
                    // Toast.makeText(getBaseContext(), (int) (location.getLongitude() + location.getLatitude()), Toast.LENGTH_SHORT).show();

                    lat = location.getLatitude();
                    lng = location.getLongitude();
                    post=location.getSpeed();
                    alt = location.getAltitude();//get alttude
                    TextView t=(TextView)findViewById(R.id.lat);//latitude
                    TextView te=(TextView)findViewById(R.id.longi);//longitude
                    TextView tex=(TextView)findViewById(R.id.distance);//altitude
                    TextView text=(TextView)findViewById(R.id.speed);//string
                    TextView textt=(TextView)findViewById(R.id.tdist);//distance
                    t.setText("LATITUDE:"+lat);
                    te.setText("LONGITUDE"+lng);
                    tex.setText("ALTITUDE:"+alt);
                    //  distance+=2;
                    textt.setText("Speed :"+post+"m/s");
                    // location.reset();







                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> lst = geocoder.getFromLocation(lat, lng, 1);
                        String s;
                        s = lst.get(0).getSubLocality()+",";
                        s += lst.get(0).getLocality() + ",";

                        s += lst.get(0).getCountryName() + ",";
                        s+=lst.get(0).getPostalCode();
                        text.setText(s);

                        LatLng sydney = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(sydney).title(s));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        }
        else if (lm.isProviderEnabled(lm.NETWORK_PROVIDER))
        {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    // TextView tx = (TextView) findViewById(R.id.lat);
                    // tx.setText("alok");//req updwtes and main activity done here
                    // Toast.makeText(getBaseContext(), (int) (location.getLongitude() + location.getLatitude()), Toast.LENGTH_SHORT).show();
                    lat = location.getLatitude();
                    lng = location.getLongitude();post=location.getSpeed();
                    alt = location.getAltitude();//get alttude
                    TextView t=(TextView)findViewById(R.id.lat);
                    TextView te=(TextView)findViewById(R.id.longi);
                    TextView tex=(TextView)findViewById(R.id.distance);
                    TextView text=(TextView)findViewById(R.id.speed);
                    t.setText("LATITUDE:"+lat);
                    te.setText("LONGITUDE"+lng);
                    tex.setText("ALTITUDE:"+alt);text.setText("SPEED "+post);


                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> lst = geocoder.getFromLocation(lat, lng, 1);
                        String s;
                        s = lst.get(0).getSubLocality();
                        s += lst.get(0).getLocality() + ",";

                        s += lst.get(0).getCountryName() + ",";
                        s+=lst.get(0).getPostalCode();

                        LatLng sydney = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(sydney).title(s));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });

        }







        mapFragment.getMapAsync(this);
      /*  sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener((SensorEventListener) this,sensor,SensorManager.SENSOR_DELAY_NORMAL);*/



    }




    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(lat,lng);
         mMap.addMarker(new MarkerOptions().position(sydney).title("sydney"));
           mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    /*@Override
     protected void onResume() {
         super.onResume();
         if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
             // TODO: Consider calling
             //    ActivityCompat#requestPermissions
             // here to request the missing permissions, and then overriding
             //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
             //                                          int[] grantResults)
             // to handle the case where the user grants the permission. See the documentation
             // for ActivityCompat#requestPermissions for more details.
             return;
         }
         lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000, 0, ll);
     }
 */
    @Override
    protected void onPause() {
        super.onPause();
        lm.removeUpdates(ll);
        // sensorManager.unregisterListener(this);
    }}
   /* private class MyLocationListener implements LocationListener
    {

        @Override
        public void onLocationChanged(Location location) {
            if(location!=null)
            {
                TextView tx=(TextView)findViewById(R.id.lat);
                tx.setText("alok");
                Toast.makeText(getBaseContext(), (int) (location.getLongitude()+location.getLatitude()),Toast.LENGTH_SHORT).show();


        }}

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

}
*/
