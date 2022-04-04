package almaszy.pokerapp.exception;

public class TournamentNotFoundException extends Exception{

    public TournamentNotFoundException(int id) {
        System.out.println("Tournament not found exception!");
    }
}