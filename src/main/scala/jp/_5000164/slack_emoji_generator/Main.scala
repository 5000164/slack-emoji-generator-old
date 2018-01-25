package jp._5000164.slack_emoji_generator

import javafx.application.Application
import javafx.embed.swing.SwingFXUtils
import javafx.event.{ActionEvent, EventHandler}
import javafx.geometry.VPos
import javafx.scene.canvas.Canvas
import javafx.scene.control.{Button, TextArea}
import javafx.scene.image.WritableImage
import javafx.scene.layout.{HBox, VBox}
import javafx.scene.paint.Color
import javafx.scene.text.{Font, FontSmoothingType, TextAlignment}
import javafx.scene.{Scene, SnapshotParameters}
import javafx.stage.{FileChooser, Stage}
import javax.imageio.ImageIO

import scala.util.Random

object Main extends App {
  Application.launch(classOf[Main], args: _*)
}

class Main extends Application {
  override def start(primaryStage: Stage): Unit = {
    val c = new Canvas(128, 128)
    val gc = c.getGraphicsContext2D

    val colorList = Map(
      "Red" -> "F44336",
      "Pink" -> "E91E63",
      "Purple" -> "9C27B0",
      "Deep Purple" -> "673AB7",
      "Indigo" -> "3F51B5",
      "Blue" -> "2196F3",
      "Light Blue" -> "03A9F4",
      "Cyan" -> "00BCD4",
      "Teal" -> "009688",
      "Green" -> "4CAF50",
      "Light Green" -> "8BC34A",
      "Lime" -> "CDDC39",
      "Yellow" -> "FFEB3B",
      "Amber" -> "FFC107",
      "Orange" -> "FF9800",
      "Deep Orange" -> "FF5722",
      "Brown" -> "795548",
      "Grey" -> "9E9E9E",
      "Blue Grey" -> "607D8B"
    )

    gc.setFont(Font.font("Hiragino Sans", 64))
    gc.setFontSmoothingType(FontSmoothingType.LCD)
    gc.setTextAlign(TextAlignment.CENTER)
    gc.setTextBaseline(VPos.CENTER)

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

        def getContent(content: List[Option[Char]], remains: List[Char]): List[Option[Char]] = remains match {
          case head :: tail => getContent(Some(head) :: content, tail)
          case Nil => content.reverse
        }

        val content = getContent(List(), t.getText.take(4).toList)

        val color = Random.shuffle(colorList.values).head
        gc.setFill(Color.web(s"0x$color"))

        gc.fillText(content.head.getOrElse("").toString, 32, 32)
        gc.fillText(content(1).getOrElse("").toString, 96, 32)
        gc.fillText(content(2).getOrElse("").toString, 32, 96)
        gc.fillText(content(3).getOrElse("").toString, 96, 96)
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
