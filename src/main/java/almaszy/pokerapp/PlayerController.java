package almaszy.pokerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/player")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewPlayer (String name, String lastname, String nick, String telnumber,
                                              String email, String address, String city, String postalcode) {
        Player p = new Player();
        p.setName(name);
        p.setLastname(lastname);
        p.setNick(nick);
        p.setTelnumber(telnumber);
        p.setEmail(email);
        p.setAddress(address);
        p.setCity(city);
        p.setPostalcode(postalcode);
        playerRepository.save(p);
        return "Saved!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Player> getAllPlayers() {
        // This returns a JSON or XML with the users
        return playerRepository.findAll();
    }

    @GetMapping(path="/delete/{id}")
    public @ResponseBody String deletePlayerById (@PathVariable("id") int id) {
        playerService.delete(id);
        return "Player with id " + id + "deleted!";
    }

}
