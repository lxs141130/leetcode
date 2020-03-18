package microsoft.Onsite;

/**
 * @author franksun
 * 
 *         Mar 4, 2020
 * 
 */
public class LC_44_WildCard_Match {
	public boolean isMatch(String s, String p) {
		boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
		match[0][0] = true;

		for (int i = 1; i <= p.length(); i++) {
			if (p.charAt(i - 1) == '*') {
				match[0][i] = match[0][i - 1];
			}
		}

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				if (p.charAt(j - 1) != '*') {
					match[i][j] = match[i - 1][j - 1] && (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1));
				}else {
					match[i][j] = match[i][j - 1] || match[i - 1][j];
				}
			}
		}
		return match[s.length()][p.length()];
	}
}
