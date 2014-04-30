public class SMSLanguage
{
    public String translate(String text)
    {

        text = replaceAll(text, ".", "");

        text = replaceAll(text, ",", "");

        text = replaceAll(text, "?", "");

        text = replaceAll(text, "!", "");

        text = text.toLowerCase();

        text = replaceAll(text, "and", "&");

        text = replaceAll(text, "ate", "8");

        text = replaceAll(text, "at", "@");

        text = replaceAll(text, "you", "U");

        return text;
    }

    public static void main(String[] args) {
        SMSLanguage object = new SMSLanguage();
        object.translate("I HATE rats, and you?");
    }

    private String replaceAll(String text, String pattern, String replacement) {
        while(text.contains(pattern)) {
            text = text.replace(pattern, replacement);
        }

        return text;
    }
}