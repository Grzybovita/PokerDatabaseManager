package almaszy.pokerapp.Exception;

public class PlayerNotFoundException extends Exception{

    public PlayerNotFoundException(int id) {
        System.out.println("Player with id " + id + " not found!");
    }
}
