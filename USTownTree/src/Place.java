import java.util.ArrayList;

public class Place implements Comparable<Place> {
	// Attributes
	private ArrayList<String> zip = new ArrayList<String>();
	private String town;
	private String state;
	private String area;
	private String county;
	private String timeZone;
	private String population;
	private String location;

	// Constructor
	Place(String l) {
		location = l;
	}
	
	Place(String z, String t, String s, String a, String c, String tz, String p) {
		zip.add(z);
		town = t;
		state = s;
		area = a;
		county = c;
		timeZone = tz;
		population = p;
		location = town + ", " + state;
	}

	// Accessors
	public ArrayList<String> getZip() {
		return zip;
	}

	public String getTown() {
		return town;
	}

	public String getState() {
		return state;
	}

	public String getPhoneAreaCode() {
		return area;
	}

	public String getCounty() {
		return county;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public String getPopulation() {
		return population;
	}

	public String getLocation() {
		return location;
	}

	// Print
	public String toString() {
		return "Town: " + location + ", " + zip + " (Area Code: " + area
				+ ", Time Zone: " + timeZone + ")\nCounty: " + county
				+ ", population " + population;
	}

	// Use lowercase to compare.
	public boolean equals(String seek) {
		return location.toLowerCase().equals(seek.toLowerCase());
	}
	
	public int compareTo(Place newPlace) {
		String location1 = location.toLowerCase();
		String location2 = newPlace.getLocation().toLowerCase();
		return location1.compareTo(location2);
	}
	
	public void addZip(ArrayList<String> newZip) {
		zip.addAll(newZip);
	}
}
