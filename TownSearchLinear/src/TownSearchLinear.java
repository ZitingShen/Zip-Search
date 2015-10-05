import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TownSearchLinear {
	
	// Input data and return an ArrayList of Place.
	public static ArrayList<Place> inputData(File f)
			throws FileNotFoundException {
		// Access to the file.
		Scanner lines = new Scanner(f);

		// Create an array of Place objects.
		ArrayList<Place> data = new ArrayList<Place>();

		// Put each group of info into a Place object.
		while (lines.hasNext()) {
			String[] pieces = lines.nextLine().split(",");
			for (int i = 0; i < pieces.length; i++) {
				pieces[i] = pieces[i].trim();
			}
			data.add(new Place(pieces[0], pieces[1], pieces[2], pieces[3],
					pieces[5], pieces[6], pieces[9]));
		}

		lines.close();

		return data;
	} // inputData()

	// Search the zip of the location.
	public static int[] searchData(String seek, ArrayList<Place> data) {

		int times = 0;
		for (int i = 0; i < data.size(); i++) {
			times++;
			if (data.get(i).equals(seek)) {
				return new int[] { i, times };
			}
		}
		return new int[] { -1, times };

	} // searchData()

	public static void main(String[] args) {
		// Attributes to count comparisons.
		int success = 0;
		int unsuccess = 0;
		int successCom = 0;
		int unsuccessCom = 0;

		String response = "y";
		do {
			try {
				// Input the data.
				ArrayList<Place> data = inputData(new File("Data/zipCodes.txt"));

				// Ask what to search.
				BufferedReader br = new BufferedReader(new InputStreamReader(
						System.in));
				System.out.print("Query: ");
				String seek = br.readLine();

				// Search zip.
				int[] result = searchData(seek, data);
				int index = result[0];
				int times = result[1];
				if (index >= 0) {
					success++;
					successCom += times;

					System.out.println("Found...");
					System.out.println(data.get(index).toString());
				} else {
					unsuccess++;
					unsuccessCom += times;

					System.out.println("Not found...");
				}
				System.out.println("Comparisons: " + times + " times.\n");

				// Search again?
				System.out
						.print("Search again? (y to continue, otherwise quit): ");
				response = br.readLine().toLowerCase();
				System.out.println();

			} catch (FileNotFoundException exc) {
				System.out.println("Please enter a valid file path!");
				System.exit(1);
			} catch (IOException exc) {
				System.out.println("Invalid Format!");
				System.exit(1);
			}
		} while (response.equals("y"));

		// Times of comparisons.
		System.out.println("Average comparisons of a query: "
				+ (successCom + unsuccessCom) / (double) (success + unsuccess));
		System.out.println("Average comparisons of a successful query: "
				+ successCom / (double) success);
		System.out.println("Average comparisons of an unsuccessful query: "
				+ unsuccessCom / (double) unsuccess);
		System.out.println("Good Bye!");

	} // main()
} // end of class
