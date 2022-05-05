package assign09;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Tester class
 * @author Divy Tripathy
 * @version 04/7/22
 *
 */
class HashTableTest {

	// fields
	HashTable<Integer, Double> fourItems;
	HashTable<Integer, Double> fiveItems;
	HashTable<Integer, Double> hundredItems;
	HashTable<Integer, Double> hundredItems2;
	HashTable<Integer, Double> zeroItems;
	HashTable<Integer, Double> oneItem;
	HashTable<Integer, Double> thirtySixItem;

	@BeforeEach
	void setUp() throws Exception {
		zeroItems = new HashTable<Integer, Double>();
		oneItem = new HashTable<Integer, Double>();
		oneItem.put(1, 11.0);
		fourItems = new HashTable<Integer, Double>();
		fourItems.put(3, 2.0);
		fourItems.put(2, 5.0);
		fourItems.put(1, 10.0);
		fourItems.put(4, 1.0);

		fiveItems = new HashTable<Integer, Double>();
		fiveItems.put(3, 2.0);
		fiveItems.put(2, 5.0);
		fiveItems.put(1, 10.0);
		fiveItems.put(4, 1.0);
		fiveItems.put(10, 0.0);

		hundredItems = new HashTable<Integer, Double>();
		ArrayList<Integer> hundredArrayListKeys = new ArrayList<Integer>();
		ArrayList<Double> hundredArrayListValues = new ArrayList<Double>();
		for (int i = 0; i < 100; i++) {
			hundredArrayListKeys.add(i);
			hundredArrayListValues.add(i + 0.0);
		}
		Random rand = new Random();
		rand.setSeed(1);
		Collections.shuffle(hundredArrayListKeys);

		Collections.shuffle(hundredArrayListValues);
		for (int i = 99; i >= 0; i--) {
			hundredItems.put(hundredArrayListKeys.get(i), hundredArrayListValues.get(i));
		}

		thirtySixItem = new HashTable<Integer, Double>();
		for (int i = 0; i < 8; i++) {
			thirtySixItem.put(hundredArrayListKeys.get(i), hundredArrayListValues.get(i));
		}

		hundredItems2 = new HashTable<Integer, Double>();
		for (int i = 99; i >= 0; i--) {
			hundredItems2.put(i, i + 0.0);
		}
	}

	@Test
	void testEntriesKeysFourItems() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		for (MapEntry<Integer, Double> entry : fourItems.entries()) {
			testArray.add(entry.getKey());
		}
		assertEquals(Arrays.toString(new Integer[] { 1, 2, 3, 4 }), Arrays.toString(testArray.toArray()));
	}

	@Test
	void testEntriesKeysFiveItems() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		for (MapEntry<Integer, Double> entry : fiveItems.entries()) {
			testArray.add(entry.getKey());
		}
		assertEquals(Arrays.toString(new Integer[] { 1, 2, 3, 4, 10 }), Arrays.toString(testArray.toArray()));
	}

	@Test
	void testEntriesKeysHundredItems() {
		ArrayList<Integer> testArray = new ArrayList<Integer>();
		for (MapEntry<Integer, Double> entry : hundredItems.entries()) {
			testArray.add(entry.getKey());
		}
		Integer[] expected = new Integer[100];
		for (int i = 0; i < 100; i++) {
			expected[i] = i;
		}
		assertEquals(expected.length, testArray.size());
		assertEquals(Arrays.toString(expected), Arrays.toString(testArray.toArray()));
	}

	@Test
	void testEntriesValuesFourItems() {
		ArrayList<Double> testArray = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : fourItems.entries()) {
			testArray.add(entry.getValue());
		}
		assertEquals(Arrays.toString(new Double[] { 10.0, 5.0, 2.0, 1.0 }), Arrays.toString(testArray.toArray()));
	}

	@Test
	void testEntriesValuesFiveItems() {
		ArrayList<Double> testArray = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : fiveItems.entries()) {
			testArray.add(entry.getValue());
		}
		assertEquals(Arrays.toString(new Double[] { 10.0, 5.0, 2.0, 1.0, 0.0 }), Arrays.toString(testArray.toArray()));
	}

	@Test
	void testEntriesValuesHundredItems() {
		ArrayList<Double> testArray = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : hundredItems2.entries()) {
			testArray.add(entry.getValue());
		}
		Double[] expected = new Double[100];
		for (int i = 0; i < 100; i++) {
			expected[i] = i + 0.0;
		}
		assertEquals(expected.length, testArray.size());
		assertEquals(Arrays.toString(expected), Arrays.toString(testArray.toArray()));
	}

	// test for duplicates

	@Test
	void testEntriesFourItemsDuplicate() {
		fourItems.put(2, 87.3);
		ArrayList<Double> testArray = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : fourItems.entries()) {
			testArray.add(entry.getValue());
		}
		assertEquals(Arrays.toString(new Double[] { 10.0, 87.3, 2.0, 1.0 }), Arrays.toString(testArray.toArray()));
	}

	@Test
	void testEntriesFourItemsDuplicate2() {
		fourItems.put(3, 87.3);
		ArrayList<Double> testArray = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : fourItems.entries()) {
			testArray.add(entry.getValue());
		}
		assertEquals(Arrays.toString(new Double[] { 10.0, 5.0, 87.3, 1.0 }), Arrays.toString(testArray.toArray()));
	}

	@Test
	void testEntriesFiveItemsDuplicate() {
		fiveItems.put(4, 98.987);
		ArrayList<Double> testArray = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : fiveItems.entries()) {
			testArray.add(entry.getValue());
		}
		assertEquals(Arrays.toString(new Double[] { 10.0, 5.0, 2.0, 98.987, 0.0 }),
				Arrays.toString(testArray.toArray()));
	}

	// make a hundred Items 5 duplicates
	@Test
	void testEntriesHundredItemsFiveDuplicates() {
		hundredItems2.put(2, 1000.7);
		hundredItems2.put(87, 200.7);
		hundredItems2.put(16, 1030.98);
		hundredItems2.put(69, 0.7);
		hundredItems2.put(99, 1000.7);

		Double[] expected = new Double[100];
		for (int i = 0; i < 100; i++) {
			if (i == 2)
				expected[i] = 1000.7;
			else if (i == 87)
				expected[i] = 200.7;
			else if (i == 16)
				expected[i] = 1030.98;
			else if (i == 69)
				expected[i] = 0.7;
			else if (i == 99)
				expected[i] = 1000.7;
			else
				expected[i] = i + 0.0;
		}

		ArrayList<Double> values = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : hundredItems2.entries()) {
			values.add(entry.getValue());
		}

		assertEquals(Arrays.toString(expected), Arrays.toString(values.toArray()));
	}

	// make a hundred Items 5 duplicates
	@Test
	void testEntriesHundredItemsFiveDuplicates2() {
		hundredItems2 = new HashTable<Integer, Double>();
		for (int i = 99; i >= 0; i--) {
			hundredItems2.put(i, i + 0.0);
			hundredItems2.put(i, i + 0.0);
		}
		hundredItems2.put(2, 1000.7);
		hundredItems2.put(87, 200.7);
		hundredItems2.put(16, 1030.98);
		hundredItems2.put(69, 0.7);
		hundredItems2.put(99, 1000.7);

		Double[] expected = new Double[100];
		for (int i = 0; i < 100; i++) {
			if (i == 2)
				expected[i] = 1000.7;
			else if (i == 87)
				expected[i] = 200.7;
			else if (i == 16)
				expected[i] = 1030.98;
			else if (i == 69)
				expected[i] = 0.7;
			else if (i == 99)
				expected[i] = 1000.7;
			else
				expected[i] = i + 0.0;
		}

		ArrayList<Double> values = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : hundredItems2.entries()) {
			values.add(entry.getValue());
		}

		assertEquals(Arrays.toString(expected), Arrays.toString(values.toArray()));
	}

	// do a test with contains() to check that our hundredItems table has everything
	@Test
	void testEntriesHundredItemsValues() {

		ArrayList<Double> values = new ArrayList<Double>();
		for (MapEntry<Integer, Double> entry : hundredItems.entries()) {
			values.add(entry.getValue());
		}

		for (int i = 0; i < 100; i++) {
			assertTrue(hundredItems.containsValue(i + 0.0));
		}
	}

	// we need to check the return values of put and we need to make sure contains
	// returns false
	// for put does it matter that rehashing can return null?
	@Test
	void testEntriesHundredItemsReturnsNull() {
		hundredItems = new HashTable<Integer, Double>();
		ArrayList<Integer> hundredArrayListKeys = new ArrayList<Integer>();
		ArrayList<Double> hundredArrayListValues = new ArrayList<Double>();
		for (int i = 0; i < 100; i++) {
			hundredArrayListKeys.add(i);
			hundredArrayListValues.add(i + 0.0);
		}
		Random rand = new Random();
		rand.setSeed(1);
		Collections.shuffle(hundredArrayListKeys);

		Collections.shuffle(hundredArrayListValues);
		for (int i = 99; i >= 0; i--) {
			assertTrue(hundredItems.put(hundredArrayListKeys.get(i), hundredArrayListValues.get(i)) == null);
		}
	}

	@Test
	void testEntriesHundredItemsReturnsDuplicates() {
		// how do I debug this
		// still not completely working
		hundredItems = new HashTable<Integer, Double>();
		ArrayList<Integer> hundredArrayListKeys = new ArrayList<Integer>();
		ArrayList<Double> hundredArrayListValues = new ArrayList<Double>();
		for (int i = 0; i < 100; i++) {
			hundredArrayListKeys.add(i);
			hundredArrayListValues.add(i + 0.0);
		}
		Random rand = new Random();
		rand.setSeed(23);
		Collections.shuffle(hundredArrayListKeys);

		Collections.shuffle(hundredArrayListValues);
		for (int i = 0; i < 100; i++) {
			hundredItems.put(hundredArrayListKeys.get(i), hundredArrayListValues.get(i));
			Double output = hundredItems.put(hundredArrayListKeys.get(i), hundredArrayListValues.get(i));
			assertEquals(hundredArrayListValues.get(i), output);
		}
	}

	@Test
	void testEntriesHundredItemsReturnsNotPermuted() {
		hundredItems2 = new HashTable<Integer, Double>();
		for (int i = 99; i >= 0; i--) {
			hundredItems2.put(i, i + 0.0);
			assertTrue(hundredItems2.put(i, i + 0.0) == i + 0.0);
		}
	}

	@Test
	void testContainsValueFalse() {
		assertFalse(hundredItems.containsValue(1.3));
	}

	@Test
	void testRemoveEveryItem() {
		for (int i = 0; i < 100; i++) {
			hundredItems.remove(i);
		}
		assertTrue(hundredItems.size() == 0);
	}

	@Test
	void testRemoveEveryItemList() {
		for (int i = 0; i < 100; i++) {
			hundredItems.remove(i);
		}
		List<MapEntry<Integer, Double>> result = hundredItems.entries();
		assertTrue(result.size() == 0);
	}

	@Test
	void testRemoveSize0() {
		for (int i = 0; i < 101; i++) {
			hundredItems.remove(i);
		}
		List<MapEntry<Integer, Double>> result = hundredItems.entries();
		assertTrue(result.size() == 0);
	}

	@Test
	void testContainsValueSize0() {
		assertFalse(zeroItems.containsValue(11.0));
	}

	@Test
	void testContainsValueSize1() {
		assertTrue(oneItem.containsValue(11.0));
		assertFalse(oneItem.containsValue(15.0));
	}

	@Test
	void testRemoveSize1() {
		oneItem.remove(1);
		assertEquals(0, oneItem.size());
		assertEquals(0, oneItem.entries().size());
	}

	@Test
	void testRemovePermuted() {
		// this one isn't working always
		List<MapEntry<Integer, Double>> entries = thirtySixItem.entries();
		ArrayList<Integer> keys = new ArrayList<Integer>();

		for (MapEntry<Integer, Double> i : entries) {
			keys.add(i.getKey());
		}
		System.out.println();
		for (int key : keys) {
			thirtySixItem.remove(key);
		}
		assertEquals(0, thirtySixItem.size());
	}

	@Test
	void testRemovePermuted2() {
		List<MapEntry<Integer, Double>> entries = thirtySixItem.entries();
		ArrayList<Integer> keys = new ArrayList<Integer>();

		for (MapEntry<Integer, Double> i : entries) {
			keys.add(i.getKey());
		}
		System.out.println();
		for (int key : keys) {
			thirtySixItem.remove(key);
		}

		int i = thirtySixItem.entries().size();
		assertEquals(0, i);
	}

	@Test
	void testContainsKeyAll() {
		Integer[] expected = new Integer[100];
		for (int i = 0; i < 100; i++) {
			expected[i] = i;
		}
		for (int i = 0; i < 100; i++)
			assertTrue(hundredItems.containsKey(expected[i]));
	}

	@Test
	void testContainsKeyAllPermuted() {
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			expected.add(i);
		}
		Collections.shuffle(expected);
		for (int i = 0; i < 100; i++)
			assertTrue(hundredItems.containsKey(expected.get(i)));
	}

	@Test
	void testContainsKeyAfterRemove() {
		Integer[] expected = new Integer[100];
		for (int i = 0; i < 100; i++) {
			expected[i] = i;
		}
		for (int i = 0; i < 100; i++) {
			hundredItems.remove(expected[i]);
			assertFalse(hundredItems.containsKey(expected[i]));
		}
	}

	@Test
	void testContainsKeyAfterRemovePeruted() {
		ArrayList<Integer> expected = new ArrayList<Integer>();
		for (int i = 0; i < 100; i++) {
			expected.add(i);
		}
		Collections.shuffle(expected);
		for (int i = 0; i < 100; i++) {
			thirtySixItem.remove(expected.get(i));
			assertFalse(thirtySixItem.containsKey(expected.get(i)));
		}
	}
	// make sure to test the return of remove

	@Test
	void testIsEmptyTrue() {
		assertTrue(this.zeroItems.isEmpty());
	}

	@Test
	void testIsEmptyFalse() {
		assertFalse(this.hundredItems.isEmpty());
	}

	@Test
	void testSizeHundredItemsPermuted() {
		hundredItems = new HashTable<Integer, Double>();
		ArrayList<Integer> hundredArrayListKeys = new ArrayList<Integer>();
		ArrayList<Double> hundredArrayListValues = new ArrayList<Double>();
		for (int i = 0; i < 100; i++) {
			hundredArrayListKeys.add(i);
			hundredArrayListValues.add(i + 0.0);
		}
		Random rand = new Random();
		rand.setSeed(23);
		Collections.shuffle(hundredArrayListKeys);

		Collections.shuffle(hundredArrayListValues);
		for (int i = 0; i < 100; i++) {
			hundredItems.put(hundredArrayListKeys.get(i), hundredArrayListValues.get(i));
			assertEquals(i + 1, hundredItems.size());
		}
	}

	@Test
	void testRemovePermutedSize() {
		List<MapEntry<Integer, Double>> entries = hundredItems.entries();
		ArrayList<Integer> keys = new ArrayList<Integer>();

		for (MapEntry<Integer, Double> i : entries) {
			keys.add(i.getKey());
		}

		int i = 100;
		for (int key : keys) {
			hundredItems.remove(key);
			assertEquals(--i, hundredItems.size());
		}
	}

	//@Test
	void testRemoveHundredItemsReturns() {
		// how do I debug this
		// still not completely working
		hundredItems = new HashTable<Integer, Double>();
		ArrayList<Integer> hundredArrayListKeys = new ArrayList<Integer>();
		ArrayList<Double> hundredArrayListValues = new ArrayList<Double>();
		for (int i = 0; i < 100; i++) {
			hundredArrayListKeys.add(i);
			hundredArrayListValues.add(i + 0.0);
		}
		Random rand = new Random();
		rand.setSeed(23);
		Collections.shuffle(hundredArrayListKeys);

		Collections.shuffle(hundredArrayListValues);
		for (int i = 0; i < 100; i++) {
			hundredItems.put(hundredArrayListKeys.get(i), hundredArrayListValues.get(i));
			Double output = hundredItems.remove(hundredArrayListKeys.get(i));
			assertEquals(hundredArrayListValues.get(i), output);
		}
	}

	@Test
	void testClearEntries() {
		hundredItems.clear();
		assertEquals(0, hundredItems.entries().size());
	}

	@Test
	void testClearSize() {
		hundredItems.clear();
		assertEquals(0, hundredItems.size());
	}

	@Test
	void testClearGet() {
		hundredItems.clear();
		assertEquals(null, hundredItems.get(90));
	}

	@Test
	void testGetPermuted() {
		hundredItems = new HashTable<Integer, Double>();
		ArrayList<Integer> hundredArrayListKeys = new ArrayList<Integer>();
		ArrayList<Double> hundredArrayListValues = new ArrayList<Double>();
		for (int i = 0; i < 100; i++) {
			hundredArrayListKeys.add(i);
			hundredArrayListValues.add(i + 0.0);
		}
		Random rand = new Random();
		rand.setSeed(23);
		Collections.shuffle(hundredArrayListKeys);

		Collections.shuffle(hundredArrayListValues);
		for (int i = 0; i < 100; i++) {
			hundredItems.put(hundredArrayListKeys.get(i), hundredArrayListValues.get(i));
			assertEquals(hundredArrayListValues.get(i), hundredItems.get(hundredArrayListKeys.get(i)));
		}
	}
	
	@Test
	void testGetOneItem() {
		assertEquals(11.0, this.oneItem.get(1));
	}
}
