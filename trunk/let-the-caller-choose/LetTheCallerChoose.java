public final class LetTheCallerChoose
{
    public static int countDistinctWords1(BufferedReader in)
    {
        final Set<String> distinctWords = new HashSet<String>();

        String line = null;
        while (null != (line = in.readLine()))
        {
            final String[] words = line.split("+\\W"); 
            List<String> wordsAsList = Arrays.asList(words);
            distinctWords.addAll(wordsAsList);
        }

        return distinctWords.size();
    }

    public static int countDistinctWords2(BufferedReader in)
    {
        final Set<String> distinctWords = new HashSet<String>();

        String line = null;
        while (null != (line = in.readLine()))
        {
            line.split(distinctWords, "+\\W"); 
        }

        return distinctWords.size();
    }
}