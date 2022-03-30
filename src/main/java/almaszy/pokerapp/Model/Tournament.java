package almaszy.pokerapp.Model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty(message = "name cannot be null!")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "date cannot be null!")
    @Column(name = "date")
    private String date;

    @NotEmpty(message = "buyin cannot be null!")
    @Digits(integer = 5, fraction = 0, message = "wrong buyin format!")
    @Column(name = "buyin")
    private Integer buyin;

    @Digits(integer = 7, fraction = 0, message = "wrong stack format!")
    @Column(name = "stack")
    private Integer stack;

    @Digits(integer = 4, fraction = 0, message = "wrong blinds time format!")
    @Column(name = "blinds")
    private Integer blinds;

    @Digits(integer = 8, fraction = 0, message = "wrong guaranteed prize format!")
    @Column(name = "guaranteed")
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