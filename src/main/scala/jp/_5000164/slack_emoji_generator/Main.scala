package jp._5000164.slack_emoji_generator

import javafx.application.Application
import javafx.embed.swing.SwingFXUtils
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.image.WritableImage
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.scene.text.Font
import javafx.stage.{FileChooser, Stage}
import javax.imageio.ImageIO

object Main extends App {
  Application.launch(classOf[Main], args: _*)
}

class Main extends Application {
  override def start(primaryStage: Stage): Unit = {
    val c = new Canvas(300, 300)
    val gc = c.getGraphicsContext2D
    gc.setFill(Color.GRAY)
    gc.setFont(Font.font("Hiragino Sans", 20))
    gc.fillText("テキスト", 50, 50)

    val fc = new FileChooser
    val f = fc.showSaveDialog(primaryStage)

    val wi = new WritableImage(300, 300)
    c.snapshot(null, wi)
    val ri = SwingFXUtils.fromFXImage(wi, null)
    ImageIO.write(ri, "png", f)

    val root = new StackPane()
    root.getChildren.add(c)
    val scene = new Scene(root, 350, 350)
    primaryStage.setTitle("test")
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
