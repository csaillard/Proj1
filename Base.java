import java.util.ArrayList;
import java.util.Arrays;


public class Base {
	public Data[] Attribut;
	public String name; //Hui-Yu: Table name
	public int nbAttributs; //Hui-Yu: number of Attribute(or it's more like INDEX of attribute?)
	
	public Base(){
		this.Attribut = new Data[5]; //Hui-Yu: "Data" is like a container of one Attribute
		for(int i=0; i<5; i++){
			this.Attribut[i] = new Data();
		}
		this.name = " ";
		this.nbAttributs = -1;
	}
	 public void setname(String s){
		 this.name=s;
	 }
	 
	 public void setnbAttributs(int i){
		 this.nbAttributs=i;
	 }
	 
	 public String getname(){
		 return this.name;
	 }
	 
	 public int getnbAttributs(){
		 return this.nbAttributs;
	 }
	 @Override  //Hui-Yu: what does that mean?
		public String toString() {  //Hui-Yu: It's for debugging
			String string ="";
			return "Base [name=" + name + ", nbAttributs= " + nbAttributs + ", Attribut= " + Arrays.toString(Attribut) +"]";
		}
}
