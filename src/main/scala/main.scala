import data.{Imagur, constants}

import scala.util.Random

object Main extends App {

  def initializePopulation(): Vector[Lawn] = Vector.fill(constants.POPULATION_SIZE){new Lawn}


  override def main(args: Array[String]) = {
    val lawnPopulation = initializePopulation()

    Imagur.buildPhoto(lawnPopulation(0).lawn)

    val finalPop = runEvo(lawnPopulation)
    val lastPop = sortPopulation(finalPop)

    lastPop.map(_.printFitness())
    Imagur.buildPhoto(lastPop(0).lawn)
  }

  def runEvo(population: Vector[Lawn]): Vector[Lawn] = {
    var nextGeneration = initializePopulation()
    for(i <- 0 to constants.MAX_GENERATION) {
      population.map(runSwarm(_, constants.MAX_SWARM_SIZE))
      sortPopulation(population)

      nextGeneration.map(_.lawn = generateOffspring(
        population(Random.nextInt(constants.TOP_PERFORMERS_TO_TAKE)),
        population(Random.nextInt(constants.TOP_PERFORMERS_TO_TAKE)))
      )

      nextGeneration.map(_.placeHopper())
      nextGeneration.map(_.jumpHopper())

      nextGeneration.map(_.evaluateFitness())
      nextGeneration = sortPopulation(nextGeneration)

      if(i % (constants.LAWN_DIMENSION/4).toInt == 0) {
        println("Progress " + i)
        Imagur.buildPhoto(nextGeneration(0).lawn)
      }
    }
    nextGeneration
  }

  def runSwarm(grassyKnoll: Lawn, swarmSize: Int) = {
    for(i <- 0 to swarmSize) {
      grassyKnoll.placeHopper()
      grassyKnoll.jumpHopper()

      grassyKnoll.evaluateFitness()
    }
  }

  def sortPopulation(pop: Vector[Lawn]): Vector[Lawn] =  pop.sortWith(_.fitness > _.fitness)

  def generateOffspring(x: Lawn, y: Lawn): Array[Array[Int]] = {
    var babyLawn = new Lawn

    for(row <- 0 to constants.LAWN_DIMENSION -1; col <- 0 to constants.LAWN_DIMENSION -1) {
        if (x.lawn(row)(col).equals(1)
          && y.lawn(row)(col).equals(1)
          && babyLawn.lawnFilled < constants.MAX_LAWN_FUEL) {
          // if both parents AND fuel left
          babyLawn.lawn(row)(col) = 1
        } else if (x.lawn(row)(col).equals(1) || y.lawn(row)(col).equals(0)) {
          // random chance to inherit, regardless of fuel left
          babyLawn.lawn(row)(col) = mutate()
        } else {
          babyLawn.lawn(row)(col) = 0
        }
      }
    babyLawn.lawn
  }

  def mutate(): Int = {
    if (Random.nextInt(100) < constants.MUTATION_RATE) {
      1
    } else {
      0
    }
  }
}
