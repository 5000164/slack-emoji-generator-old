package jp._5000164.slack_emoji_generator

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.{Image, ImageView}
import javafx.scene.layout.StackPane
import javafx.stage.Stage

object Main extends App {
  Application.launch(classOf[Main], args: _*)
}

class Main extends Application {
  override def start(primaryStage: Stage): Unit = {
    val image = new Image(getClass.getResource("/test.png").toString)
    val imageView = new ImageView(image)
    val root = new StackPane()
    root.getChildren.add(imageView)
    val scene = new Scene(root, 350, 350)
    primaryStage.setTitle("test")
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
