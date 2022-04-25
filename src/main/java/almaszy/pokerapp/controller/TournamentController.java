package almaszy.pokerapp.controller;

import almaszy.pokerapp.exception.PlayerNotFoundException;
import almaszy.pokerapp.exception.TournamentNotFoundException;
import almaszy.pokerapp.model.Player;
import almaszy.pokerapp.model.Tournament;
import almaszy.pokerapp.repository.PlayerRepository;
import almaszy.pokerapp.repository.TournamentRepository;
import almaszy.pokerapp.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController

@RequestMapping(path="/tournament")
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    TournamentService tournamentService;

    Logger logger = Logger.getLogger(TournamentController.class.getName());

    @PostMapping(path="/add")
    public void addNewTournament (
            @Valid
            @RequestBody Tournament tournament, BindingResult result) {
        if (result.hasErrors()) {
            logger.log(Level.WARNING, "Errors occured during adding a new tournament!");
        } else {
            tournamentRepository.save(tournament);
            logger.log(Level.FINEST, "Tournament {} has been added!", tournament.getId());

        }
    }

    @GetMapping (path="/find/{id}")
    public @ResponseBody Tournament getTournamentById (@PathVariable("id") int id) throws PlayerNotFoundException {
        if (tournamentRepository.findById(id).isPresent()) {
            return tournamentRepository.findById(id).get();
        }
        else {
            logger.log(Level.WARNING, "Tournament {} not found!", id);
            throw new PlayerNotFoundException(id);
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Tournament> getAllTournaments() {
        // This returns a JSON or XML with the users
        return tournamentRepository.findAll();
    }

    @GetMapping(path="/delete/{id}")
    public @ResponseBody void deleteTournamentById (@PathVariable("id") int id) throws TournamentNotFoundException {
        if (tournamentRepository.findById(id).isPresent()) {
            tournamentService.delete(id);
            logger.log(Level.ALL, "Tournament {} deleted!", id);
        } else {
            logger.log(Level.WARNING, "Tournament {} not found!", id);
            throw new TournamentNotFoundException(id);
        }
    }

    @PutMapping(path="/addPlayer")
    public @ResponseBody void addPlayerToTournament(Integer tournamentID, Integer playerID) throws PlayerNotFoundException, TournamentNotFoundException {
        if (!playerRepository.findById(playerID).isPresent()) {
            logger.log(Level.WARNING, "Player {} not found!", playerID);
            throw new PlayerNotFoundException(playerID);
        }
        else if (!tournamentRepository.findById(tournamentID).isPresent()) {
            logger.log(Level.WARNING, "Tournament {} not found!", tournamentID);
            throw new TournamentNotFoundException(tournamentID);
        }
        else {
            Player p = playerRepository.findById(playerID).get();
            Tournament t = tournamentRepository.findById(tournamentID).get();
            t.getPlayersInTournament().add(p);
            tournamentRepository.save(t);
            logger.log(Level.WARNING, "New player added to tournament {}!", tournamentID);
        }
    }
}