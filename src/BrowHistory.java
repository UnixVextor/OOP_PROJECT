import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.scene.web.WebHistory.Entry;
import javafx.util.Callback;

public class BrowHistory extends HBox{
    public BrowHistory(WebView webView){
        WebHistory history = webView.getEngine().getHistory();
        Label label = new Label("History : ");

        ComboBox<Entry> historyList = new ComboBox<>();
        historyList.setPrefWidth(250);
        historyList.setItems(history.getEntries());
        
        historyList.setCellFactory(new Callback<ListView<WebHistory.Entry>, ListCell<WebHistory.Entry>>(){
            @Override
            public ListCell<WebHistory.Entry> call(ListView<WebHistory.Entry> list){
                ListCell<Entry> cell = new ListCell<Entry>(){
                    @Override
                    public void updateItem(Entry item, boolean empty){
                        super.updateItem(item, empty);
                        if(empty){
                            this.setText(null);
                            this.setGraphic(null);
                        }
                        else{
                            String pageTitle = item.getTitle();
                            this.setText(pageTitle);
                        }
                    }
                };
                return cell;                
            } 
        });

        historyList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                int currentIndex = history.getCurrentIndex();
                Entry selectedEntry = historyList.getValue();

                int selectedIndex = historyList.getItems().indexOf(selectedEntry);
                int offset = selectedIndex - currentIndex;
                history.go(offset);
            }
        });

        this.getChildren().addAll(label,historyList);
    }
}
