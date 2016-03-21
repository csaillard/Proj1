import java.util.ArrayList;


public class Data {

	public ArrayList<String> data; //Hui-Yu: ArrayList is a special datatype of Java. It's like <vector> of c++, but support multi-type object 
	public String name;//Hui-Yu: Attribute name
	public String type;//Hui-Yu: Attribute data_type
	public Boolean key;//Hui-Yu: IsPrimaryKey
	public int numberChar;//Hui-Yu: Max Number of Char, if data_type is VARCHAR
	
	public Data(){   //Hui-Yu: Constructor
		this.data= new ArrayList<String>();
		//Hui-Yu: In c++, you don't have to NEW a vector. A vector 'data' existed when the oject 'Data' was created. Unless 'data' is a pointer. 
		//Hui-Yu: So <ArrayList> is more like a pointer than a real object (which is, reference in Java)
		this.name = " ";
		this.type = "STRING";
		this.key = false;
		this.numberChar = -1;
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isKey() {
		return key;
	}

	public void setKey(Boolean key) {
		this.key = key;
	}
	
	public int getNumberChar() {
		return numberChar;
	}

	public void setNumberChar(int numberChar) {
		this.numberChar = numberChar;
	}
	public String toString() {
		return "Data [data=" + data + ", name=" + name + ", type=" + type + ", key=" + key + ", numberChar="
				+ numberChar + "]";
	}
}
