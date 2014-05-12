import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by balasubn on 5/1/14.
 */
public class Election
{
    public int votesNeeded(int[] votes, int[] wishList)
    {
        ArrayList<Person> persons = new ArrayList<Person>();
        for(int i=0; i<votes.length; ++i)
        {
            Person person = new Person(i, votes[i], -1, wishList[i]);
            persons.add(person);
        }

        Collections.sort(persons);

        for(int i=0; i<votes.length; ++i)
        {
            persons.get(i).curPosition = i;
        }

        int votesAdded = 0;

        votesAdded += fixPersonsBehindTheirPositions(persons);

        votesAdded += fixPersonAheadOfTheirPositions(persons);

        return votesAdded;
    }

    private int fixPersonsBehindTheirPositions(ArrayList<Person> persons)
    {
        int votesAdded = 0;
        int idx = findPersonBehindOfRequiredPosition(persons);
        while (idx != -1)
        {
            votesAdded += fixPersonPosition(persons, idx);
            idx = findPersonBehindOfRequiredPosition(persons);
        }

        return votesAdded;
    }

    private int fixPersonAheadOfTheirPositions(ArrayList<Person> persons)
    {
        int votesAdded = 0;
        int idx = findPersonAheadOfRequiredPosition(persons);
        while(idx != -1)
        {
            votesAdded += fixPersonPosition(persons, idx);
            idx = findPersonAheadOfRequiredPosition(persons);
        }

        return votesAdded;
    }

    private int findPersonBehindOfRequiredPosition(ArrayList<Person> persons)
    {
        for(int i=0; i<persons.size(); ++i)
        {
            Person person = persons.get(i);
            if(person.reqPosition != -1 && person.reqPosition < person.curPosition)
            {
                return i;
            }
        }

        return -1;
    }

    private int findPersonAheadOfRequiredPosition(ArrayList<Person> persons)
    {
        for(int i=0; i<persons.size(); ++i)
        {
            Person person = persons.get(i);
            if(person.reqPosition != -1 && person.reqPosition > person.curPosition)
            {
                return i;
            }
        }

        return -1;
    }

    private int fixPersonPosition(ArrayList<Person> persons, int idx)
    {
        int votesAdded = 0;

        Person person = persons.remove(idx);
        person.curPosition = person.reqPosition;
        persons.add(person.reqPosition, person);

        for(int i=person.reqPosition; i >= 0; --i)
        {
            person = persons.get(i);
            if( i == persons.size() - 1)
            {
                continue;
            }

            Person nextPerson = persons.get(i+1);
            if(person.voteCount > nextPerson.voteCount || (person.voteCount == nextPerson.voteCount && person.personIdx < nextPerson.personIdx))
            {
                continue;
            }

            if(person.personIdx < nextPerson.personIdx)
            {
                votesAdded += nextPerson.voteCount - person.voteCount;
                person.voteCount = nextPerson.voteCount;
            }
            else
            {
                votesAdded += nextPerson.voteCount - person.voteCount + 1;
                person.voteCount = nextPerson.voteCount + 1;
            }
        }

        return votesAdded;
    }

    public static void main(String[] args)
    {
        Election tester = new Election();
        System.out.println(tester.votesNeeded(new int[] { 10, 8, 6, 4 }, new int[] { -1, 0, -1, 2 }));
    }


    public class Person implements Comparable<Person>
    {
        int personIdx, voteCount, reqPosition, curPosition;

        public Person(int personIdx, int voteCount, int curPosition, int reqPosition)
        {
            this.personIdx = personIdx;
            this.voteCount = voteCount;
            this.curPosition = curPosition;
            this.reqPosition = reqPosition;
        }

        @Override
        public int compareTo(Person person)
        {
            if(this.voteCount == person.voteCount)
            {
                return this.personIdx - person.personIdx;
            }
            else
            {
                return -(this.voteCount - person.voteCount);
            }
        }
    }
}
