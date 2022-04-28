
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
                    + " <h2 style=\"color:#FF8C32;\">  ERROR 404 </h2> "//
                    + " <h3 style=\"color:#FF8C32;\">  page not found </h2> "//
                    + " <h4 style=\"color:#FF8C32;\">  it's looking like you may have taken a wrong turn. </h2> "//
            + "</body> "//
            + "</html> "//
;
    String currentAddress;

    public NavigationBar(WebView webView,String homePagaeUrl,boolean goToHomePage,Stage window){
        // set spacing
        this.setSpacing(4);
        WebEngine webEngine = webView.getEngine();
        TextField pageUrl = new TextField();
        pageUrl.setStyle("-fx-background-color:#37488A;-fx-text-fill: #FF8C32;-fx-font-weight: bold;");

        Image Img1 = new Image("image/Back.png");
        ImageView View1 = new ImageView(Img1);
        Button btBack = new Button(); //Back
        View1.setFitHeight(20);
        View1.setPreserveRatio(true);
        btBack.setGraphic(View1);
        btBack.setDisable(true);
        btBack.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
        btBack.setCursor(Cursor.HAND);
        btBack.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btBack.setStyle("-fx-background-color: #232D57;-fx-background-radius: 45;");
            }
        });
        btBack.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btBack.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
            }
        });

        Image Img2 = new Image("image/Forward.png");
        ImageView View2 = new ImageView(Img2);
        Button btForward = new Button(); //Forward
        View2.setFitHeight(20);
        View2.setPreserveRatio(true);
        btForward.setGraphic(View2);
        btForward.setDisable(true);
        btForward.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
        btForward.setCursor(Cursor.HAND);
        btForward.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btForward.setStyle("-fx-background-color: #232D57;-fx-background-radius: 45;");
            }
        });
        btForward.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btForward.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
            }
        });

        Image Img3 = new Image("image/Reload.png");
        ImageView View3 = new ImageView(Img3);
        Button btReload = new Button(); //Reload
        View3.setFitHeight(20);
        View3.setPreserveRatio(true);
        btReload.setGraphic(View3);
        btReload.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
        btReload.setCursor(Cursor.HAND);
        btReload.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btReload.setStyle("-fx-background-color: #232D57;-fx-background-radius: 45;");
            }
        });
        btReload.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btReload.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
            }
        });

        Image Img4 = new Image("image/ZoomIn.png");
        ImageView View4 = new ImageView(Img4);
        Button btZoomIn = new Button(); //ZoomIn
        View4.setFitHeight(20);
        View4.setPreserveRatio(true);
        btZoomIn.setGraphic(View4);
        btZoomIn.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
        btZoomIn.setCursor(Cursor.HAND);
        btZoomIn.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btZoomIn.setStyle("-fx-background-color: #232D57;-fx-background-radius: 45;");
            }
        });
        btZoomIn.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btZoomIn.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
            }
        });

        Image Img5 = new Image("image/ZoomNormal.png");
        ImageView View5 = new ImageView(Img5);
        Button btZoomNormal = new Button(); //ZoomNormal
        View5.setFitHeight(20);
        View5.setPreserveRatio(true);
        btZoomNormal.setGraphic(View5);
        btZoomNormal.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
        btZoomNormal.setCursor(Cursor.HAND);
        btZoomNormal.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btZoomNormal.setStyle("-fx-background-color: #232D57;-fx-background-radius: 45;");
            }
        });
        btZoomNormal.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btZoomNormal.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
            }
        });

        Image Img6 = new Image("image/ZoomOut.png");
        ImageView View6 = new ImageView(Img6);
        Button btZoomOut = new Button(); //ZoomOut
        View6.setFitHeight(20);
        View6.setPreserveRatio(true);
        btZoomOut.setGraphic(View6);
        btZoomOut.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
        btZoomOut.setCursor(Cursor.HAND);
        btZoomOut.onMouseMovedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btZoomOut.setStyle("-fx-background-color: #232D57;-fx-background-radius: 45;");
            }
        });
        btZoomOut.onMouseExitedProperty().set(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btZoomOut.setStyle("-fx-background-color: #06113C;-fx-background-radius: 45;");
            }
        });

        
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
