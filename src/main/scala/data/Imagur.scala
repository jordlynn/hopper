package data

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import constants.LAWN_DIMENSION

object Imagur {
  val img = new BufferedImage(LAWN_DIMENSION, LAWN_DIMENSION, BufferedImage.TYPE_INT_BGR)
  var counter = 0

  def buildPhoto(lawnToPhoto: Array[Array[Int]]): Unit = {
    for (x <- 0 until LAWN_DIMENSION; y <- 0 until LAWN_DIMENSION) {
      if (lawnToPhoto(x)(y).equals(1)) {
        img.setRGB(x, y, 0x008000)
      }
      if (lawnToPhoto(x)(y).equals(0)) {
        img.setRGB(x, y, 0xFFFFFF)
      }
    }
    writeImage(img)
  }

  def writeImage(img: BufferedImage): Unit = {
    counter += 1
    val fileName = "Img_" + counter.toString + ".png"
    ImageIO.write(img, "png", new File(fileName))

  }


}
