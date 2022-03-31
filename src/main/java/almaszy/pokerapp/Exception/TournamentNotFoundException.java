package almaszy.pokerapp.Exception;

public class TournamentNotFoundException extends Exception{

    public TournamentNotFoundException(int id) {
        System.out.println("Tournament with id " + id + " not found!");
    }
}