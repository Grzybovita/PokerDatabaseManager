package almaszy.pokerapp;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String date;
    private Integer buyin;
    private Integer stack;
    private Integer blinds;
    private Integer guaranteed;
    @ManyToMany
    @JoinTable(
            name="tournament_player",
            joinColumns = @JoinColumn(name = "tournament_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id"))

    private Set<Player> playersInTournament = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getBuyin() {
        return buyin;
    }

    public void setBuyin(Integer buyin) {
        this.buyin = buyin;
    }

    public Integer getStack() {
        return stack;
    }

    public void setStack(Integer stack) {
        this.stack = stack;
    }

    public Integer getBlinds() {
        return blinds;
    }

    public void setBlinds(Integer blinds) {
        this.blinds = blinds;
    }

    public Integer getGuaranteed() {
        return guaranteed;
    }

    public void setGuaranteed(Integer guaranteed) {
        this.guaranteed = guaranteed;
    }

    public Set<Player> getPlayersInTournament() {
        return playersInTournament;
    }

    public void setPlayersInTournament(Set<Player> playersInTournament) {
        this.playersInTournament = playersInTournament;
    }
}
