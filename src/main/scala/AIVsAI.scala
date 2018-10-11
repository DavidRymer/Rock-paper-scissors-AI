import scala.util.Random

object AIVsAI {
  def main(args: Array[String]): Unit = {
    var player1Choice = ""
    var player2Choice = ""
    var player1Wins = 0
    var player2Wins = 0
    var draws = 0
    var gameEnd = false


    def game(player1Weight: Int): Unit = {
      println("Please Type \"next\" to begin")
      while (!gameEnd) {
        if (scala.io.StdIn.readLine().equals("next")) {
          println(s"Player 1 wins: ${player1Wins}, Player 2 wins: ${player2Wins}, Draws: ${draws}")

          player2Choice = randomChoice()

          if (Random.nextInt(100) < player1Weight - 10) {
            player1Choice = player1(player2Choice)
          }
          else {
            player1Choice = randomChoice()
          }
          println(s"Player 1 chooses ${player1Choice} and player 2 chooses ${player2Choice}")
          result(player1Choice, player2Choice)
        }
        else if (scala.io.StdIn.readLine().equals("quit")) {
          gameEnd = true
        }
      }

    }

    def player1(player2Choice: String): String = {
      player2Choice match {
        case "rock" => "paper"
        case "paper" => "scissors"
        case "scissors"=> "rock"
      }
  }
    def randomChoice():String = {
      val options = Array("rock", "paper", "scissors")
      Random.shuffle(options.toList).head

    }

    def result(player1Choice: String, player2Choice: String): Unit = {
      (player1Choice, player2Choice) match {
        case ("rock", "scissors") => player1Wins += 1
          println("Player 1 wins \n")
        case ("paper", "rock") => player1Wins += 1
          println("Player 1 wins \n")
        case ("scissors", "paper") => player1Wins += 1
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

    game(80)
  }

}
