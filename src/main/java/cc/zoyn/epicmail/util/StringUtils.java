package cc.zoyn.epicmail.util;

import com.google.common.collect.Lists;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * @author Zoyn
 * @since 2018-06-02
 */
public class StringUtils {

    /**
     * Splitting a string with a given index
     * <p>
     * The following is one example of the use of the tokenizer. The code:
     * <blockquote><pre>
     *     System.out.println(splitString("abcdefg", 3));
     * </pre></blockquote>
     * <p>
     * prints the following output:
     * <blockquote><pre>
     * [abc,def,g]
     * </pre></blockquote>
     * </p>
     *
     * @param str   the string
     * @param index the index
     * @return {@link List}
     */
    @Nonnull
    public static List<String> splitString(String str, int index) {
        if (str == null) {
            throw new NullPointerException("the string cannot be null!");
        }
        if (index < 0) {
            throw new IllegalArgumentException("the index cannot be less than 0!");
        }

        int length = str.length();
        List<String> data = Lists.newArrayList(str);
        if (length >= index) {
            // if the index is zero then not newline
            if (index == 0) {
                return data;
            }
            int div = length / index;

            // start splitting when the check passes
            data.clear();
            for (int i = 0; i <= div; i++) {
                // prevent EOF
                if ((i + 1) * index > length) {
                    String temp = str.substring(i * index, length);
                    if (!temp.isEmpty()) {
                        data.add(temp);
                    }
                    break;
                }
                data.add(str.substring(i * index, (i + 1) * index));
            }
            return data;
        } else {
            return data;
        }
    }
}
