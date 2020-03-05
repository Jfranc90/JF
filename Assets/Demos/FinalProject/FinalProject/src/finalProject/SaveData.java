package finalProject;
/*Name: Jessy Francisco
 * CIN: 307259828
 * Course/Section: CS2012 Section 1 and 2
 * Description: This class creates an object
 * that serializes data to be saved or loaded*/
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveData implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String[][] arr;
    private int setRows, setCols, setSelfRows, setSelfCols;
    
    public String[][] getArr()
    {
    	return this.arr;
    }
	
    public void setArr(String[][] arr)
    {
    	this.arr = arr;
    }
    
    public int getSetRows() {
		return setRows;
	}

	public void setSetRows(int setRows) {
		this.setRows = setRows;
	}

	public int getSetCols() {
		return setCols;
	}

	public void setSetCols(int setCols) {
		this.setCols = setCols;
	}

	public int getSetSelfRows() {
		return setSelfRows;
	}

	public void setSetSelfRows(int setSelfRows) {
		this.setSelfRows = setSelfRows;
	}

	public int getSetSelfCols() {
		return setSelfCols;
	}

	public void setSetSelfCols(int setSelfCols) {
		this.setSelfCols = setSelfCols;
	}


	
}
