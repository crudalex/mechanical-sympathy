public final class LetTheCallerChoose
{
    public static int countDistinctWords1(final BufferedReader in)
    {
        final Set<String> distinctWords = new HashSet<String>();

        String line = null;
        while (null != (line = in.readLine()))
        {
            final String[] words = line.split("+\\W"); // regex should be precompiled for performance
            List<String> wordsAsList = Arrays.asList(words);
            distinctWords.addAll(wordsAsList);
        }

        return distinctWords.size();
    }

    public static int countDistinctWords2(final BufferedReader in)
    {
        final Set<String> distinctWords = new HashSet<String>();

        String line = null;
        while (null != (line = in.readLine()))
        {
            line.split(distinctWords, "+\\W"); // regex should be precompiled for performance
        }

        return distinctWords.size();
    }
}