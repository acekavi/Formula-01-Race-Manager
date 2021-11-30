public class Main {
    static Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();

    public static void main(String[] args) throws InterruptedException {
        new SplashScreen().setVisible(true);
        championshipManager.menu();
        // Run the program
    }
}
