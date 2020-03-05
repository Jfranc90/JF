package finalProject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/*Name: Jessy Francisco
 * CIN: 307259828
 * Course/Section: CS2012 Section 1 and 2
 * Description: This class creates a pane that tells 
 * the user there is a  trap nearby.*/
public class TrapNotice 
{
	public TrapNotice()
	{
		StackPane pane = new StackPane();
		Label l1 = new Label("You feel the presence of a TRAP nearby....");
		pane.getChildren().add(l1);
		pane.setAlignment(Pos.CENTER);
		Scene s = new Scene(pane,300,300);
		Stage stage = new Stage();
		stage.setScene(s);
		stage.setTitle("WARNING!!!");
		stage.show();
	}
}
