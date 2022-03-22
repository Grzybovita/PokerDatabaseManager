package almaszy.pokerapp;

public interface TournamentService {

    public Iterable<Tournament> findAll();

    public void delete(int id);

}
