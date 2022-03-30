package almaszy.pokerapp.Service;

import almaszy.pokerapp.Model.Tournament;
import almaszy.pokerapp.Repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Transactional
@Service("tournamentService")
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Override
    public Iterable<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    @Override
    public void delete(int id) {
        tournamentRepository.deleteById(id);
    }
}
