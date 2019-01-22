package hmllm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;

public class HMLLMTest {

	protected HomemadeMap testMap;
	
	private String[] data = {	
		 "Minnesota", "Minneapolis",
	     "Texas", "Dallas",
	     "Oregon", "Seattle",
	     "New Jersey", "Newark",
	     "Pennsylvania", "Philadelphia",
	     "Massachusetts", "Springfield",
	     "Arizona", "Tuscon",
	     "Michigan", "Ann Arbor",
	     "Ohio", "Cincinatti",
	     "New York", "Buffalo",
	     "Florida", "Orlando",
	     "Colorado", "Boulder",
	     "Alabama", "Jackson",
	     "Kentucky", "Louisville",
	     "Kansas", "Wichita",
	     "Alaska", "Vasilia" };
	
	private String[] otherData = { "Wisconsin", "Oklahoma", "Washington" };

	protected void reset() {
		testMap = new HomemadeLLMap();
	}

	protected void populate(int pairs) {
		for (int i = 0; i < pairs; i++)
			testMap.put(data[2 * i], data[2 * i + 1]);
	}

	@Test
	public void emptyContainsKey() {
		reset();
		for (int i = 0; i < data.length; i += 2)
			assertFalse(testMap.containsKey(data[i]));
		for (int i = 0; i < otherData.length; i++)
			assertFalse(testMap.containsKey(otherData[i]));
	}

	@Test
	public void putContainsKey() {
		reset();
		populate(data.length / 2);
		for (int i = 0; i < data.length; i += 2)
			assertTrue(testMap.containsKey(data[i]));
		for (int i = 0; i < otherData.length; i++)
			assertFalse(testMap.containsKey(otherData[i]));
	}

	@Test
	public void stressEquals() {
	    reset();
	    populate(data.length / 2);
	    assertTrue("== or .equals()?", testMap.containsKey("Newcastle".substring(0, 3) + ' ' + "Yorkshire".substring(0,  4)));
	}
	
	@Test
	public void emptyGet() {
		reset();
		for (int i = 0; i < data.length; i += 2)
			assertEquals(null, testMap.get(data[i]));
		for (int i = 0; i < otherData.length; i++)
			assertEquals(null, testMap.get(otherData[i]));
		
	}

	@Test
	public void putGet() {
		reset();
		populate(data.length / 2);
		for (int i = 0; i < data.length; i += 2)
			assertEquals(data[i+1], testMap.get(data[i]));
		for (int i = 0; i < otherData.length; i++)
			assertEquals(null, testMap.get(otherData[i]));
	}

	@Test
	public void putReplace() {
		reset();
		populate(data.length / 2);
		testMap.put("Alaska", "Barrows");
		assertTrue(testMap.containsKey("Alaska"));
		for (int i = 0; i < data.length; i += 2)
			if (data[i].equals("Alaska"))
				assertEquals("Barrows", testMap.get(data[i]));
			else 
				assertEquals(data[i+1], testMap.get(data[i]));
		for (int i = 0; i < otherData.length; i++)
			assertEquals(null, testMap.get(otherData[i]));
	}

	@Test
	public void emptyIterator() {
		reset();
		int i = 0;
		for (Iterator<String> it = testMap.keyIterator(); it.hasNext(); )
			i++;
		assertEquals(0, i);
	}

	@Test
	public void populatedIterator() {
		reset();
		populate(data.length/ 2);
		boolean[] founds = new boolean[data.length / 2];
		for (int i = 0; i < founds.length; i++)
			founds[i] = false;
		for (Iterator<String> it = testMap.keyIterator(); it.hasNext(); ) {
			String key = it.next();
			boolean foundIt = false;
			for (int i = 0; i < data.length && ! foundIt; i += 2) {
				if (data[i].equals(key)) {
					// key returned from iterator has right value in map
					assertEquals(data[i+1], testMap.get(key));
					// iterator hasn't returned this key before
					assertFalse("Repeated key: " + key, founds[i/2]);
					founds[i/2] = true;
					foundIt = true;
				}
			}
			// key returned by iterator was a real key (it was found in raw data)
			assertTrue("Extraneous key: " + key, foundIt);
		}
		for (int i = 0; i < founds.length; i++)
			assertTrue("Missed key: " + data[i*2], founds[i]);
	}

	@Test
	public void replacedIterator() {
		reset();
		populate(data.length / 2);
		testMap.put("Alaska", "Barrows");
		boolean[] founds = new boolean[data.length / 2];
		for (int i = 0; i < founds.length; i++)
			founds[i] = false;
		for (Iterator<String> it = testMap.keyIterator(); it.hasNext(); ) {
			String key = it.next();
			boolean foundIt = false;
			for (int i = 0; i < data.length && ! foundIt; i += 2) {
				if (data[i].equals(key)) {
					// key returned from iterator has right value in map
					if (key.equals("Alaska"))
						assertEquals("Barrows", testMap.get(key));
					else 
						assertEquals(data[i+1], testMap.get(key));
					// iterator hasn't returned this key before
					assertFalse("Repeated key: " + key, founds[i/2]);
					founds[i/2] = true;
					foundIt = true;
				}
			}
			// key returned by iterator was a real key (it was found in raw data)
			assertTrue("Extraneous key: " + key, foundIt);
		}
		for (int i = 0; i < founds.length; i++)
			assertTrue("Missed key: " + data[i*2], founds[i]);
	}

	@Test
	public void emptyRemove() {
		reset();
		testMap.remove("Alaska");
		for (int i = 0; i < data.length; i += 2)
			assertFalse(testMap.containsKey(data[i]));
		for (int i = 0; i < otherData.length; i++)
			assertFalse(testMap.containsKey(otherData[i]));
	}

	@Test
	public void populatedRemoveFirst() {
		reset();
		populate(data.length / 2);
		testMap.remove("Alaska");
		assertFalse(testMap.containsKey("Alaska"));
		for (int i = 0; i < data.length; i += 2)
			if (data[i].equals("Alaska"))
				assertEquals(null, testMap.get(data[i]));
			else
				assertEquals(data[i+1], testMap.get(data[i]));
		for (int i = 0; i < otherData.length; i++)
			assertEquals(null, testMap.get(otherData[i]));
	}

    @Test
    public void populatedRemoveMiddle() {
        reset();
        populate(data.length / 2);
        testMap.remove("Ohio");
        assertFalse(testMap.containsKey("Ohio"));
        for (int i = 0; i < data.length; i += 2)
            if (data[i].equals("Ohio"))
                assertEquals(null, testMap.get(data[i]));
            else
                assertEquals(data[i+1], testMap.get(data[i]));
        for (int i = 0; i < otherData.length; i++)
            assertEquals(null, testMap.get(otherData[i]));
    }

    
    @Test
    public void populatedRemoveLast() {
        reset();
        populate(data.length / 2);
        testMap.remove("Minnesota");
        assertFalse(testMap.containsKey("Minnesota"));
        for (int i = 0; i < data.length; i += 2)
            if (data[i].equals("Minnesota"))
                assertEquals(null, testMap.get(data[i]));
            else
                assertEquals(data[i+1], testMap.get(data[i]));
        for (int i = 0; i < otherData.length; i++)
            assertEquals(null, testMap.get(otherData[i]));
    }

    

	
	@Test
	public void populatedRemoveSpurious() {
		reset();
		populate(data.length / 2);
		testMap.remove("Wisconsin");
		for (int i = 0; i < data.length; i += 2)
			assertEquals(data[i+1], testMap.get(data[i]));
		for (int i = 0; i < otherData.length; i++)
			assertEquals(null, testMap.get(otherData[i]));
	
	}

	@Test
	public void removeIterator() {
		reset();
		populate(data.length / 2);
		testMap.remove("Alaska");
		boolean[] founds = new boolean[(data.length / 2) - 1];
		for (int i = 0; i < founds.length; i++)
			founds[i] = false;
		for (Iterator<String> it = testMap.keyIterator(); it.hasNext(); ) {
			String key = it.next();
			boolean foundIt = false;
			for (int i = 0; i < founds.length && ! foundIt; i++) {
				if (data[2 * i].equals(key)) {
					// key returned from iterator has right value in map
					assertEquals(data[2 * i+1], testMap.get(key));
					// iterator hasn't returned this key before
					assertFalse("Repeated key: " + key, founds[i]);
					founds[i] = true;
					foundIt = true;
				}
			}
			// key returned by iterator was a real key (it was found in raw data)
			assertTrue("Extraneous key: " + key, foundIt);
		}
		for (int i = 0; i < founds.length; i++)
			assertTrue("Missed key: " + data[i*2], founds[i]);
	
	}

}
