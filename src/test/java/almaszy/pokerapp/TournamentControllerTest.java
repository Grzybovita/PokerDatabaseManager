package almaszy.pokerapp;

import almaszy.pokerapp.model.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PokerappApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TournamentControllerTest {

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
    public void testGetAllTournaments() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/tournament/all",
                HttpMethod.GET, entity, String.class);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void testAddTournament() {
        Tournament tournament = new Tournament();
        tournament.setName("admin");
        tournament.setDate("admin");
        tournament.setBuyin(1000);
        tournament.setStack(20000);
        tournament.setBlinds(20);
        tournament.setGuaranteed(12345);
        ResponseEntity<Tournament> postResponse = restTemplate
                .postForEntity(getRootUrl() + "/tournament/add", tournament, Tournament.class);
        assertEquals(200, postResponse.getStatusCode().value());
    }

    @Test
    public void testFindTournamentById() {
        int id = 18;
        //possible change to return type ResponseEntity<Tournament>
        Tournament tournament = restTemplate.getForObject(getRootUrl() + "/tournament/find/" + id, Tournament.class);
        assertNotNull(tournament.getName());
    }




}
