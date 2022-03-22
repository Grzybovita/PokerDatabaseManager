package almaszy.pokerapp;

public interface PlayerService {

    public Iterable<Player> findAll();

    public void delete(int id);

}
