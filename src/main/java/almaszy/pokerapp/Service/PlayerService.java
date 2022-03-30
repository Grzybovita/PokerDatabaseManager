package almaszy.pokerapp.Service;

import almaszy.pokerapp.Model.Player;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {

    public Iterable<Player> findAll();

    public void delete(int id);

    public Player update(Player player);

}
