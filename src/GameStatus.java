public class GameStatus {
   public static boolean startGame = false;
   public static boolean inGame = false;
   public static boolean exit = true;
   public static boolean success = false;
   public static boolean restart = false;
   public boolean isGameOver() {
      return !inGame;
   }

   public boolean isGameContinuing() {
      return inGame;
   }


}
