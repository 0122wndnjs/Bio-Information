/**
 * Bioinfo class - main method
 * @author Joowon Kim 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bioinfo {
	/**
	 * main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("The number of argumnets is not 2");
			System.exit(0);
		}

		Sequence[] seq = new Sequence[Integer.parseInt(args[0])]; // setting sequence array

		File file = new File(args[1]);
		Scanner scanner = null;
		DNARNA dr = new DNARNA();

		if (!file.exists()) { // when file does not exist, show the below message
			System.out.println("Given file does not exist");
			System.exit(0);
		}
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scanner.hasNextLine()) { // scanner
			String s = scanner.nextLine();
			if (s.equals(""))
				continue;
			dr.DnaRna(s, seq);
		}
		scanner.close();
	}
}
