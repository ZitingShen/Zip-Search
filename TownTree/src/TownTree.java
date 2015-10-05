import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TownTree {

	public static void main(String[] args) {

		try {
			BST<String> towns = new BST<String>();

			Scanner lines = new Scanner(new File("data/unsorted towns.txt"));
			while (lines.hasNext()) {
				towns.add(lines.nextLine());
			}
			lines.close();

			System.out.println("Unsorted data:");
			System.out.println("1. Preorder:\n" + towns.preOrder());
			System.out.println("2. Postorder:\n" + towns.postOrder());
			System.out.println("3. Inorder:\n" + towns.inOrder());
			System.out.println("Tree Height: " + towns.height());
			System.out.println("Tree Size: " + towns.size() + "\n");

			towns = new BST<String>();
			System.out.println("Tree cleared!");
			System.out.println("Tree Height: " + towns.height());
			System.out.println("Tree Size: " + towns.size() + "\n");

			lines = new Scanner(new File("data/sorted towns.txt"));
			while (lines.hasNext()) {
				towns.add(lines.nextLine());
			}
			lines.close();

			System.out.println("Sorted data:");
			System.out.println("1. Preorder:\n" + towns.preOrder());
			System.out.println("2. Postorder:\n" + towns.postOrder());
			System.out.println("3. Inorder:\n" + towns.inOrder());
			System.out.println("Tree Height: " + towns.height());
			System.out.println("Tree Size: " + towns.size() + "\n");
		} catch (FileNotFoundException exc) {
			System.out.println("File not found!");
		}

	}
}
