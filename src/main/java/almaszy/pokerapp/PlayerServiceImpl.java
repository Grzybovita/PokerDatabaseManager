package almaszy.pokerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("playerService")
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerRepository playerRepository;



    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public void delete(int id) {
        playerRepository.deleteById(id);
    }
}
