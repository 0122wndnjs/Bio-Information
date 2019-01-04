/**
 * Sequence class - for Bioinfo class
 * 
 * @author Joowon Kim
 */
public class Sequence {
	private String type; // type
	private SLList<Character> bases; // bases

	/**
	 * Overloaded Constructor
	 * 
	 * @param type
	 */
	public Sequence(String type) {
		this.type = type;
	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param bases
	 */
	public Sequence(SLList<Character> bases) {
		this.bases = bases;
	}

	/**
	 * Overloaded Constructor
	 * 
	 * @param type
	 * @param bases
	 */
	public Sequence(String type, SLList<Character> bases) {
		this.type = type;
		this.bases = bases;
	}

	/**
	 * Set Type
	 * 
	 * @param type
	 */
	void setType(String type) {
		this.type = type;
	}

	/**
	 * Get Type
	 * 
	 * @return type
	 */
	String getType() {
		return type;
	}

	/**
	 * Set Bases
	 * 
	 * @param bases
	 */
	void setBases(SLList<Character> bases) {
		this.bases = bases;
	}

	/**
	 * Get Bases
	 * 
	 * @return bases
	 */
	SLList<Character> getBases() {
		return bases;
	}
}
