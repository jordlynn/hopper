package data

object constants {
  val LAWN_DIMENSION = 250
  // Max jump is a magnitude of the lawn dimension. When we jump our question is
  // jump = currentPosition - (random.nextInt(magnitude))
  // giving us the ability to move forward/backward up/down
  // using positive and negative numbers.
  val MAX_JUMP_DISTANCE = (((LAWN_DIMENSION / 3).toInt))
  val POPULATION_SIZE = 30
  val MAX_LAWN_FUEL = (math.pow(LAWN_DIMENSION, 2) / 2)
  val REALLY_GOOD = 3
  val OKAY = 0
  val BAD = -1

  val TOP_PERFORMERS_TO_TAKE = (POPULATION_SIZE / 4).toInt
  val MAX_GENERATION = 1000

  val MAX_SWARM_SIZE = 500
  val MUTATION_RATE = 3
}
