package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Request;
import logic.User;

public class SupplierHSController extends Application implements Initializable{
	static User user;
	
	public static String resturantName;
	
	public static int resturantID;
    
	@FXML
    private Button orderlistbutton;

    @FXML
    private Label labelhello;

    @FXML
    private Label labelsupplierfor;
    
    @FXML
    private Button NewOrderButton;

    @FXML
    private Button editmenubutton;

    @FXML
    private Label managername;

    @FXML
    void EditMenuBtn(ActionEvent event) throws Exception {
    	RestaurantMenuController.ResName=resturantName;
    	ConnectFormController.chat.accept(new Request("show items in restaurant",resturantID));
        ((Node) event.getSource()).getScene().getWindow().hide();
        RestaurantMenuController rm=new RestaurantMenuController();
    	Stage primaryStage=new Stage();
        rm.start(primaryStage);
    }

    @FXML
    void NewOrderBtn(ActionEvent event) {

    }

    @FXML
    void orderListbtn(ActionEvent event) {

    }
    @FXML
    void LogOut(ActionEvent event) throws Exception {
    ConnectFormController.chat.accept(new Request("Log out",user));
    ((Node) event.getSource()).getScene().getWindow().hide();
    LoginScreenController login=new LoginScreenController();
	Stage primaryStage=new Stage();
    login.start(primaryStage);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/gui/SupplierHS.fxml"));
		Scene scene = new Scene(root);			
		primaryStage.setTitle("Supplier");
		primaryStage.setScene(scene);		
		primaryStage.show();	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		labelhello.setText("Hello "+ user.getFname()+" "+user.getLnaem());
		labelsupplierfor.setText("supplier of "+resturantName);
	}

}
