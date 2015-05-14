package mvit.edu.busroutes;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity {

	  static final LatLng TutorialsPoint = new LatLng(21 , 57);
	   private GoogleMap googleMap;
	   @Override
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.map_activity);
	      try { 
	            if (googleMap == null) {
	               googleMap = ((MapFragment) getFragmentManager().
	               findFragmentById(R.id.map)).getMap();
	            }
	         googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	         Marker TP = googleMap.addMarker(new MarkerOptions().
	         position(TutorialsPoint).title("TutorialsPoint"));

	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	   }

}
