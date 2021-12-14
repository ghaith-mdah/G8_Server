package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.Item;
import logic.Request;

public class AddItemController extends Application implements Initializable{
public static String status;
    @FXML
    private TextField IDField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField PriceField;
    @FXML
	private Label labelmsg;
    @FXML
    private ComboBox<String> Typecmb;

    @FXML
    void Add(ActionEvent event) {
    	if (IDField.getText().isEmpty()||PriceField.getText().isEmpty() || NameField.getText().isEmpty()
				|| Typecmb.getSelectionModel().getSelectedItem().equals(null))
			labelmsg.setText("You have to fill all Fields");
    	ConnectFormController.chat.accept(new Request("add item to menu	"+SupplierHSController.resturantID,new Item(Integer.parseInt(IDField.getText()),NameField.getText(),Typecmb.getSelectionModel().getSelectedItem(),Double.parseDouble(PriceField.getText()))));
    	if(status.equals("item added"))
		{
		labelmsg.setText(status+" you can now go back to Restaurant Menu Page");
		ConnectFormController.chat.accept(new Request("show items in restaurant",SupplierHSController.resturantID));
	}
		else labelmsg.setText(status);
	}
    
    ObservableList<String> list;

	ArrayList<String> al = new ArrayList<>();// list for combo box

	void setType() {

		al.add("MainMeal");
		al.add("Drinks");
		al.add("FirstMeal");
		al.add("Salad");
		list = FXCollections.observableArrayList(al);
		Typecmb.setItems(list);
	}
    @FXML
    void Cancel(ActionEvent event) throws Exception {
    	 ((Node) event.getSource()).getScene().getWindow().hide();
         RestaurantMenuController rm=new RestaurantMenuController();
     	 Stage primaryStage=new Stage();
         rm.start(primaryStage);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setType();
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		Pane root = loader.load(getClass().getResource("/gui/AddItem.fxml"));
		Scene scene = new Scene(root);			
		primaryStage.setTitle("Add Item");
		primaryStage.setScene(scene);		
		primaryStage.show();
		
	}

}

