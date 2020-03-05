package finalProject;
/*Name: Jessy Francisco
 * CIN: 307259828
 * Course/Section: CS2012 Section 1 and 2
 * Description: This class is the main for this project and opens the starting menu
 * with buttons that direct the user to the game based on the number of rows and columns.*/
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MainTesterFinal extends Application 
{

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		GridPane grid  = new GridPane();
		int columns = 3;
		int rows = 2;
		for (int i = 0; i < columns; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / columns);
            grid.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < rows; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / rows);
            grid.getRowConstraints().add(rowConst);         
        }
		grid.setGridLinesVisible(false);
		grid.setPadding(new Insets(50,5,5,60));
		Button b1 = new Button("5x5");
		b1.setOnAction(e ->
		{
			String[] arr = b1.getText().split("x");
			int col = Integer.parseInt(arr[0]);
			int row = Integer.parseInt(arr[1]);
			SecondPane pane2 = new SecondPane(row,col);
		});
		Button b2 = new Button("7x7");
		b2.setOnAction(e ->
		{
			String[] arr = b2.getText().split("x");
			int col = Integer.parseInt(arr[0]);
			int row = Integer.parseInt(arr[1]);
			SecondPane pane2 = new SecondPane(row,col);
		});
		
		Button b3 = new Button("10x7");
		b3.setOnAction(e ->
		{
			String[] arr = b3.getText().split("x");
			int col = Integer.parseInt(arr[0]);
			int row = Integer.parseInt(arr[1]);
			SecondPane pane2 = new SecondPane(row,col);
		});
		grid.add(b1, 0, 1);
		grid.add(b2, 1, 1);
		grid.add(b3, 2, 1);
		Scene scene = new Scene(grid,500,500);
		primaryStage.setTitle("Title");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
