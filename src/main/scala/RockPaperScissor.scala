import scala.util.Random

object RockPaperScissor {

  def main(args: Array[String]): Unit = {
    var rockCount = 0
    var paperCount = 0
    var scissorsCount = 0
    var userWins = 0
    var aiWins = 0
    var draws = 0
    var gameEnd = false



    def artificialIntelligence(rockCount: Int, paperCount: Int, scissorCount: Int): String = {
      val options = Array("rock", "paper", "scissors")
      var total = rockCount + scissorsCount + paperCount

      if (total%4 == 0 && total > 0)
        resetCounters()

        (rockCount, paperCount, scissorCount) match {
          case (a, b, c) if a > b && b >= c => "paper"
          case (a, b, c) if b > c && c >= a => "scissors"
          case (a, b, c) if c > a && a >= b => "rock"
          case _ => Random.shuffle(options.toList).head
        }




    }

    def result(userGuess: String, aiGuess: String): Unit = {
      (userGuess, aiGuess) match {
        case ("rock", "scissors") => rockCount += 1
          userWins += 1
          println("User wins \n")
        case ("paper", "rock") => paperCount += 1
          userWins += 1
          println("User wins \n")
        case ("scissors", "paper") => scissorsCount += 1
          userWins += 1
          println("User wins \n")
        case ("rock", "rock") => rockCount += 1
          draws += 1
          println("Draw \n")
        case ("paper", "paper") => paperCount += 1
          draws += 1
          println("Draw \n")
        case ("scissors", "scissors") => scissorsCount += 1
          draws += 1
          println("Draw \n")
        case ("quit", _) => gameEnd = true
        case _ => aiWins += 1
          println("AI wins \n")
      }

    }

      def game(): Unit = {
        var userGuess = ""
        var aiGuess = ""

        while (!gameEnd) {
          println(s"User wins: ${userWins}, AI wins: ${aiWins}, Draws: ${draws}")
          println("User please choose: rock, paper or scissors")
          userGuess = scala.io.StdIn.readLine()

          if (!userGuess.equals("quit")) {
            aiGuess = artificialIntelligence(rockCount, paperCount, scissorsCount)
            println("The ai has chosen: " + aiGuess)
          }
          else {
            println("Thank you for playing.")
          }
          result(userGuess, aiGuess)

        }

      }

    def resetCounters(): Unit = {
      paperCount = 0
      scissorsCount = 0
      rockCount = 0
    }

    game()


  }

}
