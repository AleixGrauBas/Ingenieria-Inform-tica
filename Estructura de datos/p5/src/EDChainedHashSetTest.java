import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class EDChainedHashSetTest {

	static private String vChar = "abcedefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	private String generateNextString(String s) {
		char c = s.charAt(0);
		c = vChar.charAt((vChar.indexOf(c) + 1) % s.length());

		return s.substring(1) + c;

	}

	private List<String> generateStrings(int nElem, String first) {
		List<String> l = new ArrayList();
		l.add(first);
		for (int i = 1; i < nElem; i++)
			l.add(generateNextString(l.get(l.size() - 1)));

		l.add(nElem / 2, null);
		return l;
	}

	private Set<String> createSet() {
		return new EDChainedHashSet<String>();
	}

	@Test
	public final void testChainedHashSetIntDouble() {
		System.out.println("\nValidando constructor con capacidada y factor de carga inicial...");

		System.out.println("set = new EDChainedHashSet<String>(15, 1.5)");
		Set<String> set = new EDChainedHashSet<String>(15, 1.5);
		System.out.println(" set =  " + set);

		assertTrue("set.isEmpty()", set.isEmpty());
		assertEquals("set.size()", 0, set.size());
	}

	@Test
	public final void testChainedHashSetInt() {
		System.out.println("\nValidando constructor con capacidad inicial...");

		System.out.println("set = new EDChainedHashSet(15)");
		Set<String> set = new EDChainedHashSet<String>(15);
		System.out.println(" set = " + set);

		assertTrue("set.isEmpty()", set.isEmpty());
		assertEquals("set.size()", 0, set.size());
	}

	@Test
	public final void testChainedHashSetDouble() {
		System.out.println("\nValidando constructor con factor de carga inicial...");

		System.out.println(" set = new EDChainedHashSet<String>(1.5)");
		Set<String> set = new EDChainedHashSet<String>(1.5);
		System.out.println(" set =  " + set);

		assertTrue("set.isEmpty()", set.isEmpty());
		assertEquals("set.size()", 0, set.size());
	}

	@Test
	public final void testChainedHashSet() {
		System.out.println("\nValidando constructor por defecto...");

		System.out.println(" set = new EDChainedHashSet<String>()");
		Set<String> set = createSet();
		System.out.println(" set = " + set);

		assertTrue("set.isEmpty()", set.isEmpty());
		assertEquals("set.size()", 0, set.size());
	}

	@Test
	public final void testAdd() {
		System.out.println("\nValidando el metodo add()...");
		Set<String> set = createSet();
		System.out.println(" set = " + set);

		assertTrue("set.isEmpty()", set.isEmpty());
		assertEquals("set.size()", 0, set.size());

		List<String> l = generateStrings(12, "dispersion");

		int i = 0;
		for (String s : l) {
			System.out.println();
			System.out.println("set.add(" + s + ")");
			boolean added = set.add(s);
			assertTrue("set.add(" + s +")", added);
			System.out.println(" set = " + set);
			assertFalse("set.ismepty()", set.isEmpty());
			assertEquals("set.szie()", ++i, set.size());
		}

		for (String s : l) {
			System.out.println();
			System.out.println("set.add(" + s + ")");
			boolean added = set.add(s);
			assertFalse("set.add(" + s +")", added);
			System.out.println(" set = " + set);
			assertFalse("set.ismepty()", set.isEmpty());
			assertEquals("set.size()", 13, set.size());
		}

	}

	@Test
	public final void testContains() {
		System.out.println("\nValidando el metodo contains()...");
		Set<String> set = createSet();
		System.out.println(" set = " + set);

		List<String> l = generateStrings(18, "dispersion");
		int count = 0;
		for (int i = 0; i < l.size(); i += 2) {
			String s = l.get(i);
			System.out.println();
			System.out.println("set.add(" + s + ")");
			boolean added = set.add(s);
			count++;
			System.out.println(" set = " + set);
			assertTrue(added);
			assertFalse("set.isEmpty()", set.isEmpty());
			assertEquals("set.size()", count, set.size());
		}

		for (int i = 0; i < l.size(); i++) {
			String s = l.get(i);
			System.out.println();
			System.out.println("set.contains(" + s + ")");
			boolean actual = set.contains(s);
			boolean expected = (i % 2 == 0) ? true : false;
			assertEquals("set.contains(" + s+ ")", expected, actual);
			System.out.println(" set = " + set);
		}

	}

	@Test
	public final void testRemove() {
		System.out.println("\nValidando el metodo remove()...");
		Set<String> set = createSet();
		System.out.println(" set = " + set);

		int count = 0;
		List<String> l = generateStrings(18, "dispersion");
		for (int i = 0; i < l.size(); i += 2) {
			String s = l.get(i);
			System.out.println();
			System.out.println("set.add(" + s + ")");
			set.add(s);
			System.out.println(" set = " + set);
			count++;
		}

		for (int i = 0; i < l.size(); i++) {
			System.out.println();
			String s = l.get(i);
			System.out.print("set.remove(" + s + ")");
			boolean actual = set.remove(s);
			boolean expected = (i % 2 == 0) ? true : false;
			if (expected)  count--;
			System.out.println(" -> " + actual);
			System.out.println(" set = " + set);
			assertEquals("set.remove()", expected, actual);
			assertEquals("set.size()", count, set.size());
		}
	}

	@Test
	public final void testSize() {
		System.out.println("\nValidando el metodo size()...");
		Set<String> set = createSet();
		System.out.println(" set = " + set);
		assertEquals("set.size()", 0, set.size());

		List<String> l = generateStrings(12, "dispersion");
		int count = 0;
		for (int i = 0; i < l.size(); i++) {
			String s = l.get(i);
			System.out.println();
			System.out.println("set.add(" + s + ")");
			set.add(s);
			System.out.println(" set = " + set);
			count++;
			assertEquals("set.size()", count, set.size());
		}

		for (int i = 0; i < l.size(); i++) {
			String s = l.get(i);
			System.out.println();
			System.out.println("set.remove(" + s + ")");
			set.remove(s);
			System.out.println(" set = " + set);
			count--;
			assertEquals("set.size()", count, set.size());
		}
	}

	@Test
	public final void testIsEmpty() {
		System.out.println("\nValidando el metodo isEmpty()...");
		Set<String> set = createSet();
		System.out.println(" set = " + set);
		System.out.println("set.isEmpty()");
		boolean empty = set.isEmpty();
		System.out.println(empty);
		assertTrue("set.isEmpty()",empty);

		List<String> l = generateStrings(12, "dispersion");

		for (String s : l) {
			System.out.println();
			System.out.println("set.add(" + s + ")");
			set.add(s);
			System.out.println(" set = " + set);
			System.out.print("set.isEmpty() -> ");
			empty = set.isEmpty();
			System.out.println(empty);
			assertFalse(empty);

			System.out.println();
			System.out.print("set.remove(" + s + ")");
			set.remove(s);
			System.out.println(" set = " + set);
			System.out.print("set.isEmpty() -> ");
			empty = set.isEmpty();
			System.out.println(empty);
			assertTrue("set.isEmpty()",empty);
		}
	}

	@Test
	public final void testClear() {
		System.out.println("\nValidando el metodo clear()...");
		Set<String> set = createSet();
		System.out.println();
		System.out.println(" set = " + set);

		List<String> l = generateStrings(20, "dispersion");

		for (int i = 0; i < l.size(); i++) {
			System.out.println();
			String s = l.get(i);
			System.out.println("set.add(" + s + ")");
			set.add(s);
			System.out.println(" set = " + set);

			if ((i + 1) % 5 == 0) {
				System.out.println();
				System.out.println("set.clear()");
				set.clear();
				System.out.println(" set = " + set);
				assertEquals(" set.size()", 0, set.size());
				assertTrue(" set.isEmpety()", set.isEmpty());
			} else
				assertNotEquals("set.size()", 0, set.size());
		}
	}

	private static String allSemillas[] = { "hola", "dispersion", "estructra", "delos", "datos" };

	@Test
	public final void testContainsAll() {
		System.out.println("\nValidando el metodo containsAll()...");

		Set<String> set = createSet();
		System.out.println(" -> " + set);

		List<String> l = new ArrayList<String>();

		System.out.print("containsAll(" + l + ")");
		boolean result = set.containsAll(l);
		System.out.println(" -> " + result);
		assertTrue(result);

		l = generateStrings(12, "hola");
		for (int i = 0; i < 7; i++)
			set.add(l.get(i));

		System.out.println(" -> " + set);
		System.out.print("containsAll(" + l.subList(0, 6) + ")");
		result = set.containsAll(l.subList(0, 6));
		System.out.println(" -> " + result);
		assertTrue(result);

		System.out.println(" -> " + set);
		System.out.print("containsAll(" + l.subList(6, l.size()) + ")");
		result = set.containsAll(l.subList(6, l.size()));
		System.out.println(" -> " + result);
		assertFalse(result);

		System.out.println(" -> " + set);
		System.out.print("containsAll(" + l + ")");
		result = set.containsAll(l);
		System.out.println(" -> " + result);
		assertFalse(result);

		l.clear();
		System.out.println(" -> " + set);
		System.out.print("containsAll(" + l + ")");
		result = set.containsAll(l);
		System.out.println(" -> " + result);
		assertTrue(result);
	}

	@Test
	public final void testAddAll() {
		System.out.println("\nValidando el metodo addAll()...");
		Set<String> set = createSet();
		System.out.println(" -> " + set);

		for (String semilla : allSemillas) {
			set.clear();
			List<String> l = generateStrings(6, semilla);
			System.out.print("addAll(" + l + ")");
			boolean modified = set.addAll(l);
			System.out.println(" -> " + modified);
			System.out.println(" -> " + set);
			for (String s : l) {
				System.out.print("contains(" + s + ")");
				boolean added = set.contains(s);
				System.out.println(" -> " + added);
				assertTrue(added);
			}

			int oldSize = l.size();
			System.out.print("addAll(" + l + ")");
			modified = set.addAll(l);
			System.out.println(" -> " + modified);
			System.out.println(" -> " + set);
			assertFalse(modified);
			assertEquals(oldSize, set.size());
		}
	}

	@Test
	public final void testRetainAll() {
		System.out.println("\nValidando el metodo retainAll()...");
		Set<String> set = createSet();
		System.out.println(" set = " + set);

		for (String semilla : allSemillas) {
			System.out.println();
			System.out.println("set.clear()");
			set.clear();
			System.out.println(" set = " + set);

			System.out.println();
			List<String> l = generateStrings(12, semilla);
			set.addAll(l);
			System.out.print("set.addAll(" + l + ")");
			System.out.println(" set = " + set);

			System.out.println();
			System.out.print("set.retainAll(" + l.subList(0, 7) + ")");
			boolean modified = set.retainAll(l.subList(0, 7));
			System.out.println(" -> " + modified);
			assertTrue("set.retainAll(" + l.subList(0, 7) + ")", modified);
			System.out.println(" set = " + set);

			for (String s : l.subList(0, 7)) {
				assertEquals("set.contains(" + s + ")", true, set.contains(s));
			}

			for (String s : l.subList(7, l.size())) {
				assertEquals("set.contains(" + s + ")", false, set.contains(s));
			}

			int oldSize = 7;
			System.out.println();
			System.out.print("set.retainAll(" + l.subList(0, 7) + ")");
			modified = set.retainAll(l.subList(0, 7));
			System.out.println(" -> " + modified);
			System.out.println(" set = " + set);
			assertFalse("set.retainAll(" + l.subList(0, 7) + ")", modified);
			assertEquals("aet.size()", oldSize, set.size());
		}
	}

	@Test
	public final void testRemoveAll() {
		System.out.println("\nValidando el metodo removeAll()...");
		Set<String> set = createSet();
		System.out.println(" -> " + set);

		for (String semilla : allSemillas) {
			System.out.println("clear()");
			set.clear();
			List<String> l = generateStrings(12, semilla);
			set.addAll(l);
			System.out.print(" addAll(" + l + ")");
			System.out.println(" -> " + set);
			System.out.print("removeAll(" + l.subList(0, 7) + ")");
			boolean modified = set.removeAll(l.subList(0, 7));
			System.out.println(" -> " + modified);
			System.out.println(" -> " + set);
			assertTrue(modified);

			for (String s : l.subList(0, 7)) {
				System.out.print("contains(" + s + ")");
				boolean removed = set.contains(s);
				System.out.println(" -> " + removed);
				assertFalse(removed);
			}

			int oldSize = l.size() - 7;
			System.out.print("removeAll(" + l.subList(0, 7) + ")");
			modified = set.removeAll(l.subList(0, 7));
			System.out.println(" -> " + modified);
			System.out.println(" -> " + set);
			assertFalse(modified);
			assertEquals(oldSize, set.size());
		}
	}

	//@Test
	public final void testIterator() {
		System.out.println("\nValidando el metodo iterator()...");

		Set<String> set = createSet();
		System.out.println(" -> " + set);

		System.out.print("iterator()");
		Iterator<String> iter = set.iterator();
		System.out.println(" -> iterador contruido con exito");
	}

	//@Test
	public final void testIteratorHasNext() {
		System.out.println("\nValidando el metodo Iterator.hasNext()...");

		Set<String> set = createSet();
		System.out.println(" -> " + set);
		System.out.println("iterator()");

		// iterador de un conjunto vacio
		Iterator<String> iter = set.iterator();
		System.out.print("hasNext()");
		boolean result = iter.hasNext();
		System.out.println(" -> " + result);
		assertFalse(result);

		// Iterador con un sólo elemento
		set.add(null);
		System.out.println(" -> " + set);
		System.out.println("iterator()");
		iter = set.iterator();
		System.out.print("hasNext()");
		result = iter.hasNext();
		System.out.println(" -> " + result);
		assertTrue(result);

		// Iterador con varios elementos
		List<String> l = generateStrings(10, "dispersion");
		set.clear();
		for (String s : l)
			set.add(s);

		System.out.println(" -> " + set);
		iter = set.iterator();
		for (int i = 0; i < 11; i++) {
			System.out.print("hasNext()");
			result = iter.hasNext();
			System.out.println(" -> " + result);
			assertTrue(result);
			System.out.print("next()");
			System.out.println(" -> " + iter.next());
		}

		System.out.print("hasNext()");
		result = iter.hasNext();
		System.out.println(" -> " + result);
		assertFalse(result);
	}

	//@Test
	public final void testIteratorNext() {
		System.out.println("\nValidando el metodo Iterator.next()...");
		Set<String> set = createSet();
		System.out.println(" -> " + set);

		// Iterador con varios elementos
		Set<String> saux = new HashSet<String>(generateStrings(10, "dispersion"));

		for (String s : saux)
			set.add(s);

		System.out.println(" -> " + set);
		Iterator<String> iter = set.iterator();
		System.out.println("iterator()");

		for (int i = 0; i < 11; i++) {
			System.out.println("next()");
			String str = iter.next();
			System.out.println(" -> " + str);

			if (!saux.contains(str))
				System.out.println("Elemento incorrecto");

			assertTrue(saux.contains(str));
			saux.remove(str);

		}

		System.out.print("hasNext()");
		boolean result = iter.hasNext();
		System.out.println(" -> " + result);
		assertFalse(result);
	}

	//@Test
	public final void testIteratorRemove() {
		System.out.println("\nValidando el metodo Iterator.remove()...");
		Set<String> set = createSet();
		System.out.println(" -> " + set);

		// Iterador con varios elementos
		Set<String> saux = new HashSet<String>(generateStrings(10, "dispersion"));

		for (String s : saux)
			set.add(s);

		System.out.println(" -> " + set);
		Iterator<String> iter = set.iterator();
		System.out.println("iterator()");

		for (int i = 0; i < 11; i++) {
			System.out.print("next()");
			String str = iter.next();
			System.out.println(" -> " + str);
			saux.remove(str);

			if (i % 2 == 0) {
				System.out.println("remove()");
				iter.remove();
				System.out.println(" -> " + set);
				System.out.print("cointains(" + str + ")");
				boolean erased = set.contains(str);
				System.out.println("-> " + set.contains(str));
				assertFalse(erased);
			}
		}

		System.out.println("iterator()");
		iter = set.iterator();
		for (int i = 0; i < 5; i++) {
			System.out.print("next()");
			String str = iter.next();
			System.out.println(" -> " + str);
			saux.remove(str);

			System.out.println("remove()");
			iter.remove();
			System.out.println(" -> " + set);
			System.out.print("cointains(" + str + ")");
			boolean erased = set.contains(str);
			System.out.println("-> " + set.contains(str));
			assertFalse(erased);
		}

		System.out.print("hasNext()");
		boolean result = iter.hasNext();
		System.out.println(" -> " + result);
		assertFalse(result);
	}

	private int testRehashData[][] = { { 10, 100 }, { 10, 200 }, { 20, 150 }, { 15, 200 } };

	@Test
	public final void testRehash() {
		for (int comb = 0; comb < testRehashData.length; comb++) {
			double loadFactor = (double)(testRehashData[comb][1])/100;
			int threshold = (int) Math.round(loadFactor * (double)testRehashData[comb][0]);

			System.out.println("PARAMETROS DE REHASH: (factorDeCarga: " + loadFactor + ", capacidad inicial: " 
					+ testRehashData[comb][0] + ", umbral: " + threshold + ")");

			EDChainedHashSet<String> set = new EDChainedHashSet<String>(testRehashData[comb][0], loadFactor);
			System.out.println(" set = " + set);

			List<String> l = generateStrings(threshold + 5, "primera");

			int capacity = set.getCapacity();
			System.out.println("Capacity: " + capacity);

			for (int i = 0; i < l.size(); i++) {
				System.out.println();
				String str = l.get(i);
				System.out.println("set.add(" + str + ")");
				set.add(str);
				System.out.println(" set = " + set);
				if (set.size() > threshold) {
					assertNotEquals("capacity", capacity, set.getCapacity());
					threshold = (int) Math.round(loadFactor * set.getCapacity());
				}
				else
					assertEquals("capacity", capacity, set.getCapacity());
				capacity = set.getCapacity();
			}
			
			// Tras el rehasing comprueba que los elementos aun se pueden encontrar
			for(String str: l) {
				System.out.println();
				System.out.println("set.contains(" + str +")");
				boolean found = set.contains(str);
				System.out.println(" set = " + set);
				assertTrue("set.contains(str)", found);
			}
				
		}
	}
}
