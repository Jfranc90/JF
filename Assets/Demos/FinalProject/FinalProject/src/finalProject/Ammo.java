package finalProject;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/*Name: Jessy Francisco
 * CIN: 307259828
 * Course/Section: CS2012 Section 1 and 2
 * Description: This class creates a stage that tells
 * the user they have ran out of ammo.*/
public class Ammo 
{
	public Ammo()
	{
		StackPane pane = new StackPane();
		Label l1 = new Label("OUT OF AMMO");
		pane.getChildren().add(l1);
		pane.setAlignment(Pos.CENTER);
		Scene s = new Scene(pane,300,300);
		Stage stage = new Stage();
		stage.setScene(s);
		stage.setTitle("UH-OH");
		stage.show();
	}
}
