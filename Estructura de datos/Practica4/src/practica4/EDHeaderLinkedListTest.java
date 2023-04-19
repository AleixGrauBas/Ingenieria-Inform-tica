package practica4;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;


public class EDHeaderLinkedListTest {
	private static Random generator;
	
	/**
	 * Crea una lista de enteros vacía
	 */
	private List<Integer> createList()  {
		return  new EDHeaderLinkedList<Integer>();
	}
	
	/**
	 * Crea y rellena una lista de enteros
	 */
	private List<Integer> createFillList() {
		List<Integer> l = createList();
		for (int i=0; i<20; i++)
			l.add(i);
		return l;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		generator = new Random();
	}

	
	@Test
	public void testConstructor() {
		System.out.println("\nValidando constructor por defecto...\n");

		System.out.println("new EDHeaderLinkedList()");
		List<Integer> l = createList();
		
		System.out.println(" LISTA RESULTADO " + l);
		assertEquals("Size:", 0, l.size());
		assertEquals("isEmpty: ", true, l.isEmpty());
	}

	
	@Test
	public void testAdd() {
		System.out.println("\nValidando metodo add(T)...\n");

		System.out.println("Creando lista vacia \nl = new EDHeaderLinkedList()");
		List<Integer> l = createList();

		System.out.println("LISTA INICIAL\n l = " + l + "\n");
		
		for (int i = 0; i< 20; i++) {
			System.out.println("\nl.add(" + i + ")");
			assertEquals("add" + i , true, l.add(i));
			System.out.println("LISTA RESULTADO\n" + l);
			assertEquals("isEmpty() devuelve true", false, l.isEmpty());
			assertEquals("size() devuelve un valor incorrecto", i+1 ,l.size());
		}
	}
	
	@Test
	public void testClear() {
		System.out.println("\nValidando metodo clear...\n");

		System.out.println("Creando lista \nl = new EDHeaderLinkedList()");
		List<Integer> l = createFillList();

		System.out.println("LISTA INICIAL\n l = " + l + "\n");
		System.out.println("l.clear()");
		l.clear();
		System.out.println("LISTA RESULTADO\n l = " + l);
		assertEquals("l-isEmpty() devuelve true", true, l.isEmpty());
		assertEquals("l.size() devuelve un valor incorrecto", 0 ,l.size());
	}
	
	@Test
	public void testGet() {
		System.out.println("\nValidando metodo get...\n");

		System.out.println("Creando lista \nl = new EDHeaderLinkedList()");
		List<Integer> l = createFillList();

		for (int i=0; i < 20; i++) {
			System.out.println("\nl = " + l );
			System.out.println("l.get(" + i +")");
			System.out.println("RESULTADO ESPERADO: " + i);
			int obtenido = (int)l.get(i);
			System.out.println("RESULTADO OBTENIDO: " + i);
			assertEquals("resultado incorrecto", i, obtenido);
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetExceptionDown() {
		System.out.println("\nValidando excpeciones del metodo get...\n");
		List<Integer> l = createFillList();
		System.out.println("\nl = " + l );
		System.out.println("l.get(-1)");
		System.out.println("Debería producir la excepcion IndexOutOfBoundsException");
		l.get(-1);
		System.out.println("...excepcion no producida");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetExceptionUp() {
		System.out.println("\nValidando excepciones del metodo get...\n");
		List<Integer> l = createFillList();

		System.out.println("\nl = " + l );
		System.out.println("l.get(20)");
		System.out.println("Debería producir la excepcion IndexOutOfBoundsException");
		
		l.get(20);
		System.out.println("...excepcion no producida");

	}
	
	@Test
	public void testAddInt() {
		System.out.println("\nValidando  metodo add(int, T)...\n");

		System.out.println("Creando lista \nl = new EDHeaderLinkedList()");
		List<Integer> l = createList();
		System.out.println("LISTA INICIAL\n l = " + l + "\n");
		
		List<Integer> truth = new LinkedList<Integer>();
		for (int i = 0; i< 20; i++) {
			int pos = generator.nextInt(i+1);
			System.out.println("\nl = " + l );
			System.out.println("l.add("+ pos +", "+ i +")");
			l.add(pos, i);

			System.out.println("RESULTADO:\nl = " + l);

			truth.add(pos, i);
			assertEquals("l.get(" + pos + ") devuelve un resultado incorrecto", truth.get(pos), l.get(pos));

			assertEquals("l.empty() devuelve true", false, l.isEmpty());
			assertEquals("l.size() devuelve un valor incorrecto", i+1 ,l.size());

		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddExceptionDown() {
		System.out.println("\nValidando excepciones del metodo add(int, T)...\n");

		List<Integer> l = createFillList();
		System.out.println("\nl = " + l );
		System.out.println("l.add(-1, 0)");
		System.out.println("Debería producir la excepcion IndexOutOfBoundsException");
		l.add(-1, 0);
		System.out.println("...excepcion no producida");

	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testAddExceptionUp() {
		System.out.println("\nValidando excepciones del metodo add...\n");

		List<Integer> l = createFillList();
		System.out.println("\nl = " + l );
		System.out.println("l.add(21, 0)");
		System.out.println("Debería producir la excepcion IndexOutOfBoundsException");
		l.add(21, 0);
		System.out.println("...excepcion no producida");

	}
	
	@Test
	public void testContains() {
		System.out.println("\nValidando metodo contains...\n");

		List<Integer> l = createList();
		
		for (int i = 0; i< 20; i ++) {
			int pos = generator.nextInt(i+1); 
			l.add(pos, i*2);
		}
		System.out.println("\nl = " + l );

		for(int i=0; i < 20; i++) {
			System.out.println("\nl = " + l );
			System.out.println("l.contains(" + i + ")");
			boolean esperado = (i%2 == 0);
			boolean obtenido = l.contains(i);
			assertEquals("Resultado incorrecto", esperado, obtenido);
		}
	}
	
	@Test
	public void testRemove() {
		System.out.println("\nValidando metodo remove(T)...\n");

		List<Integer> l = createList();

		for (int i = 0; i< 20; i ++) {
			int pos = generator.nextInt(i+1); 
			l.add(pos, i);
		}

		for(int i=0; i < 20; i += 2) {
			System.out.println("\nl = " + l );
			System.out.println("l.remove((Integer)" + i + ")");
			assertEquals(true, l.remove((Integer)i));
			System.out.println("l = " + l);
			assertEquals("l.contains(Integer)" + i + ")", false, l.contains((Integer)i));
			assertEquals("l.size()", 20-(i/2)-1, l.size());
		}
	}
	
	@Test
	public void testRemoveInt() {
		System.out.println("\nValidando metodo remove(int)...\n");
		List<Integer> l = createList();

		List<Integer> truth = new LinkedList<Integer>();
		for (int i = 0; i< 20; i ++) {
			System.out.println("\nl = " + l );
			int pos = generator.nextInt(i+1);
			System.out.println("l.add("+ pos +", "+ i +")");
			l.add(pos, i);
			System.out.println("l = " + l );
			truth.add(pos,i);
		}
		
		for(int i=0; i < 10; i++) {
			System.out.println("\nl = " + l );
			int pos = generator.nextInt(l.size()); 
			int el = truth.get(pos);
			truth.remove(pos);
			System.out.println("l.remove(" + el + ")");
			assertEquals(el, (int)l.remove(pos));
			System.out.println("l = " + l );
			assertEquals("l.contains(" + el+  ")", false, l.contains(el));
			assertEquals("l.size()", 20-i-1, l.size());
		}

		System.out.println("\nl = " + l );
		for(int i: truth) {
			assertEquals("l.contains("+i+")", true, l.contains(i));
		}	
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveExceptionDown() {
		System.out.println("\nValidando excepciones del metodo remove(int)...\n");

		List<Integer> l = createFillList();
		System.out.println("\nl = " + l );
		System.out.println("l.remove(-1)");
		System.out.println("Debería producir la excepcion IndexOutOfBoundsException");
		l.remove(-1);
		System.out.println("...excepcion no producida");
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testRemoveExceptionUp() {
		System.out.println("\nValidando excepciones del metodo remove(int)...\n");

		List<Integer> l = createFillList();
		System.out.println("\nl = " + l );
		System.out.println("l.remove(21)");
		System.out.println("Debería producir la excepcion IndexOutOfBoundsException");
		l.remove(21);
		System.out.println("...excepcion no producida");
	}
	
	@Test
	public void testSet() {
		System.out.println("\nValidando  metodo remove(int)...\n");
		List<Integer> l = createList();

		for (int i = 0; i< 20; i ++) {
			int pos = generator.nextInt(i+1); 
			l.add(pos, i);
		}
		System.out.println("\nl = " + l );
		
		for(int i=0; i < 20; i++) {
			System.out.println("\nl = " + l );
			int pos = generator.nextInt(l.size()); 
			int el = l.get(pos);
			System.out.println("l.set(" + pos + ", " + (el+1000) + ")");
			assertEquals( el, (int)l.set(pos, el+1000));
			System.out.println("l = " + l );
			assertEquals("l.get(" + pos + ")", el+1000, (int)l.get(pos));
			assertEquals("l.contains(" + el + ")", false, l.contains(el));
			assertEquals("l.size()", 20, l.size());
		}
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetExceptionDown() {
		System.out.println("\nValidando excepciones del metodo Set(int, T)...\n");
		List<Integer> l = createFillList();
		System.out.println("\nl = " + l );

		System.out.println("l.set(-1)");
		System.out.println("Debería producir la excepcion IndexOutOfBoundsException");
		l.set(-1, 0);
		System.out.println("...excepcion no producida");

	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testSetExceptionUp() {
		System.out.println("\nValidando excepciones del metodo Set(int, T)...\n");
		List<Integer> l = createFillList();
		System.out.println("\nl = " + l );

		System.out.println("l.set(20,0)");
		System.out.println("Debería producir la excepcion IndexOutOfBoundsException");
		l.set(20, 0);
		System.out.println("...excepcion no producida");

	}
	
	@Test
	public void testIndexOf() {
		System.out.println("\nValidando metodo indexOf(T)...\n");
		List<Integer> l = createFillList();
		System.out.println("\nl = " + l );
		
		for(int i=-10; i < 30 ; i++) {
			System.out.println("\nl = " + l );
			System.out.println("l.indexOf(" + i + ") -> " + l.indexOf(i));
			if (i >= 0 && i < 20) {
				assertEquals( i, l.indexOf(i));
			} else {
				assertEquals( -1, l.indexOf(i));
			}
	
		}
	}

	@Test
	public void testlastIndexOf() {
		System.out.println("\nValidando método lastIndexOf(T)...\n");
		List<Integer> l = createFillList();
		for (int i=0; i < l.size()/2; i++)
			l.set(i + 10, l.get(i));


		for(int i=-5; i < 16 ; i++) {
			System.out.println("\nl = " + l );
			System.out.println("l.lastIndexOf(" + i + ") -> " + l.lastIndexOf(i));
			if (i >= 0 && i < 10) {
				assertEquals(i+10, l.lastIndexOf(i));
			} else {
				assertEquals(-1, l.lastIndexOf(i));
			}

		}
	}

	@Test
	public void testRetainAll() {
        System.out.println("\nValidando metodo retainAll(T)...\n");

        Collection<Integer> set = new HashSet<Integer>();
		List<Integer> l = createList();


		for (int i = 0; i < 10; i++)
			l.add(i);
		System.out.println("\nl = " + l );

        for(int i = 0; i < 15; i+=2)
        	set.add(i);
        System.out.println("set = " + set);

        boolean ret = l.retainAll(set);

        System.out.println("\nl.retainAll(set)");
        assertTrue(ret);
        System.out.println("l = " + l);

        assertEquals("l.size()", 5 , l.size());

        for (int i=0; i < 5; i++)
        	assertEquals("l.get(" + i +")", i*2, (int)l.get(i));


	}




}
