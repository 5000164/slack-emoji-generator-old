package jp._5000164.slack_emoji_generator

import javafx.application.Application
import javafx.embed.swing.SwingFXUtils
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.canvas.Canvas
import javafx.scene.control.{Button, TextArea}
import javafx.scene.image.WritableImage
import javafx.scene.layout.{HBox, VBox}
import javafx.scene.paint.Color
import javafx.scene.text.{Font, FontSmoothingType}
import javafx.scene.{Scene, SnapshotParameters}
import javafx.stage.{FileChooser, Stage}
import javax.imageio.ImageIO

object Main extends App {
  Application.launch(classOf[Main], args: _*)
}

class Main extends Application {
  override def start(primaryStage: Stage): Unit = {
    val c = new Canvas(128, 128)
    val gc = c.getGraphicsContext2D
    gc.setFill(Color.GRAY)
    gc.setFont(Font.font("Hiragino Sans", 20))
    gc.setFontSmoothingType(FontSmoothingType.LCD)
    gc.fillText("テキスト", 50, 50)

    val b = new Button()
    b.setText("保存")

    val t = new TextArea("test")

    val gb = new Button()
    gb.setText("生成")

    b.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent): Unit = {
        val fc = new FileChooser
        fc.setInitialFileName(s"${t.getText.lines.mkString}.png")
        val f = fc.showSaveDialog(primaryStage)

        if (f != null) {
          val wi = new WritableImage(128, 128)
          val sp = new SnapshotParameters
          sp.setFill(Color.TRANSPARENT)
          c.snapshot(sp, wi)
          val ri = SwingFXUtils.fromFXImage(wi, null)
          ImageIO.write(ri, "png", f)
        }
      }
    })

    gb.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent): Unit = {
        gc.clearRect(0, 0, c.getWidth, c.getHeight)
        gc.fillText(t.getText, 50, 50)
      }
    })

    val l1 = new HBox()
    l1.getChildren.addAll(c, t, gb)

    val l2 = new HBox()
    l2.getChildren.add(b)

    val root = new VBox()
    root.getChildren.addAll(l1, l2)
    val scene = new Scene(root, 600, 300)
    primaryStage.setTitle("Slack Emoji Generator")
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
