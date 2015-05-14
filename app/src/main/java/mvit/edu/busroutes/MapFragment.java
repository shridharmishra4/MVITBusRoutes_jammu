package mvit.edu.busroutes;

import java.util.ArrayList;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapFragment extends Fragment {

	MapView mMapView;
	private GoogleMap googleMap = null;
	LocationManager Lmgr;
	ArrayList<LatLng> points;
	boolean first_time = true;
	Polyline line;
	Circle position;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View v = inflater.inflate(R.layout.map_layout, container, false);
		mMapView = (MapView) v.findViewById(R.id.mapView);
		mMapView.onCreate(savedInstanceState);
		mMapView.onResume();// needed to get the map to display immediately
		points = new ArrayList<LatLng>();

		try {
			MapsInitializer.initialize(getActivity().getApplicationContext());
		} catch (Exception e) {
			e.printStackTrace();
		}

		googleMap = mMapView.getMap();
		Lmgr = (LocationManager) this.getActivity().getSystemService(
				this.getActivity().LOCATION_SERVICE);
		Lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,
				new LocListener());
		// googleMap.setMyLocationEnabled(true);

		return v;
	}

	@Override
	public void onResume() {
		super.onResume();
		mMapView.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		mMapView.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mMapView.onDestroy();
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		mMapView.onLowMemory();
	}

	public class LocListener implements LocationListener {

		@Override
		public void onLocationChanged(Location loc) {
			// TODO Auto-generated method stub
			
			if(!first_time){
				position.remove();
				line.remove();
			}
			position = googleMap.addCircle(new CircleOptions() .center(new
			  LatLng(loc.getLatitude(), loc.getLongitude()))
			  .radius(1).strokeColor(Color.BLUE).fillColor(Color.BLUE));
			 
			if (first_time) {
				CameraUpdate update = CameraUpdateFactory.newLatLngZoom(
						new LatLng(loc.getLatitude(), loc.getLongitude()), 16);
				googleMap.animateCamera(update);
				first_time = false;
			}
			PolylineOptions options = new PolylineOptions();
			points.add(new LatLng(loc.getLatitude(), loc.getLongitude()));
			options.addAll(points);
			options.width(10).color(Color.RED);
			 line = googleMap.addPolyline(options);
			
			

		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}

	}

}
