package almaszy.pokerapp;

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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Player> getAllPlayers() {
        // This returns a JSON or XML with the users
        return playerRepository.findAll();
    }


    @GetMapping(path="/delete/{id}")
    public @ResponseBody String deletePlayerById (@PathVariable("id") int id) {
        if (playerRepository.findById(id).isPresent()) {
            playerService.delete(id);
            return "Player with id " + id + " deleted!";
        }
        else return "No player with id " + id + " in database!";
    }

}
