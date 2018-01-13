package jp._5000164.slack_emoji_generator

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.{ImageView, WritableImage}
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Stage

object Main extends App {
  Application.launch(classOf[Main], args: _*)
}

class Main extends Application {
  override def start(primaryStage: Stage): Unit = {
    val wi = new WritableImage(300, 300)
    val pw = wi.getPixelWriter
    for {
      x <- 0 until 300
      y <- 0 until 300
    } pw.setColor(x, y, Color.BLACK)
    val imageView = new ImageView(wi)
    val root = new StackPane()
    root.getChildren.add(imageView)
    val scene = new Scene(root, 350, 350)
    primaryStage.setTitle("test")
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
