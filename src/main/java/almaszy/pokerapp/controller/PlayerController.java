package almaszy.pokerapp.controller;
import almaszy.pokerapp.model.Player;
import almaszy.pokerapp.exception.PlayerNotFoundException;
import almaszy.pokerapp.repository.PlayerRepository;
import almaszy.pokerapp.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@RequestMapping(path="/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

    Logger logger = Logger.getLogger(PlayerController.class.getName());

    @PostMapping (path="/add")
    public void addNewPlayer (
            @Valid
            @RequestBody Player player, BindingResult result) {
        if (result.hasErrors()) {
            logger.log(Level.WARNING, "Error occured during adding new player!");
        } else {
            playerRepository.save(player);
            logger.log(Level.ALL, "New player has been added succesfully!");
        }
    }

    @GetMapping (path="/find/{id}")
    public @ResponseBody Player getPlayerById (@PathVariable("id") int id) throws PlayerNotFoundException {
        if (playerRepository.findById(id).isPresent()) {
            return playerRepository.findById(id).get();
        }
        else {
            logger.log(Level.WARNING, "Player not found");
            throw new PlayerNotFoundException(id);
        }
    }

    @PostMapping (path="/updatetel/{id}")
    public void updatePlayerTel (@RequestBody @PathVariable("id") int id, String telnumber) throws PlayerNotFoundException {
        if (playerRepository.findById(id).isPresent()) {
            Player p = playerRepository.getById(id);
            p.setTelnumber(telnumber);
            playerRepository.save(p);
            logger.log(Level.ALL, "Player {} tel. number updated!", id);
        } else {
            logger.log(Level.WARNING, "Player {} not found!", id);
            throw new PlayerNotFoundException(id);
        }
    }

    @PostMapping (path="/updatemail/{id}")
    public void updatePlayerEmail (
            @RequestBody
            @PathVariable("id") int id, String email) throws PlayerNotFoundException {
        if (playerRepository.findById(id).isPresent()) {
            Player p = playerRepository.getById(id);
            p.setEmail(email);
            playerRepository.save(p);
            logger.log(Level.ALL, "player {} email has been updated!", id);
        } else {
            logger.log(Level.WARNING, "player {} not found!", id);
            throw new PlayerNotFoundException(id);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Player> getAllPlayers() {
        // This returns a JSON or XML with the users
        return playerRepository.findAll();
    }

    @GetMapping(path="/delete/{id}")
    public @ResponseBody void deletePlayerById (@PathVariable("id") int id) throws PlayerNotFoundException {
        if (playerRepository.findById(id).isPresent()) {
            playerService.delete(id);
            logger.log(Level.ALL, "Player {} deleted!", id);
        } else {
            logger.log(Level.WARNING, "Player {} not found!", id);
            throw new PlayerNotFoundException(id);
        }
    }
}
