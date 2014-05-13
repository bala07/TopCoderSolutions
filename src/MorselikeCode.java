import java.util.HashMap;

/**
 * Created by Balasubramanian on 5/13/14.
 */
public class MorselikeCode
{
    public String decrypt(String[] library, String message)
    {
        HashMap<String, Character> dictionary = new HashMap<String, Character>();

        processLibrary(library, dictionary);

        String[] wordsInMessage = message.split(" ");
        String decryptedMessage = "";

        for(String word : wordsInMessage)
        {
            if(dictionary.containsKey(word))
            {
                decryptedMessage += dictionary.get(word) + "";
            }
            else
            {
                decryptedMessage += "?";
            }
        }

        return decryptedMessage;
    }

    private void processLibrary(String[] library, HashMap<String, Character> dictionary)
    {
        for(String code : library)
        {
            char ch = code.split(" ")[0].charAt(0);
            String sequence = code.split(" ")[1];

            dictionary.put(sequence, ch);
        }
    }
}
