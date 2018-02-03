import data.constants

object Main extends App {

  def initializePopulation(): Vector[Lawn] = Vector.fill(constants.POPULATION_SIZE){new Lawn}



  override def main(args: Array[String]) = {
    val lawnPopulation = initializePopulation()
    lawnPopulation.map(_.randomizeLawn())

    lawnPopulation.map(_.placeHopper())
  }

  def jumpHopper(grassyLawn: Array[Array[Int]]) = {
    for (row <- grassyLawn) {
      for (col <- row) {
        if (grassyLawn(row)(col).equals(2)) {
          return new List[Int](row, col)
        }
      }
    }
  }
}
