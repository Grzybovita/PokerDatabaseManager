package almaszy.pokerapp.Service;

import almaszy.pokerapp.Model.Player;
import almaszy.pokerapp.Repository.PlayerRepository;
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

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }
}
