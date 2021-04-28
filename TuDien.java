import java.io.*;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.io.BufferedReader;

public class TuDien {
	final static String filePath = "slang.txt";

	static Map<String, String> dictionary = new HashMap<String, String>();

	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) throws IOException {

		CreateDictionary();

		var a = 0;
		while (a != -1) {
			System.out.println("----------------------------------");
			System.out.println("Chon thao tac");
			System.out.println(
					"1: Search slang word | 2: Search definition | 3: Show history | 4: Add slangword | 5: Edit slang word");
			System.out.println(
					"6: Delete slang word | 7: Reset lang words | 8: Random slang word | 9: lang word quizz | 10: definition quizz");
			System.out.print("selection input: ");

			a = input.nextInt();

			if (a == 1) {
				SearchSW();
			}
			// if (a == 2) {
			// CapNhatHocSinh();
			// }
			// if (a == 3) {
			// XoaHocSinh();
			// }
			// if (a == 4) {
			// XemDanhSachHocSinh();
			// }
			// if (a == 5) {
			// ExportHocSinh();
			// }
		}

	}

	public static void SearchSW() {
		String value;

		System.out.print("input slang word: ");
		value = input.next();

		var rs = dictionary.get(value);

		if (rs == null || rs.isEmpty())
			rs = dictionary.get(value.toUpperCase());

		System.out.println("definition: " + rs);

	}

	public static void SearchDefinition() {
		System.out.print("input definition: ");
		String definition = sc.nextLine();
		for (Entry<String, String> entry : slangdictionary.entrySet()) {
			// System.out.println(entry.getValue());
			if (entry.getValue().contains(definition)) {
				System.out.println("Slang Word : " + entry.getKey());
			}
		}
	}

	public static void CreateDictionary() {
		String thisLine = null;

		try {
			// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader(new FileReader(filePath));

			while ((thisLine = br.readLine()) != null) {

				String[] str = thisLine.split("`");

				if (str.length < 2)
					continue;

				dictionary.put(str[0], str[1]);

			}
			br.close();
			// System.out.println(dictionary);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
