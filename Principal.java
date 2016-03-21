

import java.util.ArrayList;

public class Principal {

	public Base[] r;
	public int indexTable;

	public Principal() { // Constructor
		this.r = new Base[5]; // Initialized bases 
		//Hui-Yu: Base == Table
		//Hui-Yu: Every Base has up to 5 attributes
		this.indexTable = 0;
		for (int i = 0; i < 5; i++) {
			this.r[i] = new Base(); //Hui-Yu: A 'Principle' has up to 5 'Table'(which is 'Base')
		}
	}

	public Base getBase(int i) { // Return the table number i 
		return this.r[i];
	}

	public void general(String s) {
		String m = s.toUpperCase(); // Translate statement in upper case
		String statement = m.trim().replaceAll(" +", " "); // Delete useless spaces
		String[] words = statement.split(" "); // Split statements by space
		if (words[0].equals("CREATE")) { // Test if the statement is a create method
			String k = this.spaceCreate(m); // Replace the statement in a right order it is not
			this.create(k);
		} else {
			if (words[0].equals("INSERT")) { // Test if the statement is an insert method
				String j = this.spaceInsert(statement); // Replace the statement in a right order it is not
				this.insert(j);

			} else {
				if (words[0].equals("STOP")) { 
					System.out.println("End");
				} else {
					System.out.println("Error");
				}
			}
		}

	}

	public boolean isInsertOk(String s) {
		String[] space = s.split(" "); // Split statements by space
		int car = s.length();
		boolean b = false;
		int nTable = searchNameRelation(space[2]);
		int nbAt = this.r[nTable].getnbAttributs();
		if (space[1].equals("INTO")) { // Test if the second word is into
			if (s.substring(car - 1, car).equals(";")) { // Test if the statement is close by a ;
				if (this.isNbParenthesePair(s)) { // Test if there is of pair number of parenthese
					if (space[3].substring(0, 1).equals("(")) { // Test If the order is changing
						if ((nbAt - 1) * 2 == nbComas(s)) {
							b = true;
						} else {
							System.out.println("Error: wrong number of comas"); // Error wrong number of comas
						}
					} else {
						if (nbAt - 1 == nbComas(s)) { // Test the number of coma
							b = true;
						} else {
							System.out.println("Error: wrong number of comas"); // Error wrong number of comas
						}
					}
				} else {
					System.out.println("Error: parenthese is missing");
				}
			} else {
				System.out.println("Error: ; is missing"); // Error the statement doesn't finish with ;
			}
		} else {
			System.out.println("Second word is not INTO"); // ERROR: Second word is not INTO
		}
		return b;
	}

	public boolean isNbParenthesePair(String s) { // return TRUE if the number of parenthese in the String s is pair, otherwise FALSE
		boolean b = false;
		int nbPar = 0;
		int size = s.length(); 
		for (int i = 0; i < size - 1; i++) {
			if (s.substring(i, i + 1).equals("(") || s.substring(i, i + 1).equals(")")) {
				nbPar++;
			}
		}
		if (s.substring(size - 1).equals("(") || s.substring(size - 1).equals(")")) {
			nbPar++;
		}
		if (nbPar % 2 == 0) {
			b = true;
		}
		return b;
	}

	public int nbComas(String s) { // Return the number of comas contained in the String g
		int nb = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == ',')
				nb++;
		}
		return nb;
	}

	public boolean isCreateOk(String s) { // return TRUE if all the words of the String s are well ordered, orthewise return FALSE
		String[] arrayString = s.split(" ");
		int size = s.length();
		boolean result = false;
		int nbComas = this.nbComas(s);
		ArrayList<String> youpi = new ArrayList<String>();
		youpi = this.getOnlyKeyWords(s); // quelque soit cle primaire existe ou
											// non = plus de cle primaire
		youpi.remove(0);
		int nbAtt = youpi.size() / 2;

//		for (int i = 0; i < youpi.size(); i++) {
//		 System.out.println(youpi.get(i)); }
//
//		 System.out.println(nbComas);
//		System.out.println(nbAtt);
//
//		// System.out.println(s);

		// The word CREATE has already been checked before
		if (arrayString[1].equals("TABLE")) { //The second word has to be TABLE
			if (this.isNbParenthesePair(s)) { //The number of parenthese has to be pair
				if (arrayString[3].substring(0, 1).equals("(")) { // The fourth word hqs to be q parenthese
					if (nbAtt == nbComas + 1) { // The number of comas has to be proportional to the number of attributs
						result = true;
					} else {

						System.out.println("Error: There is a coma mistake.");
					}
				} else {
					//System.out.println("Error: There is a parenthese mistake.");
				}
			} else {
				System.out.println("Error: There is a parenthese mistake.");
			}

		} else {
			System.out.println("Error: There is a parenthese mistake.");
		}

		return result;
	}

	public String spaceCreate(String s) { // Return a String that the code could understand

		String strCorrect = s;
		int c = 0;
		// FIRST PARENTHESE -- ONE SPACE BEFORE AND ONE SPACE AFTER

		for (int k = 0; k < strCorrect.length() && c < 1; k++) {
			if (strCorrect.substring(k, k + 1).equals("(")) {
				strCorrect = s.substring(0, k) + " " + s.substring(k, k + 1) + " " + s.substring(k + 1);
				k = k + 3;
				c++;
			}
		}

		// LAST PARENTHESE -- ONE SPACE BEFORE

		if (strCorrect.substring(strCorrect.length() - 2).equals(" )")) {
		} else {
			strCorrect = strCorrect.substring(0, strCorrect.length() - 1) + " "
					+ strCorrect.substring(strCorrect.length() - 1);
		}

		// COMAS - AT LEAST ONE SPACE AFTER THE COMAS

		for (int k = 0; k < strCorrect.length(); k++) {
			if (strCorrect.substring(k, k + 1).equals(",")) {
				strCorrect = strCorrect.substring(0, k + 1) + " " + strCorrect.substring(k + 1);
				k++;
			}
		}

		// NO SPACE BEFORE THE COMA

		for (int k = 1; k < strCorrect.length(); k++) {
			if (strCorrect.substring(k, k + 1).equals(",") && strCorrect.substring(k - 1, k).equals(" ")) {
				strCorrect = strCorrect.substring(0, k - 1) + strCorrect.substring(k);
				k--;
			}
		}

		// ONLY ONE SPACE BETWEEN THE WORDS
		strCorrect = strCorrect.trim().replaceAll(" +", " ");

		return strCorrect;
	}

	public String[] getKeyWords(String s) { //Return an array of String with the key words and the words PRIMARY and KEY if they exist
		// There are the words CREATE, TABLE, and some parentheses
		String statement = s.toUpperCase(); 
		String[] arrayString = statement.split(" ");

		for (int i = 0; i < arrayString.length; i++) {
			if (arrayString[i].substring(arrayString[i].length() - 1, arrayString[i].length()).equals(",")) {
				arrayString[i] = arrayString[i].substring(0, arrayString[i].length() - 1);
			}
		}
		/*
		 * for (int i = 0; i < arrayString.length; i++) {
		 * System.out.println(arrayString[i]); }
		 */
		;
		return arrayString;
	}

	public String spaceInsert(String s) { // Replace in a right order a statement with little mistake
		String m = s;
		for (int i = 0; i < m.length(); i++) {
			if (m.substring(i, i + 1).equals("(")) { // if parentheses are not in the good place
				m = m.substring(0, i) + " " + m.substring(i);
				if (m.substring(i + 2, i + 3).equals(" ")) {
					m = m.substring(0, i + 2) + m.substring(i + 3);
				}
				i++;
			}
		}
		m = m.trim().replaceAll(" +", " ");

		// SPACES AFTER A COMA

		for (int k = 0; k < m.length(); k++) {
			if (m.substring(k, k + 1).equals(",")) {
				m = m.substring(0, k + 1) + " " + m.substring(k + 1);
				k++;
			}
		}

		// NO SPACE BEFORE THE COMA

		for (int k = 1; k < m.length(); k++) {
			if (m.substring(k, k + 1).equals(",") && m.substring(k - 1, k).equals(" ")) {
				m = m.substring(0, k - 1) + m.substring(k);
				k--;
			}
		}

		// ONLY ONE SPACE BETWEEN THE WORDS
		m = m.trim().replaceAll(" +", " ");

		return m;
	}

	public boolean withPrimaryKey(String s) { // RETURN TRUE IF THERE IS A PRIMARY KEY
		// key
		boolean result = false;
		String[] arrayString = this.getKeyWords(s); // String[]
		for (int i = 0; i < arrayString.length; i++) {
			if (arrayString[i].equals("PRIMARY")) {
				result = true;
			}
		}
		return result;

	}

	public String getNamePK(String s) { // RETURN THE NAME OF THE PRIMARY KEY
		ArrayList<String> arrayList = new ArrayList<String>();
		String[] arrayString = this.getKeyWords(s); // ok
		String primaryKey = "";

		if (this.withPrimaryKey(s) == true) {
			for (int i = 0; i < arrayString.length; i++) {
				if (arrayString[i].equals("PRIMARY")) {
					arrayString[i] = "";
					arrayString[i + 1] = "";
					primaryKey = arrayString[i - 2];
				}

			}
		}

		return primaryKey; // retourne -1 si pas de primary key

	}

	public ArrayList<String> getOnlyKeyWords(String s) { // RETURN AN ARRAYLIST OF THE KEY WORDS ONLY - WITHOUT the words CREATE, TABLE, PRIMARY and KEY
		// and without any parenthese
		ArrayList<String> arrayList = new ArrayList<String>();
		String[] arrayString = this.getKeyWords(s); // ok
		String primaryKey = "";

		for (int i = 0; i < arrayString.length; i++) {
			if (arrayString[i].equals("CREATE")) {
				arrayString[i] = "";
			}
			if (arrayString[i].equals("TABLE")) {
				arrayString[i] = "";
			}
			if (arrayString[i].equals("(")) {
				arrayString[i] = "";
			}
			if (arrayString[i].equals(")")) {
				arrayString[i] = "";
			}
		}

		if (this.withPrimaryKey(s) == true) {
			for (int i = 0; i < arrayString.length; i++) {

				if (arrayString[i].equals("PRIMARY")) {
					arrayString[i] = "";
					arrayString[i + 1] = "";

				}

			}
		}

		for (int i = 0; i < arrayString.length; i++) {
			if (!arrayString[i].equals("")) {
				arrayList.add(arrayString[i]);
			}
		}
		return arrayList;

	}

	public void create(String s) { // CREATE THE TABLE
		String primaryKey = this.getNamePK(s);
		ArrayList<String> arrayList = this.getOnlyKeyWords(s);
		String k;

		if (this.isCreateOk(s)) { //TO BE SURE THE FOLLOWING CODE WILL UNDERSTAND THE MESSAGE OF THE USER
		this.r[this.indexTable].setname(arrayList.get(0)); //the name of the table is registered in name
		this.r[this.indexTable].setnbAttributs((arrayList.size() - 1) / 2); //the number of attributs is registered in nbAttributs
		/*
		 * System.out.println(this.r[indexTable].getname()); //affiche STUDENT
		 * System.out.println(this.r[indexTable].getnbAttributs());
		 */

		for (int i = 0; i < (arrayList.size() - 1) / 2; i++) { 
			this.r[this.indexTable].Attribut[i].setName(arrayList.get(i * 2 + 1)); //Each attribut sets its name in name

			boolean iskey = primaryKey.equals(this.r[this.indexTable].Attribut[i].getName());
			this.r[this.indexTable].Attribut[i].setKey(iskey); //If an attribut is a primary key, the data is registered in key

			if (arrayList.get(i * 2 + 2).equals("INT")) { //if the type is INT ok
				this.r[this.indexTable].Attribut[i].setType(arrayList.get(i * 2 + 2));
			} else { //if it is not, the integer into VARCHAR is registred in numberChar
				k = arrayList.get(i * 2 + 2);
				int index = k.indexOf("(");
				int indexf = k.indexOf(")");
				this.r[this.indexTable].Attribut[i].setNumberChar(Integer.parseInt(k.substring(index + 1, indexf)));
			}

		}
		this.toStringCreation(indexTable); //PRINT THE TABLE
		this.indexTable++; //The index increase by one, in order to create the next table in an other place
		}
		else {
			
		}


	}

	public void toStringTable(int i) { // To print the table i 
		System.out.println("Table name : " + this.r[i].getname());
		for (int j = 0; j < this.r[i].getnbAttributs(); j++) {
			System.out.println(this.r[i].Attribut[j].getName());
			System.out.println(this.r[i].Attribut[j].getData());
		}
	}

	public void toStringCreation(int i) { // To print an information when a table has been created
		System.out.println("Table : " + this.r[i].getname() + " has been created with " + this.r[i].getnbAttributs()
				+ " attribut(s)");
	}

	public void insert(String m) { 
		String s = m.toUpperCase();
		String[] space = s.split(" ");
		int nTable = searchNameRelation(space[2]);
		if (space[1].equals("INTO")) { // Test if the second word is into
			if (searchNameRelation(space[2]) >= 0) { // Test if the searched table exists
				if (isInsertOk(s)) { // If the statement is correct
					if (space[3].substring(0, 1).equals("(")) { // if the insertion order has been changed
						String[] z = this.reorganized(s);
						this.completInsert(z, nTable);
					} else {
						String[] z = this.simpleInsert(s);
						this.completInsert(z, nTable);
					}
				}

			} else {
				System.out.println("Table doesn't exist");
			}
		}
		System.out.println("Statelent error");
	}

	public String[] simpleInsert(String s) {
		String[] space = s.split(" "); // Split by space
		String[] koma = s.split(", "); // split by koma
		int nTable = searchNameRelation(space[2]);
		int nbCase = this.r[nTable].getnbAttributs(); // Nombre d'attributs
		String[] old = new String[nbCase * 4];

		// Insert data's name, type and data and size in an array
		int ind = koma[0].indexOf("(");
		String w = koma[0].substring(ind + 1, koma[0].length()); // first
		if (w.substring(0, 1).equals("'")) { // if it's a string
			old[nbCase] = "STRING"; // insert type
			old[2 * nbCase] = w.substring(1, w.length() - 1); // insert value 
			old[3 * nbCase] = String.valueOf(w.substring(1, w.length() - 1).length()); // insert size of the string

		} else {
			old[nbCase] = "INT"; // insert type
			old[2 * nbCase] = w; // insert value
			old[3 * nbCase] = String.valueOf(-1);
		}
		for (int i = 1; i < nbCase - 1; i++) { // Intern
			if (koma[i].substring(0, 1).equals("'")) { // if it"s a string
				old[nbCase + i] = "STRING";
				old[i + 2 * nbCase] = koma[i].substring(1, koma[i].length() - 1);
				old[i + 3 * nbCase] = String.valueOf(koma[i].substring(1, koma[i].length() - 1).length());
			} else {
				old[i + nbCase] = "INT";
				old[i + 2 * nbCase] = koma[i];
				old[i + 3 * nbCase] = String.valueOf(-1);
			}
		}
		int nind = koma[nbCase - 1].indexOf(")");
		String v = koma[nbCase - 1].substring(0, nind); // last one
		if (v.substring(0, 1).equals("'")) { // if it's a string
			old[nbCase * 2 - 1] = "STRING";
			old[nbCase * 3 - 1] = v.substring(1, v.length() - 1);
			old[nbCase * 4 - 1] = String.valueOf(v.substring(1, v.length() - 1).length());
		} else {
			old[nbCase * 2 - 1] = "INT";
			old[nbCase * 3 - 1] = v.substring(0, v.length());
			old[nbCase * 4 - 1] = String.valueOf(-1);
		}
		return old;
	}

	public String[] reorganized(String s) {
		String[] space = s.split(" "); // Split by space
		String[] koma = s.split(", "); // Split by koma
		int nTable = searchNameRelation(space[2]); // Table number
		int nbCase = this.r[nTable].getnbAttributs(); //
		String[] data = new String[nbCase * 4]; // Array in the right order to be inserted
		String[] old = new String[nbCase * 4]; // Array in the old order

		// Insert attribut's name in the fourth first cases
		int index = koma[0].indexOf("(");
		old[0] = koma[0].substring(index + 1, koma[0].length()); // insert first attribut
		for (int i = 1; i < nbCase - 1; i++) { // Attributs interns ancien ordre
			old[i] = koma[i];
		}
		int nindex = koma[nbCase - 1].indexOf(")");
		old[nbCase - 1] = koma[nbCase - 1].substring(0, nindex); // insert last attribut

		// insert data and type
		int ind = koma[nbCase - 1].indexOf("(");
		String w = koma[nbCase - 1].substring(ind + 1, koma[nbCase - 1].length()); // first
		if (w.substring(0, 1).equals("'")) { // if it's a string
			old[nbCase] = "STRING";
			old[2 * nbCase] = w.substring(1, w.length() - 1);
			old[3 * nbCase] = String.valueOf(w.substring(1, w.length() - 1).length());

		} else {
			old[nbCase] = "INT";
			old[2 * nbCase] = w;
			old[3 * nbCase] = String.valueOf(-1);
		}
		for (int i = nbCase; i < nbCase * 2 - 1; i++) { // Intern
			if (koma[i].substring(0, 1).equals("'")) { // if it's a string
				old[i + 1] = "STRING";
				old[i + nbCase + 1] = koma[i].substring(1, koma[i].length() - 1);
				old[i + 2 * nbCase + 1] = String.valueOf(koma[i].substring(1, koma[i].length() - 1).length());
			} else {
				old[i + 1] = "INT";
				old[i + nbCase + 1] = koma[i];
				old[i + 2 * nbCase + 1] = String.valueOf(-1);
			}
		}
		int nind = koma[nbCase * 2 - 2].indexOf(")");
		String v = koma[nbCase * 2 - 2].substring(0, nind); // last
		if (v.substring(0, 1).equals("'")) { // if it's a string
			old[nbCase * 2 - 1] = "STRING";
			old[nbCase * 3 - 1] = v.substring(1, v.length() - 1);
			old[nbCase * 4 - 1] = String.valueOf(v.substring(1, v.length() - 1).length());
		} else {
			old[nbCase * 2 - 1] = "INT";
			old[nbCase * 3 - 1] = v.substring(0, v.length());
			old[nbCase * 4 - 1] = String.valueOf(-1);
		}

		// insert the data in the new array in the right order

		for (int i = 0; i < nbCase; i++) { // insert attribut's names
			boolean b = false;
			int j = 0;
			while (!b && j < nbCase) {
				if (old[i].equals(this.r[nTable].Attribut[j].getName())) {
					b = true;
					data[j] = old[i];
					data[j + nbCase] = old[i + nbCase];
					data[j + 2 * nbCase] = old[i + 2 * nbCase];
					data[j + 3 * nbCase] = old[i + 3 * nbCase];
				}
				j++;
			}
		}
		return data;
	}

	public int nameTable(String s) {
		String[] space = s.split(" ");
		int nTable = searchNameRelation(space[2]);
		return nTable;
	}

	public void completInsert(String[] tableauRange, int nTable) {

		int nbAt = this.r[nTable].getnbAttributs();

		if (tableauRange.length / 4 == nbAt) { //If it's the right number of attributs
			boolean b = false;
			int j = 0;
			while (!b && j < nbAt) {
				if (this.r[nTable].Attribut[j].isKey()) {
					b = true;
				}
				j++;
			}
			if (!b) { // If there isn't a primary key 
				for (int i = 0; i < nbAt; i++) {
					int m = Integer.parseInt(tableauRange[i + 3 * nbAt]);
					if (this.r[nTable].Attribut[i].getType().equals(tableauRange[i + nbAt])
							&& this.r[nTable].Attribut[i].getNumberChar() >= m) { // if type and char's number are ok 
						this.r[nTable].Attribut[i].data.add(tableauRange[i + 2 * nbAt]); // insert data
						this.toStringTable(nTable);
					} else {
						System.out.println("error");
					}
				}
			} else { // If there is a primary key 
				boolean bo = false;
				for (int k = 0; k < this.r[nTable].Attribut[j - 1].data.size(); k++) {
					if (tableauRange[j - 1 + 2 * nbAt].equals("")
							|| tableauRange[j - 1 + 2 * nbAt].equals(this.r[nTable].Attribut[j - 1].data.get(k))) { // if the value of the primary key already exist
						bo = true;
					}
				}
				if (bo) {
					System.out.println("Primary key's error");
				} else {
					boolean c = false;
					int i = 0;
					while (!c && i < nbAt) {
						int m = Integer.parseInt(tableauRange[i + 3 * nbAt]);
						if (this.r[nTable].Attribut[i].getType().equals(tableauRange[i + nbAt])
								&& this.r[nTable].Attribut[i].getNumberChar() >= m) { // if type and char's number are ok 
							this.r[nTable].Attribut[i].data.add(tableauRange[i + 2 * nbAt]); // insert data
						} else {
							System.out.println("error");
							c = true;
							for (int h = 0; h < i; h++) {
								int f = this.r[nTable].Attribut[i].data.size();
								this.r[nTable].Attribut[h].data.remove(f);
							}
						}
						i++;
					}
					if (!c) {
						this.toStringTable(nTable);
					}
				}
			}
		} else {
			System.out.println("Error in Attribut's number");
		}

	}

	public int searchNameRelation(String s) {
		int i = -1;
		boolean b = false;
		int j = 0;
		while (!b && j < 5) {
			if (this.r[j].name.equals(s)) {
				i = j;
				b = true;
			}
			j++;
		}
		return i;
	}

	public int nbAttributs(String s) { // Find the number of attribut in the statement
		int i = -1;
		String[] k = s.split(",");
		i = k.length;
		return i;
	}

	public static void main(String[] args) {

		Principal p = new Principal();
		String s = "";
		
		  while(!s.equals("STOP")){ System.out.println("To quit write STOP");
		  System.out.println("What is your request ?"); s =
		  Clavier.lireString(); p.general(s); }
		 
	}
}
