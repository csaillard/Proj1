import java.io.*;

public class Clavier {
static BufferedReader input=new BufferedReader(new
InputStreamReader(System.in));
/**
* @return Realise une saisie au clavier et retourne la chaine de caracteres saisie.
*/
public static String lireString() {
try {
return input.readLine();
} catch (Exception e) {//Hui-Yu: what does 'e' mean?
return "";
}
}
/**
* @return Realise une saisie et retourne le premier caractere de cette saisie
* (Retourne Character.MAX_VALUE si la saisie ne comporte pas de caracteres)
*/
public static char lireChar() {
try {
return lireString().charAt(0);//Hui-Yu: Return the first char in inputed string
} catch (Exception e) {
return Character.MAX_VALUE;//Hui-Yu: is it like a DEFAULT VALUE?
}
}
/**
* @return Realise une saisie et retourne l'entier correspondant a cette saisie
* (Retourne Integer.MAX_VALUE si la saisie ne correspond pas a un entier)
*/
public static int lireInt() {
try {
return Integer.parseInt(lireString().trim());//Hui-Yu: the function of ".trim()"?  ".parseInt()"?
} catch (Exception e) {
return Integer.MAX_VALUE;
}
}
/**
* @return Realise une saisie et retourne l'entier long correspondant a cette saisie
* (Retourne Long.MAX_VALUE si la saisie ne correspond pas a un entier long)
*/
public static long lireLong() {
try {
return Long.parseLong(lireString().trim());
} catch (Exception e) {
return Long.MAX_VALUE;
}
}
}
