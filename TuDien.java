import java.io.*;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.*;

public class TuDien {
	final static String filePath = "slang.txt";

	static Map<String, String> dictionary = new HashMap<String, String>();

	static HashSet<String> foundSlang = new HashSet<String>();

	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) throws IOException {

		CreateDictionary();

		var a = 0;
		while (a != -1) {
			System.out.println("----------------------------------");
			System.out.println("Type your choice | -1: quit");
			System.out.println(
					"1: Search slang word | 2: Search definition | 3: Show history | 4: Add slangword | 5: Edit slang word");
			System.out.println(
					"6: Delete slang word | 7: Reset lang words | 8: Random slang word | 9: definition quizz | 10: slangword quizz");
			System.out.print("selection input: ");

			a = input.nextInt();

			if (a == 1) {
				SearchSlangWord();
			}
			if (a == 2) {
				SearchDefinition();
			}
			if (a == 3) {
				PrintFoundSlang();
			}
			if (a == 4) {
				AddSlangWord();
			}
			if (a == 5) {
				EditSlangWord();
			}
			if (a == 6) {
				DeleteSlangWord();
			}
			if (a == 7) {
				ResetDictionary();
			}
			if (a == 8) {
				PrintRandomSlangWord();
			}
			if (a == 9) {
				DefinitionQuizz();
			}
			if (a == 10) {
				SlangWordQuizz();
			}
		}

	}

	public static void SearchSlangWord() {
		String value;

		System.out.print("input slang word: ");
		value = input.next();

		foundSlang.add(value);

		var rs = dictionary.get(value);

		if (rs == null || rs.isEmpty())
			rs = dictionary.get(value.toUpperCase());

		System.out.println("definition: " + rs);

	}

	public static void SearchDefinition() {
		System.out.print("input definition: ");
		String definition = input.next();

		for (Entry<String, String> entry : dictionary.entrySet()) {
			if (entry.getValue().contains(definition)) {
				System.out.println("Slang Word : " + entry.getKey());
			}
		}
	}

	public static void PrintFoundSlang() {
		System.out.println("Found Slang: ");
		int i = 0;
		for (String string : foundSlang) {
			System.out.println(i + " - " + string);
			i++;
		}
	}

	public static void AddSlangWord() {
		System.out.print("input slang word: ");
		String slang = input.next();

		Boolean add = false;

		String def = null;

		for (Entry<String, String> entry : dictionary.entrySet()) {
			if (entry.getKey().equals(slang) || entry.getKey().equals(slang.toUpperCase())) {
				System.out.println("slang was existing | 1: Overwrite | 2: Add new ");
				System.out.print("selection: ");
				int selection = input.nextInt();
				if (selection == 1) {
					System.out.print("input definition: ");
					String definition = input.next();
					entry.setValue(definition);
					add = true;
				} else if (selection == 2) {
					System.out.print("input definition: ");
					String definition = input.next();
					def = definition;
					add = true;
				}
			}
		}

		if (def != null){
			if (!def.isEmpty() )
				dictionary.put("-" + slang, def);
		}

		if (!add) {
			System.out.print("input definition: ");
			String definition = input.next();
			dictionary.put(slang, definition);
			add = true;
		}

		OverwriteDictionary();

	}

	public static void EditSlangWord() {
		System.out.print("input slang word: ");
		String slang = input.next();

		for (Entry<String, String> entry : dictionary.entrySet()) {
			if (entry.getKey().equals(slang) || entry.getKey().equals(slang.toUpperCase())) {
				System.out.print("input definition: ");
				String definition = input.next();
				entry.setValue(definition);
			}
		}

		OverwriteDictionary();
	}

	public static void DeleteSlangWord() {
		System.out.print("input slang word: ");
		String slang = input.next();

		System.out.println("Are you sure to remove [" + slang + "] from dictionary?");
		System.out.print("input 1 to confirm, other to cancel: ");
		int selection = input.nextInt();

		if (selection == 1) {
			dictionary.remove(slang);
			dictionary.remove(slang.toUpperCase());
		}

		OverwriteDictionary();
	}

	public static void ResetDictionary() {
		dictionary = new HashMap<String, String>();
		String thisLine = null;

		try {
			// open input stream test.txt for reading purpose.
			BufferedReader br = new BufferedReader(new FileReader("slangBackup.txt"));

			while ((thisLine = br.readLine()) != null) {

				String[] str = thisLine.split("`");

				if (str.length < 2)
					continue;

				dictionary.put(str[0], str[1]);

			}
			br.close();
			System.out.println("Dictionary reset successfully");
			// System.out.println(dictionary);
		} catch (Exception e) {
			e.printStackTrace();
		}

		OverwriteDictionary();

	}

	public static void PrintRandomSlangWord() {
		Random generator = new Random();

		int value = generator.nextInt(dictionary.size()) + 1;

		Object firstKey = dictionary.keySet().toArray()[value];
		Object valueForFirstKey = dictionary.get(firstKey);

		System.out.println(firstKey);
		System.out.println(valueForFirstKey);
	}

	public static void DefinitionQuizz() {
		Random generator = new Random();

		int value = generator.nextInt(dictionary.size()) + 1;

		Object firstKey = dictionary.keySet().toArray()[value];
		Object valueForFirstKey = dictionary.get(firstKey);

		String ans = valueForFirstKey.toString();

		List<String> definitions = new ArrayList<>();
		definitions.add(ans);

		System.out.println("-------------------");
		System.out.println("slang word: " + firstKey);
		System.out.println("Choose the right definition");

		for (int i = 0; i < 3; i++) {

			value = generator.nextInt(dictionary.size()) + 1;
			Object firstKey2 = dictionary.keySet().toArray()[value];
			Object valueForFirstKey2 = dictionary.get(firstKey2);

			definitions.add(valueForFirstKey2.toString());
		}

		Collections.shuffle(definitions);

		HashMap<String, String> quizz = new HashMap<String, String>();
		quizz.put("A", definitions.get(0));
		quizz.put("B", definitions.get(1));
		quizz.put("C", definitions.get(2));
		quizz.put("D", definitions.get(3));

		for (Entry<String, String> entry : quizz.entrySet()) {
			System.out.println(entry.getKey() + ". " + entry.getValue());
		}

		System.out.print("Input answer: ");
		String q = input.next();
		String qU = q.toUpperCase();

		if (quizz.containsKey(q)) {
			if (quizz.get(q).equals(ans)) {
				System.out.println("Right answer, Congratulation!");
			} else {
				System.out.println("Wrong answer, good luck next time!");
			}
		} else if (quizz.containsKey(qU)) {
			if (quizz.get(qU).equals(ans)) {
				System.out.println("Right answer, Congratulation!");
			} else {
				System.out.println("Wrong answer, good luck next time!");
			}
		} else {
			System.out.println("Wrong answer, good luck next time!");
		}
	}

	public static void SlangWordQuizz() {
		Random generator = new Random();

		int value = generator.nextInt(dictionary.size()) + 1;

		Object firstKey = dictionary.keySet().toArray()[value];
		Object valueForFirstKey = dictionary.get(firstKey);

		String ans = firstKey.toString();

		List<String> definitions = new ArrayList<>();
		definitions.add(ans);

		System.out.println("-------------------");
		System.out.println("definition: " + valueForFirstKey);
		System.out.println("Choose the right slangword");

		for (int i = 0; i < 3; i++) {

			value = generator.nextInt(dictionary.size()) + 1;
			Object firstKey2 = dictionary.keySet().toArray()[value];
			Object valueForFirstKey2 = dictionary.get(firstKey2);

			definitions.add(firstKey2.toString());
		}

		Collections.shuffle(definitions);

		HashMap<String, String> quizz = new HashMap<String, String>();
		quizz.put("A", definitions.get(0));
		quizz.put("B", definitions.get(1));
		quizz.put("C", definitions.get(2));
		quizz.put("D", definitions.get(3));

		for (Entry<String, String> entry : quizz.entrySet()) {
			System.out.println(entry.getKey() + ". " + entry.getValue());
		}

		System.out.print("Input answer: ");
		String q = input.next();
		String qU = q.toUpperCase();

		if (quizz.containsKey(q)) {
			if (quizz.get(q).equals(ans)) {
				System.out.println("Right answer, Congratulation!");
			} else {
				System.out.println("Wrong answer, good luck next time!");
			}
		} else if (quizz.containsKey(qU)) {
			if (quizz.get(qU).equals(ans)) {
				System.out.println("Right answer, Congratulation!");
			} else {
				System.out.println("Wrong answer, good luck next time!");
			}
		} else {
			System.out.println("Wrong answer, good luck next time!");
		}
	}

	public static void OverwriteDictionary() {
		try {
			// open input stream test.txt for reading purpose.
			BufferedWriter br = new BufferedWriter(new FileWriter(filePath));

			br.write("");

			for (Entry<String, String> entry : dictionary.entrySet()) {
				br.append(entry.getKey() + "`" + entry.getValue() + "\n");
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
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
