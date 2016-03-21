import java.util.ArrayList;


public class Test {
	public static void main(String[] args) {
		
		String before = "Bonjour                        je m     appelle Agathe";
		String after = before.trim().replaceAll(" +", " ");
		String a =after.toUpperCase();
		System.out.println(a);
		
		
		;
		//String m = Clavier.lireString();
		/*String m = "INSERT INTO Student VALUES (10, 'John Smith', 'M', 22);";
		String s = m.toUpperCase();
		String[] koma = s.split(", ");
		String[] space = s.split(" ");
		ArrayList<String> reorganized = new ArrayList<String>();
		for(int i=0; i<koma.length; i++){
			reorganized.add(koma[i]);
		}
		
		
		int index = koma[4-1].indexOf(")");
		//System.out.println(index = koma[4-1].indexOf(")"));
		//System.out.println(koma[4-1].substring(0,index));
		
		
		for (int i=0; i<koma.length; i++){
			System.out.println(koma[i]);
			//System.out.println(reorganized.get(i));
		}
		
		//System.out.println(space[0].equals("INSERT"));
		//System.out.println(space[1].equals("INTO"));
		//System.out.println(space[2].equals("STUDENT"));
		//System.out.println(koma[0].substring(koma[0].length()-1, koma[0].length()).equals("'"));
		//System.out.println(koma[1].substring(1,koma[1].length()-1));
		
		
	}
	

	/*String l = Clavier.lireString();
	String s = l.toUpperCase(); //s = String en majuscules
	String[] result = s.split(" "); //result = tableau avec tous les mots séparés, mais encore virgules
	String[] b;
	
	ArrayList<String> array = new ArrayList<String>();
			
	for (int j =0; j<result.length; j++){
		b = result[j].split(",");
		for (int k=0; k<b.length; k++){
			array.add(b[k]);
		}
	}
	
	
	
	for(int i=0; i<result.length; i++){
		System.out.println(array.get(i)); //on affiche toutes les String du tableau où tous les mots sont séparés
}*/
}


}
