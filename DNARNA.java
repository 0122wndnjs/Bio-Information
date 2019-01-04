/**
 * DNARNA class - for Bioinfo classes
 * 
 * @author Joowon Kim
 */

public class DNARNA {
	/**
	 * Parse method
	 * 
	 * @param s
	 *            : A line of command
	 * @param seq:
	 *            Sequence Array
	 */
	public void DnaRna(String s, Sequence[] seq) {
		String[] sArr = s.split("\\s+");
		// Calling methods 
		switch (sArr[0]) {
		case "insert": //  calling insert method
			Insert(Integer.parseInt(sArr[1]), sArr[2], sArr.length > 3 ? sArr[3] : "null", seq);
			break;
		case "remove": // calling remove method
			Remove(Integer.parseInt(sArr[1]), seq);
			break;
		case "print": // calling print method
			Print(sArr.length > 1 ? Integer.parseInt(sArr[1]) : -1, seq);
			break;
		case "splice": // calling splice method
			Splice(Integer.parseInt(sArr[1]), sArr[2], sArr[3], Integer.parseInt(sArr[4]), seq);
			break;
		case "clip": // calling clip method
			Clip(Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]), sArr.length > 3 ? Integer.parseInt(sArr[3]) : -1, seq);
			break;
		case "copy": // calling copy method
			Copy(Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]), seq);
			break;
		case "swap": // calling swap method
			Swap(Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]), Integer.parseInt(sArr[3]), Integer.parseInt(sArr[4]),
					seq);
			break;
		case "overlap": // calling overlap method
			Overlap(Integer.parseInt(sArr[1]), Integer.parseInt(sArr[2]), seq);
			break;
		case "transcribe": // calling transcribe method
			Transcribe(Integer.parseInt(sArr[1]), seq);
			break;
		case "translate": // calling translate method
			Translate(Integer.parseInt(sArr[1]), seq);
			break;
		default:
			System.out.println("Wrong command.");
			System.exit(0);
			break;
		}
	}

	/**
	 * Insert method
	 * 
	 * @param pos
	 *            : Sequence array position
	 * @param type
	 * @param bases
	 * @param seq
	 *            : Sequence array
	 */
	public void Insert(int pos, String type, String bases, Sequence[] seq) {
		if (pos >= seq.length || pos < 0) {
			System.out.println("Invalid position to insert at position: " + pos);
			return;
		}

		if (!type.equals("DNA") && !type.equals("RNA")) {
			System.out.println("Invalid Type");
			return;
		}

		if (bases.equals("null")) { // when there are no characters, does not work.
			if (type.equals("DNA")) {
				seq[pos] = new Sequence("DNA", new SLList<Character>());
				return;
			} else {
				seq[pos] = new Sequence("RNA", new SLList<Character>());
				return;
			}
		}

		if ((!bases.matches("[ATGC]*") && !type.equals("RNA")) || (!bases.matches("[AUGC]*") && !type.equals("DNA"))) {
			System.out.println("Given bases were not correct for given type to insert at position: " + pos);
			return;
		}

		seq[pos] = new Sequence(type, new SLList<Character>());
		char[] chBases = bases.toCharArray();
		for (char ch : chBases)
			seq[pos].getBases().add(Character.valueOf(ch));
	}

	/**
	 * Remove method
	 * 
	 * @param pos
	 *            : Sequence array position
	 * @param seq
	 *            : Sequence array
	 */

	public void Remove(int pos, Sequence[] seq) {
		if (pos >= seq.length || pos < 0) {
			System.out.println("Invalid position to remove at position: " + pos);
			return;
		}

		if (seq[pos] == null) {
			System.out.println("There is no sequence to remove at position: " + pos);
			return;
		}
		seq[pos] = null;
	}

	/**
	 * Print method
	 * 
	 * @param pos
	 *            : When pos = -1 than it prints out all the sequences
	 * @param seq
	 *            : Sequence array
	 */
	public void Print(int pos, Sequence[] seq) {
		if (pos == -1) { // printing out all sequences.
			for (int i = 0; i < seq.length; i++) {
				if (seq[i] == null) {
					System.out.println(i);
				} else { 
					System.out.println(i + "   " + seq[i].getType() + "  " + seq[i].getBases().toString());
				}
			}
		} else { // printing out only one sequence.
			if (seq[pos] == null){
				System.out.println("There is not sequence at position " + pos);
			} else {
				System.out.println(pos + "   " + seq[pos].getType() + "  " + seq[pos].getBases().toString());
			}
		}
	}

	/**
	 * Splice method
	 * 
	 * @param pos
	 *            : Sequence array position
	 * @param type
	 * @param bases
	 * @param start
	 * @param seq
	 *            : Sequence array
	 */
	public void Splice(int pos, String type, String bases, int start, Sequence[] seq) {
		if (seq[pos] == null) {
			System.out.println("There is no sequence to remove at position: " + pos);
			return;
		}

		if (!type.equals("DNA") && !type.equals("RNA")) {
			System.out.println("Given bases were not correct for given type to insert at position: " + pos);
			return;
		}

		if (pos >= seq.length || pos < 0) {
			System.out.println("Invalid position to insert at position: " + pos);
			return;
		}

		if (start >= seq[pos].getBases().length || start < 0) {
			System.out.println("Invalid position to insert at position: " + pos);
			return;
		}

		if ((!bases.matches("[ATGC]*") && !seq[pos].getType().equals("RNA"))
				|| (!bases.matches("[AUGC]*") && !seq[pos].getType().equals("DNA"))) {
			System.out.println("The sequence you want to splice in is not made of valid bases.");
			return;
		}

		if ((!bases.matches("[ATGC]*") && !type.equals("RNA")) || (!bases.matches("[AUGC]*") && !type.equals("DNA"))) {
			System.out.println("Given bases were not correct for given type to insert at position: " + pos);
			return;
		}

		char[] chBases = bases.toCharArray();
		int count = 0;
		for (char ch : chBases) {
			seq[pos].getBases().insert(start + count, Character.valueOf(ch));
			count++;
		}
	}

	/**
	 * Clip method
	 * 
	 * @param pos
	 *            : Sequence array position
	 * @param start
	 * @param end
	 *            : If end = -1, than end is the length of the Sequence
	 * @param seq
	 *            : Sequence array
	 */
	public void Clip(int pos, int start, int end, Sequence[] seq) {
		if (seq[pos] == null || seq[pos].getBases().length == 0) {
			System.out.println("There is no sequence to remove at position: " + pos);
			return;
		}

		if (pos >= seq.length || pos < 0) {
			System.out.println("Invalid position at position: " + pos);
			return;
		}

		if (end == -1) {
			if (start < 0 || start >= seq[pos].getBases().length) {
				System.out.println("Invalid clip indexes passed");
				return;
			}

			Sequence sq = new Sequence(seq[pos].getType(), new SLList<Character>());
			
			for (int i = start; i < seq[pos].getBases().length; i++)
				sq.getBases().add(seq[pos].getBases().getValue(i));
			seq[pos] = sq;
		} else {
			if (start < 0 || start >= seq[pos].getBases().length) {
				System.out.println("Invalid clip indexes passed");
				return;
			}

			if (end < 0 || end >= seq[pos].getBases().length) {
				System.out.println("stop cannot be greater than list length.");
				return;
			}

			Sequence sq = new Sequence(seq[pos].getType(), new SLList<Character>());
			for (int i = start; i <= end; i++)
				sq.getBases().add(seq[pos].getBases().getValue(i));
			seq[pos] = sq;
		}
	}

	/**
	 * Copy method
	 * 
	 * @param pos1
	 *            : Sequence array position 1
	 * @param pos2
	 *            : Sequence array position 2
	 * @param seq
	 *            : Sequence array
	 */
	public void Copy(int pos1, int pos2, Sequence[] seq) {
		if (seq[pos1] == null) {
			System.out.println("There is no sequence to copy at position: " + pos1);
			return;
		}

		Sequence sq = new Sequence(seq[pos1].getType(), new SLList<Character>());
		for (int i = 0; i < seq[pos1].getBases().length; i++)
			sq.getBases().add(seq[pos1].getBases().getValue(i));
		seq[pos2] = sq;
	}

	/**
	 * Swap methd
	 * 
	 * @param pos1
	 *            : Sequence array position 1
	 * @param start1
	 *            : Sequence array start 1
	 * @param pos2
	 *            : Sequence array position 2
	 * @param start2
	 *            : Sequence array start 2
	 * @param seq
	 *            : Sequence array
	 */
	public void Swap(int pos1, int start1, int pos2, int start2, Sequence[] seq) {
		if (seq[pos1] == null || seq[pos1].getBases().length == 0) {
			System.out.println("There is no sequences to swap at positions: " + pos1);
			return;
		}
		
		if(seq[pos2] == null || seq[pos2].getBases().length == 0) {
			System.out.println("There is no sequences to swap at positions: " + pos2);
			return;
		}

		if (!seq[pos1].getType().equals(seq[pos2].getType())) {
			System.out.println("Have to be same type to swap");
			return;
		}

		if (start1 < 0 || start1 > seq[pos1].getBases().length) {
			System.out.println("Start position of swap is not valid.");
			return;
		}

		if (start2 < 0 || start2 > seq[pos2].getBases().length) {
			System.out.println("Start position of swap is not valid.");
			return;
		}

		SLList<Character> sl1 = new SLList<Character>();
		SLList<Character> sl2 = new SLList<Character>();
		SLList<Character> sl3 = new SLList<Character>();
		SLList<Character> sl4 = new SLList<Character>();

		for (int i = 0; i < start1; i++)
			sl1.add(seq[pos1].getBases().getValue(i));

		for (int i = start1; i < seq[pos1].getBases().length; i++)
			sl2.add(seq[pos1].getBases().getValue(i));

		for (int i = 0; i < start2; i++)
			sl3.add(seq[pos2].getBases().getValue(i));

		for (int i = start2; i < seq[pos2].getBases().length; i++)
			sl4.add(seq[pos2].getBases().getValue(i));

		seq[pos1] = new Sequence(seq[pos1].getType(), sl1);
		seq[pos1].getBases().insertList(sl4, seq[pos1].getBases().length - 1);

		seq[pos2] = new Sequence(seq[pos2].getType(), sl3);
		seq[pos2].getBases().insertList(sl2, seq[pos2].getBases().length - 1);
	}

	/**
	 * Overlap method
	 * 
	 * @param pos1
	 *            : Sequence array position 1
	 * @param pos2
	 *            : Sequence array position 2
	 * @param seq
	 *            : Sequence array
	 */
	public void Overlap(int pos1, int pos2, Sequence[] seq) {
		if (!seq[pos1].getType().equals(seq[pos2].getType())) {
			System.out.println("Sequences must be of the same type for overlap mathod.");
			return;
		}

		if (seq[pos1] == null || seq[pos1].getBases() == null || seq[pos2] == null || seq[pos2].getBases() == null) {
			System.out.println("There is no overlap in these sequences.");
			return;
		}

		boolean over = true; // return true when overlapped
		String s; 

		for (int i = seq[pos1].getBases().length > seq[pos2].getBases().length
				? seq[pos1].getBases().length - seq[pos2].getBases().length : 0; i < seq[pos1].getBases().length; i++) {
			over = true; 
			s = "";
			for (int j = i; j < seq[pos1].getBases().length; j++) {
				s += seq[pos1].getBases().getValue(j).toString();
				if (!seq[pos1].getBases().getValue(j).equals(seq[pos2].getBases().getValue(j - i))) {
					over = false;
					break;
				}
			}

			if (over) {
				System.out.println("Overlap starts at pos " + i + "; bases that overlap " + s);
				break;
			}
		}

		if (!over){
			System.out.println("There is no overlap in these sequences.");
			}
	}

	/**
	 * Transcribe method
	 * 
	 * @param pos
	 *            : Sequence array position
	 * @param seq
	 *            : Sequence array
	 */
	public void Transcribe(int pos, Sequence[] seq) {
		if (seq[pos] == null || seq[pos].getBases().length == 0) {
			System.out.println("Sequence type must be DNA.");
			return;
		}

		if (!seq[pos].getType().equals("DNA")) {
			System.out.println("Sequence type must be DNA.");
			return;
		}

		seq[pos].setType("RNA");

		for (int i = 0; i < seq[pos].getBases().length; i++) {
			if (seq[pos].getBases().getValue(i).toString().equals("T")) {
				seq[pos].getBases().remove(i);
				seq[pos].getBases().insert(i, Character.valueOf('U'));
			}

			if (seq[pos].getBases().getValue(i).toString().equals("A")) {
				seq[pos].getBases().remove(i);
				seq[pos].getBases().insert(i, Character.valueOf('U'));
			}

			else if (seq[pos].getBases().getValue(i).toString().equals("U")) {
				seq[pos].getBases().remove(i);
				seq[pos].getBases().insert(i, Character.valueOf('A'));
			}

			else if (seq[pos].getBases().getValue(i).toString().equals("G")) {
				seq[pos].getBases().remove(i);
				seq[pos].getBases().insert(i, Character.valueOf('C'));
			}

			else if (seq[pos].getBases().getValue(i).toString().equals("C")) {
				seq[pos].getBases().remove(i);
				seq[pos].getBases().insert(i, Character.valueOf('G'));
			}
		}
		seq[pos].getBases().reverse();
	}

	/**
	 * Translate method
	 * 
	 * @param pos
	 *            : Sequence array position
	 * @param seq
	 *            : Sequence array
	 */
	public void Translate(int pos, Sequence[] seq) {
		if (seq[pos] == null || seq[pos].getBases().length == 0) {
			System.out.println("Sequence type must be RNA.");
			return;
		}

		if (!seq[pos].getType().equals("RNA")) {
			System.out.println("Sequence type must be RNA.");
			return;
		}

		if (seq[pos].getBases().length < 6) {
			System.out.println("Given bases is too short to perform translation");
			return;
		}

		int trans = -1;
		for (int i = 0; i < seq[pos].getBases().length - 3; i++) {
			if (seq[pos].getBases().getValue(i).equals(Character.valueOf('A'))
					&& seq[pos].getBases().getValue(i + 1).equals(Character.valueOf('U'))
					&& seq[pos].getBases().getValue(i + 2).equals(Character.valueOf('G'))) {
				trans = i;
				break;
			}
		}

		if (trans == -1) {
			System.out.println("There is no open reading frame in this sequence.");
			return;
		}

		boolean isStopped = false; // check whether stop codon is in the frame or not
		Sequence sq = new Sequence("AA ", new SLList<Character>()); 
		sq.getBases().add(Character.valueOf('M'));

		int index = trans;

		while (index + 5 < seq[pos].getBases().length) {
			index += 3;
			sq.getBases().add(Character.valueOf(toAminoAcid(seq[pos].getBases().getValue(index),
					seq[pos].getBases().getValue(index + 1), seq[pos].getBases().getValue(index + 2))));
			if (sq.getBases().getLast().getElement().equals(Character.valueOf('!'))) {
				sq.getBases().remove(sq.getBases().length - 1);
				isStopped = true;
				break;
			}
		}

		if (isStopped) { // case when the stop codon exists.
			seq[pos] = sq;
		} else { // case when the stop codon does not exists.
			System.out.println("There is no open reading frame in this sequence.");
			}
	}
	/**
	 * toAminoAcid
	 * 
	 * @param ch1
	 * @param ch2
	 * @param ch3
	 * @return char
	 */
	public char toAminoAcid(char ch1, char ch2, char ch3) {
		String a = Character.valueOf(ch1).toString() + Character.valueOf(ch2).toString()
				+ Character.valueOf(ch3).toString();
		// Converting codon forms to amino acid abbreviation form
		// ! is the stop codon
		switch (a) {
		case "UUU":
			return 'F';
		case "UUC":
			return 'F';
		case "UUA":
			return 'L';
		case "UUG":
			return 'L';
		case "CUU":
			return 'L';
		case "CUC":
			return 'L';
		case "CUA":
			return 'L';
		case "CUG":
			return 'L';
		case "AUU":
			return 'I';
		case "AUC":
			return 'I';
		case "AUA":
			return 'I';
		case "AUG":
			return 'M';
		case "GUU":
			return 'V';
		case "GUC":
			return 'V';
		case "GUA":
			return 'V';
		case "GUG":
			return 'V';
		case "UCU":
			return 'S';
		case "UCC":
			return 'S';
		case "UCA":
			return 'S';
		case "UCG":
			return 'S';
		case "CCU":
			return 'P';
		case "CCC":
			return 'P';
		case "CCA":
			return 'P';
		case "CCG":
			return 'P';
		case "ACU":
			return 'T';
		case "ACC":
			return 'T';
		case "ACA":
			return 'T';
		case "ACG":
			return 'T';
		case "GCU":
			return 'A';
		case "GCC":
			return 'A';
		case "GCA":
			return 'A';
		case "GCG":
			return 'A';
		case "UAU":
			return 'Y';
		case "UAC":
			return 'Y';
		case "UAA":
			return '!';
		case "UAG":
			return '!';
		case "CAU":
			return 'H';
		case "CAC":
			return 'H';
		case "CAA":
			return 'Q';
		case "CAG":
			return 'Q';
		case "AAU":
			return 'N';
		case "AAC":
			return 'N';
		case "AAA":
			return 'K';
		case "AAG":
			return 'K';
		case "GAU":
			return 'D';
		case "GAC":
			return 'D';
		case "GAA":
			return 'E';
		case "GAG":
			return 'E';
		case "UGU":
			return 'C';
		case "UGC":
			return 'C';
		case "UGA":
			return '!';
		case "UGG":
			return 'W';
		case "CGU":
			return 'R';
		case "CGC":
			return 'R';
		case "CGA":
			return 'R';
		case "CGG":
			return 'R';
		case "AGU":
			return 'S';
		case "AGC":
			return 'S';
		case "AGA":
			return 'R';
		case "AGG":
			return 'R';
		case "GGU":
			return 'G';
		case "GGC":
			return 'G';
		case "GGA":
			return 'G';
		case "GGG":
			return 'G';
		default:
			return '~';
		}
	}
}
