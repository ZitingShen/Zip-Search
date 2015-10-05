// Set up the canvas.
void setup() {
  //size(500, 500);
  size(displayWidth, displayHeight);
  background(255);
  noLoop();
}

// Draw the pots.
void draw() {
  
  // Deal with the data.
  String lines[] = loadStrings("zips.txt");
  String basic[] = splitTokens(lines[0], " ,#");
  
  Place data[] = new Place[int(basic[0])];
  
  for(int i = 1; i < lines.length; i++) {
    String pieces[] = splitTokens(lines[i], "\t,");
    
    // We have to use float() to turn the x and y 
    // coordinates into floating numbers.
    // And also use trim() to get rid of the space 
    // before the state.
    data[i - 1] = new Place(pieces[0], pieces[3], 
                  trim(pieces[4]), float(pieces[1]), 
                  float(pieces[2]));
  }

  // Plot the data.
  strokeWeight(1);
  for(Place p: data) {    
    if(p.getState().equals("PA"))
      stroke(255, 0, 0);
    else
      stroke(0);
    point(map(p.getX(), float(basic[1]), float(basic[2]), 
          0.05*width, 0.95*width), map(p.getY(), float(basic[3]), 
          float(basic[4]), 0.95*height, 0.05*height));
  }
}

// Model each zip code to contain the zip code,
// town, state, x and y coordinate.
class Place {
  String zipCode;
  String town;
  String state;
  float xCo, yCo;
  
  // Constructor
  Place(String z, String t, String s, float x, float y) {
    zipCode = z;
    town = t;
    state = s;
    xCo = x;
    yCo = y;
  }
  
  // Get the zip code.
  String getZip() {
    return zipCode;
  }
  
  // Get the town.
  String getTown() {
    return town;
  }
  
  // Get the state.
  String getState() {
    return state;
  }
  
  // Get the x coordinate.
  float getX() {
    return xCo;
  }
  
  // Get the y coordinate.
  float getY() {
    return yCo;
  }
  
  // Present the object as a string.
  String toString() {
    return "Location: " + town + ", " + state + "\n" +
           "Coordinate: " + xCo + ", " + yCo + "\n" +
           "Zip code: " + zipCode;
  }
}
