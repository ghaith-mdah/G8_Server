package gui;
//ipad comment
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Request;
import logic.User;

public class BMScreenController extends Application implements Initializable{
	
	static User user;
	@FXML
    private Button logout;

    @FXML
    private Button EditMenu;

    @FXML
    private Button sendreport;

    @FXML
    private Button ViewReports;
    
    @FXML
    private Label labelhello;
    
    @FXML
    private Button RegAcc;

    @FXML
    private Button EditUser;

    @FXML
    private Button neworder;

    @FXML
    private Button ConfimEmp;

    @FXML
    void EditUser(ActionEvent event) {

    }

    @FXML
    void EmpConfirmation(ActionEvent event) {

    }

    @FXML
    void LogOut(ActionEvent event) throws Exception {
    ConnectFormController.chat.accept(new Request("Log out",user));
    ((Node) event.getSource()).getScene().getWindow().hide();
    LoginScreenController login=new LoginScreenController();
	Stage primaryStage=new Stage();
    login.start(primaryStage);
    }

    @FXML
    void OpenMenu(ActionEvent event) {

    }

    @FXML
    void ReportList(ActionEvent event) {

    }

    @FXML
    void SendReport(ActionEvent event) {

    }

    @FXML
    void StartNewOrder(ActionEvent event) {

    }

    @FXML
    void StartRegAcc(ActionEvent event) {

    }

    public void start(Stage primaryStage) throws Exception {	
    	FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/gui/BMScreen.fxml"));
		Scene scene = new Scene(root);			
		primaryStage.setTitle("BM");
		primaryStage.setScene(scene);		
		primaryStage.show();	 	   
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelhello.setText("Hello "+ user.getFname()+" "+user.getLnaem());
		
	}
}
