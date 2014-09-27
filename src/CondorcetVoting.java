/**
 * Created by balasubn on 9/13/14.
 */
public class CondorcetVoting
{
    int totalVoters;
    int totalCandidates;
    String[] votes;
    public int winner(String[] votes) {
        this.votes = votes;
        totalVoters = votes.length;
        totalCandidates = votes[0].length();

        for(int candidate = 0; candidate < totalCandidates; ++candidate) {
            if(isCondorcetWinner(candidate)) {
                return candidate;
            }
        }

        return -1;
    }

    private boolean isCondorcetWinner(int candidate)
    {
        for(int oppCandidate = 0; oppCandidate < totalCandidates; ++oppCandidate ) {
            if(oppCandidate == candidate) {
                continue;
            }

            int votesForCandidate = 0;
            int votesForOppCandidate = 0;
            for(int voter = 0; voter < totalVoters; ++voter) {
                if(votes[voter].charAt(candidate) < votes[voter].charAt(oppCandidate)) {
                    ++votesForCandidate;
                }
                else if(votes[voter].charAt(candidate) > votes[voter].charAt(oppCandidate)) {
                    ++votesForOppCandidate;
                }
            }

            if(votesForCandidate <= votesForOppCandidate) {
                return false;
            }
        }

        return true;
    }


}
