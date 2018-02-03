import data.constants
import scala.util.Random

class Lawn {
  var lawn = Array.ofDim[Int](constants.LAWN_DIMENSION, constants.LAWN_DIMENSION)
  val randomizer = new Random()

  def randomizeLawn(): Unit =  {
    lawn.map(_.transform(getRandomPosition(_)))
  }

  def getRandomPosition(tmp: Int): Int = randomizer.nextInt(2)

  def printLawn(): Unit = {
    for(lawnArry <- lawn) {
      for (values <- lawnArry) {
        print(values)
      }
      println()
    }
  }

  def placeHopper(): Unit = {
    lawn(randomizer.nextInt(constants.LAWN_DIMENSION))(randomizer.nextInt(constants.LAWN_DIMENSION)) = 2

  }
}
