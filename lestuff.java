//import java packages
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//Java class Two_D_Array
public class Two_D_Array {
// main() method
public static void main(String[] args) throws IOException {
// instance of Scanner class
Scanner scanner = new Scanner(System.in);
// these variables will store rows and columns of 2D array
int two_D_rows, two_D_Columns;
// using while loop for number of rows
while (true) {
// prompting user for number of rows of 2D array
System.out.print("Enter number of rows for 2D array:");
two_D_rows = scanner.nextInt();// reading rows
// checking number of 2D array rows
if (two_D_rows < 5 || two_D_rows > 10) {
// when two_d_rows are less than 5 or greater than 10 then
// show error message
System.out.println("2D array rows should be between 5 to 10 inclusive.");
} else {
// when valid rows are entered then break the loop
break;
}
}
// using while loop for number of columns
while (true) {
// prompting user for number of columns of 2D array
System.out.print("Enter number of columns for 2D array:");
two_D_Columns = scanner.nextInt();// reading columns
// checking number of 2D array columns
if (two_D_Columns < 5 || two_D_Columns > 10) {
// when two_d_Columns are less than 5 or greater than 10 then
// show error message
System.out.println("2D array columns should be between 5 to 10 inclusive.");
} else {
// when valid columns are entered then break the loop
break;
}
}
// create an array with two_D_rows and two_D_Columns
int[][] two_D_arr = new int[two_D_rows][two_D_Columns];
// object of Random class
Random rd = new Random();
// using for loop for number of rows
for (int tr = 0; tr < two_D_rows; tr++) {
// this for loop is used for number of columns
for (int tc = 0; tc < two_D_Columns; tc++) {
// generating a random number between 100 to 999 inclusive
// and store in the array
two_D_arr[tr][tc] = rd.nextInt(900) + 100;
}
}
// printing 2D array
System.out.println("Two Dimensional Array :");
// using for loop for number of rows
for (int tr = 0; tr < two_D_rows; tr++) {
// this for loop is used for number of columns
for (int tc = 0; tc < two_D_Columns; tc++) {
//print the array elements
System.out.print(two_D_arr[tr][tc]+" ");
}
System.out.println();//used for new line
}
//creating object of FileWriter to write to the file
FileWriter randomWriter = new FileWriter("randomTable.txt");
// printing 2D array
randomWriter.write("Two Dimensional Array :\n");
// using for loop for number of rows
for (int tr = 0; tr < two_D_rows; tr++) {
// this for loop is used for number of columns
for (int tc = 0; tc < two_D_Columns; tc++) {
//write the array elements to the file
randomWriter.write(two_D_arr[tr][tc]+" ");
}
randomWriter.write("\n");//used for new line
}
//this line is used to close the file
randomWriter.close();
}

}
