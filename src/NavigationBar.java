
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker;
import javafx.stage.*;

public class NavigationBar extends HBox{
    private static String HTML_ERROR =  //
    "<html>"//
            + "<head> " //
            + "</head> "//
            + "<body> "//
            + "   <h2> ERORR 404 </h2> "//
            + "   <h4>  page not found <h2> "//
            + "   <h5>  it's looking like you may have taken a wrong turn. <h2> "//
            + "</body> "//
            + "</html> "//
;
    String currentAddress;

    public NavigationBar(WebView webView,String homePagaeUrl,boolean goToHomePage,Stage window){
        // set spacing
        this.setSpacing(4);
        WebEngine webEngine = webView.getEngine();

        TextField pageUrl = new TextField();

        Button btBack = new Button("Back");
        Button btReload = new Button("Reload");
        Button btForward = new Button("Forward");
        Button btZoomIn = new Button("Zoom In");
        Button btZoomOut = new Button("Zoom out");
        Button btZoomNormal = new Button("Zoom Normal");
        Button btsrc = new Button("src");

        HBox.setHgrow(pageUrl, Priority.ALWAYS);
            
        pageUrl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event){
                 boolean isHttp = pageUrl.getText().indexOf("http://") != -1?true:false;
                 boolean isHttps = pageUrl.getText().indexOf("https://") != -1?true:false;
                 if(isHttp || isHttps){
                     webEngine.load(pageUrl.getText());
                 }else webEngine.load("http://"+pageUrl.getText());
             }
         });
             
         webEngine.getLoadWorker().stateProperty().addListener((obs,oldvalue,newvalue) -> {
             if(newvalue == Worker.State.SUCCEEDED){
               System.out.println("page has been loaded");
             }else if(newvalue == Worker.State.FAILED){
               webEngine.loadContent(HTML_ERROR);
             }
        });    
    
        webEngine.locationProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov,final String oldvalue,final String newvalue){
                pageUrl.setText(newvalue);
                currentAddress = newvalue;
            }
        });

        btBack.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                webEngine.getHistory().go(-1);
                ObservableList<WebHistory.Entry> entries = webEngine.getHistory().getEntries();
                pageUrl.setText(entries.get(webEngine.getHistory().getCurrentIndex()).getUrl());
            }
        });

        btForward.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                webEngine.getHistory().go(1);
                ObservableList<WebHistory.Entry> entries = webEngine.getHistory().getEntries();
                pageUrl.setText(entries.get(webEngine.getHistory().getCurrentIndex()).getUrl());
            }
        });

        btReload.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                webEngine.reload();
                pageUrl.clear();
                pageUrl.setText(currentAddress);
            }
        });

        btZoomIn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                webView.setZoom(webView.getZoom() + 0.10);
            }
        });

        btZoomOut.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                webView.setZoom(webView.getZoom() - 0.10);
            }
        });

        btZoomNormal.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
                webView.setZoom(1.0);
            }
        });
        
        btsrc.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event){
               showText("Source of " + webEngine.getTitle(), window, (String) webEngine.executeScript("document.documentElement.outerHTML"));
            }
        });
        
        this.getChildren().addAll(btBack,btReload,btForward,pageUrl,btZoomIn,btZoomNormal,btZoomOut,btsrc);
        if(goToHomePage){
            webEngine.load("http://"+homePagaeUrl);
        }
        
    }

    public void showText(String title,Stage window,String text){
        TextArea root = new TextArea(text);
        Scene secondScene = new Scene(root,600,400);
        Stage secondWindow = new Stage();
        secondWindow.setTitle(title);
        secondWindow.setScene(secondScene);
        secondWindow.initOwner(window);
        secondWindow.show();
    }
}
