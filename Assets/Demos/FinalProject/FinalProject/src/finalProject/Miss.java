package finalProject;
/*Name: Jessy Francisco
 * CIN: 307259828
 * Course/Section: CS2012 Section 1 and 2
 * Description: This class creates an a pane
 * that tells the user they missed the boss.*/
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Miss 
{
	public Miss()
	{
		StackPane pane = new StackPane();
		Label l1 = new Label("YA MISSED YOUR SHOT!");
		pane.getChildren().add(l1);
		pane.setAlignment(Pos.CENTER);
		Scene s = new Scene(pane,300,300);
		Stage stage = new Stage();
		stage.setScene(s);
		stage.setTitle("DANG!");
		stage.show();
	}
}
