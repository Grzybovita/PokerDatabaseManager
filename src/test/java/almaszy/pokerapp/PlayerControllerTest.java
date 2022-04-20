package almaszy.pokerapp;

import almaszy.pokerapp.model.Player;
import almaszy.pokerapp.repository.PlayerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PokerappApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetAllPlayers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/player/all",
                HttpMethod.GET, entity, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testAddPlayer() {
        Player player = new Player();
        player.setName("admin");
        player.setLastname("admin");
        player.setNick("adminNick");
        player.setTelnumber("1233456");
        player.setEmail("admin@admin.com");
        ResponseEntity<Player> postResponse = restTemplate
                            .postForEntity(getRootUrl() + "/player/add", player, Player.class);
        assertEquals(200, postResponse.getStatusCode().value());
    }

    @Test
    public void testFindPlayerById() {
        int id = 5;
        //possible change to return type ResponseEntity<Player>
        Player player = restTemplate.getForObject(getRootUrl() + "/player/find/" + id, Player.class);
        assertNotNull(player.getName());
    }

    @Test
    public void testUpdatePlayerTel() {
        int id = 5;
        Player player = restTemplate.getForObject(getRootUrl() + "/player/find/" + id, Player.class);
        assertNotNull(player.getName());
        player.setTelnumber("333444555");
        restTemplate.put(getRootUrl() + "/player/updatetel/" + id, player);
        assertEquals("333444555", player.getTelnumber());
    }

    @Test
    public void testUpdatePlayerEmail() {
        int id = 5;
        Player player = restTemplate.getForObject(getRootUrl() + "/player/find/" + id, Player.class);
        assertNotNull(player.getName());
        player.setEmail("adminxx@admin.com");
        restTemplate.put(getRootUrl() + "/player/updatemail/" + id, player);
        assertEquals("adminxx@admin.com", player.getEmail());
    }

    //TODO
    //check for all player records in player_tournament table (many-to-many), player_id is PK
    //test below only checks if endpoint is ok
    @Test
    public void testDeletePlayer() {
        int id = 5;
        Player player = restTemplate.getForObject(getRootUrl() + "/player/delete/" + id, Player.class);
        assertNotNull(player);
        restTemplate.delete(getRootUrl() + "/player/delete/" + id);
        try {
            restTemplate.getForObject(getRootUrl() + "/player/delete/" + id, Player.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
        }
    }
}
