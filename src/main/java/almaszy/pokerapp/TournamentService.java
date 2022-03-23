package almaszy.pokerapp;

import org.springframework.stereotype.Service;

@Service
public interface TournamentService {

    public Iterable<Tournament> findAll();

    public void delete(int id);

}
