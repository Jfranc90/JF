package finalProject;
/*Name: Jessy Francisco
 * CIN: 307259828
 * Course/Section: CS2012 Section 1 and 2
 * Description: This class a pane with a notice
 * of whether the user hit  the boss*/
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Hit 
{
	public Hit()
	{
		StackPane pane = new StackPane();
		Label l1 = new Label("YA SHOT THA BOSS!!!");
		pane.getChildren().add(l1);
		pane.setAlignment(Pos.CENTER);
		Scene s = new Scene(pane,300,300);
		Stage stage = new Stage();
		stage.setScene(s);
		stage.setTitle("HIT!!!");
		stage.show();
	}
}
