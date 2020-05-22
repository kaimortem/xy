public class GameStatus {
   public static boolean startGame = false;
   public static boolean inGame = false;
   public static boolean success = false;
   public static boolean isGameOver() {
      return !inGame;
   }

   public static boolean isGameContinuing() {
      return inGame;
   }

}
