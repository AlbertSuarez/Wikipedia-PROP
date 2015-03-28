package wikipedia.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;


public class WP
{
	// private Graph
	private Map<String, Category> catMap;
	private Map<String, Page> pagMap;

	public WP() {
		catMap = new ConcurrentSkipListMap<String, Category>();
		pagMap = new ConcurrentSkipListMap<String, Page>();
	}

	Category getCategory(String title) {
		return catMap.get(title);
	}

	Page getPage(String title) {
		return pagMap.get(title);
	}
}
