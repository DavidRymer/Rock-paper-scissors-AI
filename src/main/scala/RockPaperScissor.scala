import scala.util.Random

object RockPaperScissor {

  def main(args: Array[String]): Unit = {
    var rockCount = 0
    var paperCount = 0
    var scissorsCount = 0
    var player1Wins = 0
    var player2Wins = 0
    var draws = 0
    var gameEnd = false
    val options = Array("rock", "paper", "scissors")


    def artificialIntelligence(rockCount: Int, paperCount: Int, scissorCount: Int): String = {

      var total = rockCount + scissorsCount + paperCount

      if (total % 4 == 0 && total > 0)
        resetCounters()

      (rockCount, paperCount, scissorCount) match {
        case (a, b, c) if a > b && b >= c => "paper"
        case (a, b, c) if b > c && c >= a => "scissors"
        case (a, b, c) if c > a && a >= b => "rock"
        case _ => Random.shuffle(options.toList).head
      }
    }

    def result(player1Choice: String, player2Choice: String): Unit = {
      (player1Choice, player2Choice) match {
        case ("rock", "scissors") => rockCount += 1
          player1Wins += 1
          println("Player 1 wins \n")
        case ("paper", "rock") => paperCount += 1
          player1Wins += 1
          println("Player 1 wins \n")
        case ("scissors", "paper") => scissorsCount += 1
          player1Wins += 1
          println("Player 1 wins \n")
        case ("rock", "rock") => draws += 1
          println("Draw \n")
        case ("paper", "paper") => draws += 1
          println("Draw \n")
        case ("scissors", "scissors") => draws += 1
          println("Draw \n")
        case ("quit", _) => gameEnd = true
        case _ => player2Wins += 1
          println("Player 2 wins \n")
      }

    }

    def game(): Unit = {
      var userGuess = ""
      var aiGuess = ""

      while (!gameEnd) {
        if (player1Wins + player1Wins > 0)
          println(s"User wins: $player1Wins, AI wins: $player2Wins, Draws: $draws")

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

    def aiVsAi(player1Weight: Int): Unit = {
      var player1Choice = ""
      var player2Choice = ""

      println(s"Welcome to the AI vs AI Rock, Paper," +
        s" Scissors simulation. You have selected AI1 to" +
        s" have an $player1Weight% favour.")

      println("Please Type \"next\" to begin and for each iteration")
      while (!gameEnd) {
        scala.io.StdIn.readLine() match {

          case "next" => player2Choice = randomChoice()
            if (Random.nextInt(100) < player1Weight - 10) {
              player1Choice = win(player2Choice)
            }
            else {
              player1Choice = randomChoice()
            }
            println(s"Player 1 chooses $player1Choice and player 2 chooses $player2Choice")
            result(player1Choice, player2Choice)
            if (player1Wins + player1Wins > 0)
              println(s"AI1 wins: $player1Wins, AI2 wins: $player2Wins, Draws: $draws")

          case "quit" => gameEnd = true
            println("Thank you for playing")

          case _ => println("Please type in \"next\" or \"quit\".")
        }
      }
    }

    def win(opponentChoice: String): String = {
      opponentChoice match {
        case "rock" => "paper"
        case "paper" => "scissors"
        case "scissors" => "rock"
      }
    }

    def randomChoice(): String = {
      val options = Array("rock", "paper", "scissors")
      Random.shuffle(options.toList).head

    }

    def resetCounters(): Unit = {
      paperCount = 0
      scissorsCount = 0
      rockCount = 0
    }

    def play(): Unit = {
      println("Do you want to play of simulate?")
      val choice = scala.io.StdIn.readLine()
      var start = false

      while (!start) {
        choice match {
          case "play" => game()
            start = true
          case "simulate" => println("What favour rating would you like to put on AI 1? (between 0 and 100) \n")
            start = true
            aiVsAi(scala.io.StdIn.readInt())

          case _ => println("Please make a valid choice.")
        }

      }



    }
    play()
  }

}
