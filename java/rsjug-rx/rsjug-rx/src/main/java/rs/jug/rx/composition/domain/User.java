package rs.jug.rx.composition.domain;

/**
 * @author toff
 *
 */
public class User {

	public String id;

	public User(String id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}
	
	
}
