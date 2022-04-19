
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
        btBack.setDisable(true);
        Button btReload = new Button("Reload");
        Button btForward = new Button("Forward");
        btForward.setDisable(true);
        Button btZoomIn = new Button("Zoom In");
        Button btZoomOut = new Button("Zoom out");
        Button btZoomNormal = new Button("Zoom Normal");


        // Set on Action each button or set Event
        HBox.setHgrow(pageUrl, Priority.ALWAYS);
            
        pageUrl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
             public void handle(ActionEvent event){
                 boolean isHttp = pageUrl.getText().indexOf("http://") != -1?true:false;
                 boolean isHttps = pageUrl.getText().indexOf("https://") != -1?true:false;
                 boolean hasCom = pageUrl.getText().indexOf(".com") != -1 ? true:false;
                 if((isHttp || isHttps) && hasCom){
                     webEngine.load(pageUrl.getText());
                 }else if((!isHttp || !isHttps) && hasCom) webEngine.load("http://"+pageUrl.getText());
                 else webEngine.load("https://www.google.com/search?q=" + pageUrl.getText()); 
             }
         });
             
         webEngine.getLoadWorker().stateProperty().addListener((obs,oldvalue,newvalue) -> {
             if(newvalue == Worker.State.SUCCEEDED){
               //System.out.println("page has been loaded");
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
        
        webEngine.getHistory().currentIndexProperty().addListener(new ChangeListener<Number>()
        {
            public void changed(ObservableValue<? extends Number> ov,
                    final Number oldvalue, final Number newvalue) 
            {
                int currentIndex = newvalue.intValue();
                System.out.print(currentIndex + " "); 
                System.out.println(webEngine.getHistory().getEntries().size());
                if (currentIndex <= 0) 
                {
                    btBack.setDisable(true);
                } 
                else
                {
                    btBack.setDisable(false);
                }
                 
                if (currentIndex == webEngine.getHistory().getEntries().size() - 1) 
                {
                    btForward.setDisable(true);
                } 
                else
                {
                    btForward.setDisable(false);
                }
            }
        });     
 

        // add all element to pane
        this.getChildren().addAll(btBack,btReload,btForward,pageUrl,btZoomIn,btZoomNormal,btZoomOut);
        if(goToHomePage){
            webEngine.load("http://"+homePagaeUrl);
        }
        
    }

}
