package almaszy.pokerapp.Controller;
import almaszy.pokerapp.Model.Player;
import almaszy.pokerapp.Exception.PlayerNotFoundException;
import almaszy.pokerapp.Repository.PlayerRepository;
import almaszy.pokerapp.Service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@Validated
@RequestMapping(path="/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

    @PostMapping (path="/add")
    public String addNewPlayer (
            @Valid
            @RequestBody Player player, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        playerRepository.save(player);
        return "Saved!";
    }

    @GetMapping (path="/find/{id}")
    public @ResponseBody Player getPlayerById (@PathVariable("id") int id) throws PlayerNotFoundException {
        if (playerRepository.findById(id).isPresent()) {
            System.out.println(playerRepository.findById(id).get());
            return playerRepository.findById(id).get();
        }
        else {
            throw new PlayerNotFoundException(id);
        }
    }

    @PostMapping (path="/updatetel/{id}")
    public String updatePlayerTel (@RequestBody @PathVariable("id") int id, String telnumber) throws PlayerNotFoundException {
        if (playerRepository.findById(id).isPresent()) {
            Player p = playerRepository.getById(id);
            p.setTelnumber(telnumber);
            playerRepository.save(p);
        } else {
            throw new PlayerNotFoundException(id);
        }
     return "New phone number saved!";
    }

    @PostMapping (path="/updatemail/{id}")
    public String updatePlayerEmail (
            @RequestBody
            @PathVariable("id") int id, String email) throws PlayerNotFoundException {
        if (playerRepository.findById(id).isPresent()) {
            Player p = playerRepository.getById(id);
            p.setEmail(email);
            playerRepository.save(p);
        } else {
            throw new PlayerNotFoundException(id);
        }
        return "New email address saved!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Player> getAllPlayers() {
        // This returns a JSON or XML with the users
        return playerRepository.findAll();
    }


    @GetMapping(path="/delete/{id}")
    public @ResponseBody String deletePlayerById (@PathVariable("id") int id) throws PlayerNotFoundException {
        if (playerRepository.findById(id).isPresent()) {
            playerService.delete(id);
        } else {
            throw new PlayerNotFoundException(id);
        }
        return "Player with id " + id + " succesfully deleted!";
    }
}
