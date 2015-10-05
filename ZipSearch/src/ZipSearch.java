import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ZipSearch {

	// Input data and return an array of Place.
	public static Place[] inputData(File f) throws FileNotFoundException {
		// Access to the file.
		Scanner lines = new Scanner(f);

		// Obtain the general info.
		String[] basicInfo = lines.nextLine().split(",");
		int l = Integer.parseInt(basicInfo[0].split(" ")[1]);

		// Create an array of Place objects.
		Place[] data = new Place[l];
		int counter = 0;

		// Put each group of info into a Place object.
		while (lines.hasNext()) {
			String[] pieces1 = lines.nextLine().split("\t");
			String[] pieces2 = pieces1[3].split(", ");
			data[counter] = new Place(pieces1[0], pieces2[0], pieces2[1]);
			counter++;
		}

		lines.close();

		return data;
	} // inputData()

	// Use binary search and recursion to get the location of a zip.
	public static String searchData(String input, Place[] dataPieces) {

		// Get the basic info of the data set.
		int length = dataPieces.length;
		int mid = length / 2;

		// May throw NumberFormatException if the input is not a 5-digit number.
		if (input.length() != 5)
			throw new NumberFormatException();

		// Test if the input is in the data set.
		if (dataPieces[mid].equals(input)) {
			return dataPieces[mid].toString();
		} else if (dataPieces.length == 1) {
			return "The zip code " + input + " does not exist.";
		} else { // Binary search
			if (Integer.parseInt(input) < Integer.parseInt(dataPieces[mid]
					.getZip())) {
				return searchData(input, Arrays.copyOfRange(dataPieces, 0, mid));
			} else {
				return searchData(input,
						Arrays.copyOfRange(dataPieces, mid, length));
			}
		}
	} // searchData()

	public static void main(String[] args) {
		int response = 0;
		do {
			try {
				// Input the data.
				Place[] data = inputData(new File("Data/zips.txt"));

				// Ask for input.
				String input = JOptionPane.showInputDialog(null,
						"Please enter the zip code you want to search for:",
						"Zip Code Search", JOptionPane.QUESTION_MESSAGE);

				if (input != null) { // If click "OK"...
					// Eliminate spaces before and after the input.
					input = input.trim();
					System.out.println("You asked me to search for zip code: "
							+ input);

					// Seek for the zip.
					String output = searchData(input, data);
					JOptionPane.showMessageDialog(null, output,
							"Zip Code Search", JOptionPane.INFORMATION_MESSAGE,
							null);
					System.out.println(output);

					// Ask if search again.
					String[] options = { "Yes", "No" };
					response = JOptionPane.showOptionDialog(null,
							"Do you want me to search again?",
							"Zip Code Search", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);

					if (response == 0) {
						System.out
								.println("Do you want me to search again? Yes\n");
					} else {
						System.out
								.println("Do you want me to search again? No\n");
					}
				} else { // If click "cancel"...
					System.out.println("You cancelled the search.\n");
					response = 1;
				}
			} catch (FileNotFoundException exc) {
				System.out.println("Please enter a valid file path!");
				System.exit(1);
			} catch (NumberFormatException exc) {
				// Give info about the incorrect input.
				JOptionPane.showMessageDialog(null,
						"Please enter a valid zip code!", "Zip Code Search",
						JOptionPane.ERROR_MESSAGE, null);
				System.out.println("Please enter a valid zip code!");

				// Ask if search again.
				String[] options = { "Yes", "No" };
				response = JOptionPane
						.showOptionDialog(null,
								"Do you want me to search again?",
								"Zip Code Search", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE, null, options,
								options[0]);
				if (response == 0) {
					System.out.println("Do you want me to search again? Yes\n");
				} else {
					System.out.println("Do you want me to search again? No\n");
				}
			}
		} while (response == 0);

		System.out.println("Good Bye!\n");
	} // main()
} // end of class
