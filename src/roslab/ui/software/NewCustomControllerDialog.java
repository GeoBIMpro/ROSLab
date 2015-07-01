package roslab.ui.software;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import roslab.ROSLabController;
import roslab.model.software.ROSNode;
import roslab.model.general.Node;

@SuppressWarnings("deprecation")
public class NewCustomControllerDialog implements Initializable {
	@FXML
    private TextField nameField;
    
    @FXML
    private TextField rateField;

    private Stage dialogStage;
    private boolean addClicked = false;

    private ROSLabController controller;

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Returns true if the user clicked Add, false otherwise.
     *
     * @return
     */
    public boolean isAddClicked() {
        return addClicked;
    }

    /**
     * Called when the user clicks add.
     */
    @FXML
    private void handleAdd() {
        if (isInputValid() && controller != null) {
            ROSNode n = new ROSNode(nameField.getText(), rateField.getText());
            n.addAnnotation("user-defined", "true");
        	n.addAnnotation("custom-type", "controller");
            controller.addLibraryNode(n);
            addClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";
    	for(Node n: controller.getSWLibrary().getNodes()) {
    		if(n.getName().toLowerCase().equals(nameField.getText().toLowerCase())) {
                errorMessage += "Node name already exists in library!\n";    			
    		}
    	}
    	if (nameField.getText().matches("^.*\\s+.*$")) {
    		errorMessage += "Name must not contain whitespace!\n";
    	}
        if (nameField.getText() == null || nameField.getText().equals("")) {
            errorMessage += "No name given!\n";
        }
        if (rateField.getText() == null || rateField.getText().equals("")) {
        	errorMessage += "No rate given!\n";
        } else {
            try {
            	Integer.parseInt(rateField.getText());
            } catch(NumberFormatException e) {
            	errorMessage += "Rate must be an integer!\n";
            }        	
        }
        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // Show the error message
            Dialogs.create().owner(dialogStage).title("Invalid Fields").masthead("Please correct invalid fields").message(errorMessage).showError();
            return false;
        }
    }

    public void setRLController(ROSLabController rosLabController) {
        this.controller = rosLabController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
