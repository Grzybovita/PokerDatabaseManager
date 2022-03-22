package almaszy.pokerapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/tournament")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    TournamentService tournamentService;

    @PostMapping(path="/add")
    public @ResponseBody String addNewTournament (String name, String date, Integer buyin, Integer stack, Integer blinds, Integer guaranteed) {
        Tournament t = new Tournament();
        t.setName(name);
        t.setDate(date);
        if (buyin != null)
            t.setBuyin(buyin);
        else
            t.setBuyin(0);
        if (stack != null)
            t.setStack(stack);
        else
            t.setStack(0);
        if (blinds != null)
            t.setBlinds(blinds);
        else
            t.setBlinds(0);
        if (guaranteed != null)
            t.setGuaranteed(guaranteed);
        else
            t.setGuaranteed(0);
        tournamentRepository.save(t);
        return "Saved!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Tournament> getAllTournaments() {
        // This returns a JSON or XML with the users
        return tournamentRepository.findAll();
    }

    @GetMapping(path="/delete/{id}")
    public @ResponseBody String deletePlayerById (@PathVariable("id") int id) {
        tournamentService.delete(id);
        return "Tournament with id " + id + "deleted!";
    }

    @PutMapping(path="/addPlayer")
    public @ResponseBody void addPlayerToTournament(Integer tournamentID, Integer playerID) {
        if (playerRepository.findById(playerID).isPresent() &&
            tournamentRepository.findById(tournamentID).isPresent()) {
            Player p = playerRepository.findById(playerID).get();
            Tournament t = tournamentRepository.findById(tournamentID).get();
            t.getPlayersInTournament().add(p);
            tournamentRepository.save(t);
            p.getTournaments().add(t);
            playerRepository.save(p);
            System.out.println("Player list updated!");
        } else {
            System.out.println("Wrong tournament or player ID!");
        }
    }
}