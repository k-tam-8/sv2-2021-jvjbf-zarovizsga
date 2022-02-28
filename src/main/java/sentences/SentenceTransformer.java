package sentences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentenceTransformer {

    public String shortenSentence(String sentence) {
        StringBuilder result = new StringBuilder();

        checkStartWithCapital(sentence);
        checkEndWithPunct(sentence);

        String[] words = sentence.split(" ");
        if (words.length > 4) {
            return result.append(words[0]).append(" ... ").append(words[words.length - 1]).toString();
        }
        return sentence;
    }

    private void checkStartWithCapital(String sentence) {
        if (sentence.charAt(0) != sentence.toUpperCase().charAt(0)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
    }

    private void checkEndWithPunct(String sentence) {
        List<Character> puncts = Arrays.asList('?', '!', '.');
        if (!puncts.contains(sentence.charAt(sentence.length() - 1))) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }
}
