/**
 * 
 */
package indeed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author franksun
 *
 */
public class LC_811_Subdomain_Visite_Count {
	Map<String, Integer> map = new HashMap<>();

	public List<String> subdomainVisits(String[] cpdomains) {
		List<String> rst = new ArrayList<>();
		for (String cpdomain : cpdomains) {
			String[] sArray = cpdomain.trim().split("\\s+");
			int times = Integer.parseInt(sArray[0]);

			String[] domains = sArray[1].split("\\.");
			String subDomain = "";
			for (int i = domains.length - 1; i >= 0; i--) {
				if (subDomain.equals("")) {
					subDomain = domains[i];
				} else {
					subDomain = domains[i] + "." + subDomain;
				}
				map.putIfAbsent(subDomain, 0);
				map.put(subDomain, map.get(subDomain) + times);
			}
		}
		
		map.entrySet().stream().forEach(entry-> rst.add(entry.getValue() + " " + entry.getKey()));
		return rst;
	}

	public static void main(String[] args) {
		LC_811_Subdomain_Visite_Count svc = new LC_811_Subdomain_Visite_Count();
		String[] cpdomains = { "900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org" };
		List<String> rst = svc.subdomainVisits(cpdomains);
		System.out.print(rst);
		
	}
}
