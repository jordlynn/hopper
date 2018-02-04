import data.constants
import scala.util.Random

class Lawn {
  var lawn = Array.ofDim[Int](constants.LAWN_DIMENSION, constants.LAWN_DIMENSION)
  val randomizer = new Random()
  var hopperRow: Int = 0
  var hopperCol: Int = 0
  var fitness: Int = 0
  var lawnFilled: Int = 0

  def randomizeLawn(): Unit =  {
      lawn.map(_.transform(getRandomPosition(_)))
  }

  def getRandomPosition(tmp: Int): Int = {
    if (lawnFilled < constants.MAX_LAWN_FUEL) {
      if (randomizer.nextInt(2) == 0) {
        lawnFilled += 1
        1
      }
      0
    } else {
      0
    }
  }

  def printLawn(): Unit = {
    for(lawnArry <- lawn) {
      for (values <- lawnArry) {
        print(values)
      }
      println()
    }
    println("Hoper at: " + hopperRow + " " + hopperCol)
    println("Fitness: " + fitness)
    println("--------")
  }

  def printFitness(): Unit = {
    println("my fit: " + fitness)
  }

  def placeHopper(): Unit = {
    hopperRow = randomizer.nextInt(constants.LAWN_DIMENSION)
    hopperCol = randomizer.nextInt(constants.LAWN_DIMENSION)
  }

  def findHopperSpot(row: Int, col: Int): Boolean = {
    if(lawn(row)(col).equals(1)) {
      hopperRow = row
      hopperCol = col
      false
    }
    else true
  }

  def jumpHopper(): Unit = {
    hopperRow = hopperRow - randomizer.nextInt(constants.MAX_JUMP_DISTANCE)
    hopperCol = hopperCol - randomizer.nextInt(constants.MAX_JUMP_DISTANCE)
  }

  def evaluateFitness(): Unit = {
    if(hopperRow < 0 || hopperCol < 0) {
      fitness += constants.OKAY
    } else if(lawn(hopperRow)(hopperCol).equals(1)) {
      fitness += constants.REALLY_GOOD
    } else if(lawn(hopperRow)(hopperCol).equals(0)) {
      fitness += constants.BAD
    }
  }
}
