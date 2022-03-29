package almaszy.pokerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(path="/tournament")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    TournamentService tournamentService;

    @PostMapping(path="/add")
    public String addNewTournament (
            @Valid
            @RequestBody Tournament tournament, BindingResult result) {
        if (result.hasErrors()) {
            return "error";
        }
        tournamentRepository.save(tournament);
        return "Saved!";
    }

    @GetMapping (path="/find/{id}")
    public @ResponseBody Tournament getTournamentById (@PathVariable("id") int id) throws PlayerNotFoundException {
        if (tournamentRepository.findById(id).isPresent()) {
            System.out.println(tournamentRepository.findById(id).get());
            return tournamentRepository.findById(id).get();
        }
        else
            throw new PlayerNotFoundException(id);
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Tournament> getAllTournaments() {
        // This returns a JSON or XML with the users
        return tournamentRepository.findAll();
    }

    @GetMapping(path="/delete/{id}")
    public @ResponseBody String deleteTournamentById (@PathVariable("id") int id) throws TournamentNotFoundException {
        if (tournamentRepository.findById(id).isPresent()) {
            tournamentService.delete(id);
        } else {
            throw new TournamentNotFoundException(id);
        }
        return "Player with id " + id + " succesfully deleted!";
    }

    @PutMapping(path="/addPlayer")
    public @ResponseBody void addPlayerToTournament(Integer tournamentID, Integer playerID) throws PlayerNotFoundException, TournamentNotFoundException {
        if (!playerRepository.findById(playerID).isPresent()) {
            throw new PlayerNotFoundException(playerID);
        }
        else if (!tournamentRepository.findById(tournamentID).isPresent()) {
            throw new TournamentNotFoundException(tournamentID);
        }
        else {
            Player p = playerRepository.findById(playerID).get();
            Tournament t = tournamentRepository.findById(tournamentID).get();
            t.getPlayersInTournament().add(p);
            tournamentRepository.save(t);
            System.out.println("Player list updated!");
        }
    }
}