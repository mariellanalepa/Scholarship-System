package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class AppFormController {
	
	@FXML private Button actionTarget;
	
	@FXML protected void handleSignInButtonAction(ActionEvent event) {
		actionTarget.setText("Sign in button pressed");
	}

}
