import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
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

      ProgressBar progress = new ProgressBar(0);
      progress.setStyle("-fx-accent: #00FF00;");
      progress.progressProperty().bind(webEngine.getLoadWorker().progressProperty());
      progress.prefWidthProperty().bind(window.widthProperty());
      progress.setPrefHeight(12);
      TilePane progreesTile = new TilePane();
      progreesTile.getChildren().add(progress);

      VBox root = new VBox(5);
      root.getChildren().addAll(navigationBar,progreesTile,webView);
      
      Scene scene = new Scene(root,600,600);

      window.setScene(scene);
      window.show();
    }
}
