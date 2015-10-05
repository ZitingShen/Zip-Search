import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class USTownTree {

	public static void main(String[] args) {

		try {
			int success = 0;
			int unsuccess = 0;
			int successCom = 0;
			int unsuccessCom = 0;

			BST<Place> towns = new BST<Place>();
			int entries = 0;
			Scanner lines = new Scanner(new File("data/zipCodes.txt"));

			// Add nodes to the tree.
			while (lines.hasNext()) {
				String[] pieces = lines.nextLine().split(",");
				for (int i = 0; i < pieces.length; i++) {
					pieces[i] = pieces[i].trim();
				}
				Place newTown = new Place(pieces[0], pieces[1], pieces[2],
						pieces[3], pieces[5], pieces[6], pieces[9]);
				Place oldTown = towns.find(newTown);
				if (oldTown != null) { // if same, replace
					newTown.addZip(oldTown.getZip());
					towns.add(newTown);
				} else { // if different, add and increment size
					towns.add(newTown);
					entries++;
				}
			}
			lines.close();

			// The height and entries of the tree.
			System.out.println("Total entries: " + entries);
			System.out.println("The height of the tree: " + towns.height()
					+ "\n");

			 // Find the towns.
			 lines = new Scanner(new File("data/towns.txt"));
			 while (lines.hasNext()) {
			 String l = lines.nextLine();
			 Place newPlace = new Place(l);
			 System.out.println("Query: " + l);
			
			 Place result = towns.find(new Place(l));
			 if (result == null) {
			 System.out.println("Not found!" + "\n");
			 unsuccess++;
			 unsuccessCom += towns.findComparisons(newPlace);
			 } else {
			 System.out.println("Found...");
			 System.out.println(result + "\n");
			 success++;
			 successCom += towns.findComparisons(newPlace);
			 }
			 }
			lines.close();

			// The average comparisons.
			System.out.println("Average comparisons of a query: "
					+ (successCom + unsuccessCom)
					/ (double) (success + unsuccess));
			System.out.println("Average comparisons of a successful query: "
					+ successCom / (double) success);
			System.out.println("Average comparisons of an unsuccessful query: "
					+ unsuccessCom / (double) unsuccess);

		} catch (FileNotFoundException exc) {
			System.out.println("File not found!");
		}

	}

}
