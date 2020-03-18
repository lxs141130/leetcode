package indeed;

import java.util.ArrayList;
import java.util.List;

/**
 * @author franksun
 * 
 *         Mar 10, 2020
 * 
 */
public class LC_68_Text_Justification {
	public List<String> fullJustify(String[] words, int maxWidth) {
		List<String> res = new ArrayList<>();
		if (words == null || words.length == 0) {
			return res;
		}
		int count = 0;// count is the length of the line for now
		int start = 0;//start of current line
		for (int i = 0; i < words.length; i++) {
			if (count + words[i].length() + (i - start) > maxWidth) {
				int spaceNum = 0;
				int extraNum = 0;
				// check how many space should evenly distribute between the words
				// space between existed only when there is more than one word
				if (i - start - 1 > 0) {
					spaceNum = (maxWidth - count) / (i - start - 1);
					extraNum = (maxWidth - count) % (i - start - 1);
				}
				StringBuilder str = new StringBuilder();
				for (int j = start; j < i; j++) {
					str.append(words[j]);
					if (j < i - 1) {
						for (int k = 0; k < spaceNum; k++) {
							str.append(" ");
						}
						if (extraNum > 0) {
							str.append(" ");
						}
						extraNum--;
					}
				}
				for (int j = str.length(); j < maxWidth; j++) {
					str.append(" ");
				}
				res.add(str.toString());
				count = 0;
				start = i;
			}
			count += words[i].length();//only count character
		}
		//we need to process the last line
		StringBuilder str = new StringBuilder();
		for (int j = start; j < words.length; j++) {
			str.append(words[j]);
			if (str.length() < maxWidth) {
				str.append(" ");
			}
		}
		for (int i = str.length(); i < maxWidth; i++) {
			str.append(" ");
		}
		res.add(str.toString());
		return res;
	}
}
