package jp._5000164.slack_emoji_generator

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Stage

object Main extends App {
  Application.launch(classOf[Main], args: _*)
}

class Main extends Application {
  override def start(primaryStage: Stage): Unit = {
    val c = new Canvas(300, 300)
    val gc = c.getGraphicsContext2D
    gc.setFill(Color.GRAY)
    gc.fillRect(0, 0, 300, 300)
    val root = new StackPane()
    root.getChildren.add(c)
    val scene = new Scene(root, 350, 350)
    primaryStage.setTitle("test")
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
