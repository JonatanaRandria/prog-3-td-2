package integration;

import app.foot.FootApi;
import app.foot.controller.rest.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.flogger.Flogger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FootApi.class)
@AutoConfigureMockMvc
class MatchIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules();  //Allow 'java.time.Instant' mapping

    @Test
    void read_match_by_id_ok() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/matches/2"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();
        Match actual = objectMapper.readValue(
                response.getContentAsString(), Match.class);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedMatch2(), actual);
    }



    void read_match_ok() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(get("/matches"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse();

        Match actual = objectMapper.readValue(
                response.getContentAsString(), Match.class);

        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(expectedMatch2(), actual);
    }


    private static List<Object> exceptedMatchN(){
        return List.of(,expectedMatch2());
    }


    public static Match expectedMatch1() {
        return Match.builder()
                .id(1)
                .teamA(teamMatchA1())
                .teamB(teamMatchB1())
                .stadium("S1")
                .datetime(Instant.parse("2023-01-01T10:00:00Z"))
                .build();
    }

    public static TeamMatch teamMatchA1() {
        return TeamMatch.builder()
                .team(team1())
                .score(4)
                .scorers(List.of(PlayerScorer.builder()
                                .player(player1())
                                .scoreTime(30)
                                .isOG(false)
                                .build(),
                        PlayerScorer.builder()
                                .player(player1())
                                .scoreTime(20)
                                .isOG(false)
                                .build(),
                                PlayerScorer.builder()
                                        .player(player1())
                                        .scoreTime(10)
                                        .isOG(false)
                                        .build(),
                                PlayerScorer.builder()
                                        .player(player4())
                                        .scoreTime(60)
                                        .isOG(true)
                                        .build()
                        )

                        )
                .build();
    }

    public static TeamMatch teamMatchB1() {
        return TeamMatch.builder()
                .team(team2())
                .score(2)
                .scorers(List.of(PlayerScorer.builder()
                                        .player(player2())
                                        .scoreTime(40)
                                        .isOG(true)
                                        .build(),
                                PlayerScorer.builder()
                                        .player(player3())
                                        .scoreTime(50)
                                        .isOG(false)
                                        .build()
                        )
                )
                .build();
    }

    private static Team team1() {
        return Team.builder()
                .id(1)
                .name("E1")
                .build();
    }

    private static Team team2() {
        return Team.builder()
                .id(2)
                .name("E2")
                .build();
    }
    public static app.foot.controller.rest.Team team3() {
        return app.foot.controller.rest.Team.builder()
                .id(4)
                .name("E4")
                .build();
    }

    public static Player player1() {
        return Player.builder()
                .id(1)
                .name("J1")
                .teamName(team1().getName())
                .isGuardian(false)
                .build();
    }
    public static Player player2() {
        return Player.builder()
                .id(2)
                .name("J2")
                .teamName(team1().getName())
                .isGuardian(false)
                .build();
    }
    private static Player player3() {
        return Player.builder()
                .id(3)
                .name("J3")
                .teamName(team2().getName())
                .isGuardian(false)
                .build();
    }
    public static Player player4() {
        return Player.builder()
                .id(4)
                .name("J4")
                .teamName(team1().getName())
                .isGuardian(false)
                .build();
    }



    public static Player player7() {
        return Player.builder()
                .id(6)
                .name("J6")
                .teamName(team5().getName())
                .isGuardian(false)
                .build();
    }

    public static Player player5() {
        return Player.builder()
                .id(4)
                .name("J4")
                .teamName(team2().getName())
                .isGuardian(false)
                .build();
    }

    public static app.foot.controller.rest.Team team5() {
        return app.foot.controller.rest.Team.builder()
                .id(3)
                .name("E3")
                .build();
    }










    private static Match expectedMatch2() {
        return Match.builder()
                .id(2)
                .teamA(teamMatchA())
                .teamB(teamMatchB())
                .stadium("S2")
                .datetime(Instant.parse("2023-01-01T14:00:00Z"))
                .build();
    }

    private static TeamMatch teamMatchB() {
        return TeamMatch.builder()
                .team(team3())
                .score(0)
                .scorers(List.of())
                .build();
    }

    private static TeamMatch teamMatchA() {
        return TeamMatch.builder()
                .team(team2())
                .score(2)
                .scorers(List.of(PlayerScorer.builder()
                                .player(player3())
                                .scoreTime(70)
                                .isOG(false)
                                .build(),
                        PlayerScorer.builder()
                                .player(player6())
                                .scoreTime(80)
                                .isOG(true)
                                .build()))
                .build();
    }

    private static Team team3() {
        return Team.builder()
                .id(3)
                .name("E3")
                .build();
    }

    private static Player player6() {
        return Player.builder()
                .id(6)
                .name("J6")
                .teamName(team3().getName())
                .isGuardian(false)
                .build();
    }




}
