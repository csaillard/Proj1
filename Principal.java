import java.util.ArrayList;

public class Principal {
	

	public int indexTable;
	Create c = new Create();
	Insert insert = new Insert(this);

	public Base getBase(int i) { // Return the table number i 
		return c.r[i];
	}

	public void general(String s) {
		String m = s.toUpperCase(); // Translate statement in upper case
		String statement = m.trim().replaceAll(" +", " "); // Delete useless spaces
		String[] words = statement.split(" "); // Split statements by space
		if (words[0].equals("CREATE")) { // Test if the statement is a create method
			String k = c.spaceCreate(m); // Replace the statement in a right order if  it is not
			c.create(k);
		} else {
			if (words[0].equals("INSERT")) { // Test if the statement is an insert method
				String j = insert.spaceInsert(statement); // Replace the statement in a right order if  it is not
				insert.insert(j);

			} else {
				if (words[0].equals("STOP")) { 
					System.out.println("End");
				} else {
					System.out.println("Error");
				}
			}
		}

	}





	public static void main(String[] args) {

		Principal p = new Principal();
		String s = "";
		
		  while(!s.equals("STOP")){ System.out.println("To quit write STOP");
		  System.out.println("What is your request ?"); 
		  s = Clavier.lireString(); p.general(s); }
		 
	}
}
