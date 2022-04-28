import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception {
      WebView webView = new WebView();
      WebEngine webEngine = webView.getEngine();
      webEngine.titleProperty().addListener(new ChangeListener<String>()
      {
          public void changed(ObservableValue<? extends String> ov,
                  final String oldvalue, final String newvalue) 
          {
              window.setTitle(newvalue);
          }
      });

      webView.prefHeightProperty().bind(window.heightProperty());
      webView.prefWidthProperty().bind(window.widthProperty());
      String homePageUrl = "www.google.com";

      NavigationBar navigationBar = new NavigationBar(webView, homePageUrl, true,window);
      navigationBar.setStyle("-fx-padding:10 0 5 0;-fx-background-color: #06113C;");
      ProgressBar progress = new ProgressBar(0);
      progress.setStyle("-fx-text-box-border: #06113C;-fx-control-inner-background: #06113C;-fx-accent: #FF8C32;-fx-background-color: #06113C;");
      progress.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
      progress.prefWidthProperty().bind(window.widthProperty());
      progress.setPrefHeight(12);
      TilePane progreesTile = new TilePane();
      progreesTile.getChildren().add(progress);
      
      VBox root = new VBox();
      root.getChildren().addAll(navigationBar,progreesTile,webView);

      Scene scene = new Scene(root,600,600);
      window.getIcons().add(new Image("D:/OOP_pr/OOP_PROJECT/image/Star.png"));
      window.setScene(scene);
      window.show();
    }
}
