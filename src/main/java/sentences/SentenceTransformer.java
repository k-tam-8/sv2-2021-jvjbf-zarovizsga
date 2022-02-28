package sentences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentenceTransformer {

    public String shortenSentence(String sentence) {
        String result = sentence;

        isStartWithCapital(sentence);
        isEndWithPunct(sentence);

        String[] words = sentence.split(" ");
        if (words.length > 4) {
            result = words[0] + " ... " + words[words.length - 1];
        }
        return result;
    }

    private void isStartWithCapital(String sentence) {
        if (sentence.charAt(0) != sentence.toUpperCase().charAt(0)) {
            throw new IllegalArgumentException("Must start with capital letter!");
        }
    }

    private void isEndWithPunct(String sentence) {
        List<Character> puncts = Arrays.asList('?', '!', '.');
        if (!puncts.contains(sentence.charAt(sentence.length() - 1))) {
            throw new IllegalArgumentException("Must end with . ! or ?");
        }
    }
}
