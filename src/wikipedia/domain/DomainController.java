package wikipedia.domain;
import wikipedia.persistence.*;

public class DomainController
{
	private WP wikipedia;

	public DomainController() {
		wikipedia = new WP();
	}
	public void Run(){
		PersistenceController pc = new PersistenceController();
		pc.load(wikipedia);
	}
	
}
