package mvit.edu.busroutes;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity implements
		Fragment1.DataPassListener {

	String TabFragmentB;
	String busno = null;
	ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from activity_main.xml
		setContentView(R.layout.activity_main);

		// Locate the viewpager in activity_main.xml
		viewPager = (ViewPager) findViewById(R.id.pager);

		// Set the ViewPagerAdapter into ViewPager
		viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

	}

	@Override
	public void passData(String data) {

		busno = data;

		viewPager.setCurrentItem(2);

	}

	public void setTabFragmentB(String t) {
		TabFragmentB = t;
	}

	public String getTabFragmentB() {
		return TabFragmentB;
	}

}