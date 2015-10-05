public class Place {
	// Attributes
	private String zip;
	private String town;
	private String state;

	// Constructor
	Place(String z, String t, String s) {
		zip = z;
		town = t;
		state = s;
	}

	// Accessors
	public String getZip() {
		return zip;
	}

	public String getTown() {
		return town;
	}

	public String getState() {
		return state;
	}

	// Print
	public String toString() {
		return "The zip code " + zip + " belongs to " + town + ", " + state;
	}

	public boolean equals(String seek) {
		return zip.equals(seek);
	}
}
