import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Balasubramanian on 5/13/14.
 */
public class Paintball
{
    ArrayList<Team> teams = new ArrayList<>();
    HashMap<String, Player> playersLookUp = new HashMap<>();
    HashMap<String, Team> playerTeamsLookUp = new HashMap<>();

    public String[] getLeaderboard(String[] players, String[] messages)
    {

        addPlayersToTeams(players);

        populatePlayersLookUp();

        populatePlayersTeamLookUp();

        computeScoresForPlayers(messages);

        computeScoresForTeams();

        Collections.sort(teams);
        for(Team team : teams)
        {
            Collections.sort(team.players);
        }

        String[] leaderBoard = fillLeaderBoard();

        return leaderBoard;
    }

    private String[] fillLeaderBoard()
    {
        ArrayList<String> result = new ArrayList<>();

        for(Team team : teams)
        {
            result.add(team.name + " " + team.score);
            for(Player player : team.players)
            {
                result.add("  " + player.name + " " + player.score);
            }
        }

        String[] leaderBoard = new String[result.size()];

        for(int idx = 0; idx < leaderBoard.length; ++idx)
        {
            leaderBoard[idx] = result.get(idx);
        }

        return leaderBoard;
    }

    private void computeScoresForTeams()
    {
        for(Team team : teams)
        {
            for(Player player : team.players)
            {
                team.score += player.score;
            }
        }
    }

    private void computeScoresForPlayers(String[] messages)
    {
        for(String message : messages)
        {
            String[] tokens = message.split(" ");
            Player splatterer = playersLookUp.get(tokens[0]);
            Player splattered = playersLookUp.get(tokens[2]);

            Team splattererTeam = playerTeamsLookUp.get(tokens[0]);
            Team splatteredTeam = playerTeamsLookUp.get(tokens[2]);

            if(splattererTeam.name.equals(splatteredTeam.name))
            {
                splatterer.score -= 1;
            }
            else
            {
                splatterer.score += 1;
                splattered.score -= 1;
            }
        }
    }

    private void populatePlayersTeamLookUp()
    {
        for(Team team : teams)
        {
            for(Player player : team.players)
            {
                playerTeamsLookUp.put(player.name, team);
            }
        }
    }

    private void populatePlayersLookUp()
    {
        for(Team team : teams)
        {
            for(Player player : team.players)
            {
                playersLookUp.put(player.name, player);
            }
        }
    }

    private void addPlayersToTeams(String[] players)
    {
        HashMap<String, Team> teamsLookUp = new HashMap<>();

        for(String entry : players)
        {
            String playerName = entry.split(" ")[0];
            String teamName = entry.split(" ")[1];

            Player player = new Player(playerName, 0);

            if(teamsLookUp.containsKey(teamName))
            {
                Team team = teamsLookUp.get(teamName);
                team.players.add(player);
            }
            else
            {
                Team team = new Team();
                team.name = teamName;

                team.players.add(player);

                teams.add(team);

                teamsLookUp.put(teamName, team);
            }
        }
    }

    private class Player implements Comparable<Player>
    {
        String name;
        int score;

        public Player()
        {
            name = "";
            score = 0;
        }

        public Player(String name, int score)
        {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Player player)
        {
            if(this.score != player.score)
            {
                return player.score - this.score;
            }

            else
            {
                return this.name.compareTo(player.name);
            }
        }
    }

    private class Team implements Comparable<Team>
    {
        String name;
        int score;
        ArrayList<Player> players;

        public Team()
        {
            name = "";
            score = 0;
            players = new ArrayList<Player>();
        }

        @Override
        public int compareTo(Team team)
        {
            if(this.score != team.score)
            {
                return team.score - this.score;
            }
            else
            {
                return this.name.compareTo(team.name);
            }

        }
    }
}
