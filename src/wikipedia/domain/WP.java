package wikipedia.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

<<<<<<< HEAD
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
        return null;
    }
    Page getPage(String title) {
        return null;
    }


}
