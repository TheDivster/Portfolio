package assign09;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a HashTable
 * @author Divy Tripathy
 * @version 04/7/22
 *
 * @param <K> - placeholder for key type
 * @param <V> - placeholder for value type
 */
public class HashTable<K, V> implements Map<K, V> {

	// fields
	private int size;
	private ArrayList<MapEntry<K, V>> list;
	private ArrayList<Boolean> lazyDeletionList;

	// constructor
	public HashTable() {
		this.size = 0;
		this.list = new ArrayList<MapEntry<K, V>>();
		this.lazyDeletionList = new ArrayList<Boolean>();
		for (int i = 0; i < 7; i++) {
			this.list.add(null);
			this.lazyDeletionList.add(false);
		}
	}

	/**
	 * Removes all mappings from this map.
	 * 
	 * O(table length) for quadratic probing or separate chaining
	 */
	@Override
	public void clear() {
		this.size = 0;
		this.list = new ArrayList<MapEntry<K, V>>();
		this.lazyDeletionList = new ArrayList<Boolean>();
		for (int i = 0; i < 7; i++) {
			this.list.add(null);
			this.lazyDeletionList.add(null);
		}
	}

	/**
	 * Determines whether this map contains the specified key.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return true if this map contains the key, false otherwise
	 */
	@Override
	public boolean containsKey(K key) {
		int index = Math.abs(key.hashCode()) % this.list.size();
		if (this.list.get(index) == null) {
			return false;
		} else if (this.list.get(index).getKey().equals(key) && this.lazyDeletionList.get(index) == false) {
			return true;
		} else if (this.list.get(index).getKey().equals(key) && this.lazyDeletionList.get(index) == true) {
			return false;
		} else {
			int counter = 1;
			boolean isNotFound = true;
			while (isNotFound == true) {
				int quadraticIndex = ((int) (index + Math.pow(counter, 2))) % this.list.size();
				// if where key is expected is null, return false
				if (this.list.get(quadraticIndex) == null) {
					isNotFound = false;
					return false;
				}
				// if this is the key and not lazy deleted
				else if (this.list.get(quadraticIndex).getKey().equals(key)
						&& this.lazyDeletionList.get(quadraticIndex) == false) {
					isNotFound = false;
					return true;
				}
				// if we found the key and is lazy deleted, return true
				else if (this.list.get(quadraticIndex).getKey().equals(key)
						&& this.lazyDeletionList.get(quadraticIndex) == true) {
					isNotFound = false;
					return false;
				}
				// else increment counter and search again
				else {
					counter++;
				}
			}
		}

		return false;
	}

	/**
	 * Determines whether this map contains the specified value.
	 * 
	 * O(table length) for quadratic probing O(table length + N) for separate
	 * chaining
	 * 
	 * @param value
	 * @return true if this map contains one or more keys to the specified value,
	 *         false otherwise
	 */
	@Override
	public boolean containsValue(V value) {
		for (MapEntry<K, V> entry : this.entries()) {
			if (entry.getValue().equals(value)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns a List view of the mappings contained in this map, where the ordering
	 * of mapping in the list is insignificant.
	 * 
	 * O(table length) for quadratic probing O(table length + N) for separate
	 * chaining
	 * 
	 * @return a List object containing all mapping (i.e., entries) in this map
	 */
	@Override
	public List<MapEntry<K, V>> entries() {
		ArrayList<MapEntry<K, V>> returnList = new ArrayList<MapEntry<K, V>>();
		for (int i = 0; i < this.list.size(); i++) {
			if (this.list.get(i) != null && this.lazyDeletionList.get(i) != (true))
				returnList.add(this.list.get(i));
		}
		return returnList;
	}

	/**
	 * Gets the value to which the specified key is mapped.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @return the value to which the specified key is mapped, or null if this map
	 *         contains no mapping for the key
	 */
	@Override
	public V get(K key) {
		// should we have a case where if the value at the index is null, we return null
		int index = Math.abs(key.hashCode()) % this.list.size();
		// if the hashing index gives us the same key, get the value
		// check if the value at the index is null and return null if it is
		if (this.list.get(index) == null)
			return null;
		else if (this.list.get(index).getKey().equals(key) && this.lazyDeletionList.get(index).equals(false))
			return this.list.get(index).getValue();
		else if (this.list.get(index).getKey().equals(key)
				&& this.lazyDeletionList.get(index) == true) {
			return null;
		}
		// else do quadratic probing
		else {
			int iterativeQuadratic = 1;
			// flag to keep the loop going
			boolean isNotFound = true;
			while (isNotFound == true) {
				int quadraticIndex = ((int) (index + Math.pow(iterativeQuadratic, 2))) % this.list.size();
				if (this.list.get(quadraticIndex) == null) {
					isNotFound = false;
					return null;
				} else if (this.list.get(quadraticIndex).getKey() == null) {
					isNotFound = false;
					return null;
				} else if (this.list.get(quadraticIndex).getKey().equals(key)
						&& this.lazyDeletionList.get(quadraticIndex) == false) {
					isNotFound = false;
					return this.list.get(quadraticIndex).getValue();
				} else if (this.list.get(quadraticIndex).getKey().equals(key)
						&& this.lazyDeletionList.get(quadraticIndex) == true) {
					isNotFound = false;
					return null;
				} else {
					iterativeQuadratic++;
				}
			}
		}
		return null;
	}

	/**
	 * Determines whether this map contains any mappings.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return true if this map contains no mappings, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		if (this.size == 0)
			return true;
		return false;
	}

	/**
	 * Associates the specified value with the specified key in this map. (I.e., if
	 * the key already exists in this map, resets the value; otherwise adds the
	 * specified key-value pair.)
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @param key
	 * @param value
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V put(K key, V value) {
		// grow the list if needed
		if ((this.size + 1 + 0.0) / this.list.size() >= 0.5) {
			this.rehash();
		}
		// initial index
		int index = Math.abs(key.hashCode()) % list.size();
		// if index position is null, insert
		// rehashing before everything would fix things because we are not checking for
		// collisons
		// after we get the new index
		// we need this second if statement to check again whether or not we have a
		// collision
		if (this.list.get(index) == null) {
			this.list.set(index, new MapEntry<K, V>(key, value));
			this.size++;
			// add a false to the lazy deletion list
			this.lazyDeletionList.set(index, false);
			// since there was nothing at this index return null
			return null;
		} else if (this.lazyDeletionList.get(index) == true && this.list.get(index).getKey().equals(key)) {
			this.list.set(index, new MapEntry<K, V>(key, value));
			this.size++;
			// add a false to the lazy deletion list
			this.lazyDeletionList.set(index, false);
			// since there was nothing at this index return null
			return null;
		}
		// if they are the same key, replace the value
		// this is for if the hash didn't have collisions
		else if (this.list.get(index).getKey().equals(key) && this.lazyDeletionList.get(index) == false) {
			// don't increment the size when we have the same key
			V item = this.list.get(index).getValue();
			this.list.get(index).setValue(value);
			return item;
		}
		// this means that the item at the hash index isn't empty and it isn't the same
		// key value
		else {
			// do a while loop
			// boolean flag
			boolean isNotFound = true;
			// starts at 1 for quadratic probing
			int i = 1;
			while (isNotFound) {
				//this.collisions++;
				int indexQuadratic = ((int) (index + Math.pow(i, 2))) % this.list.size();
				// we need to check again if our new index is a collision
				if (this.list.get(indexQuadratic) == null) {
					// don't increment the size when we have the same key
					this.list.set(indexQuadratic, new MapEntry<K, V>(key, value));
					this.size++;
					// add a false to the lazy deletion list
					this.lazyDeletionList.set(indexQuadratic, false);
					isNotFound = false;
					// since there was nothing at this index return null
					return null;
				} else if (this.lazyDeletionList.get(indexQuadratic) == true
						&& this.list.get(indexQuadratic).getKey().equals(key)) {
					// don't increment the size when we have the same key
					this.list.set(indexQuadratic, new MapEntry<K, V>(key, value));
					this.size++;
					// add a false to the lazy deletion list
					this.lazyDeletionList.set(indexQuadratic, false);
					isNotFound = false;
					// since there was nothing at this index return null
					return null;
				} else if (this.list.get(indexQuadratic).getKey().equals(key)
						&& this.lazyDeletionList.get(indexQuadratic) == false) {
					V item = this.list.get(indexQuadratic).getValue();
					this.list.get(indexQuadratic).setValue(value);
					// make sure that the loop stops running
					isNotFound = false;
					return item;
				} else {
					// advance the loop
					i++;
				}
			}
		}
		return null;
	}

	/**
	 * Removes the mapping for a key from this map if it is present.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 *
	 * @param key
	 * @return the previous value associated with key, or null if there was no
	 *         mapping for key
	 */
	@Override
	public V remove(K key) {
		// is it ok to check for lazy deletion using null or should we check our lazy
		// deletion list
		int index = Math.abs(key.hashCode()) % this.list.size();
		// if the item at index is null, return null
		if (this.list.get(index) == null)
			return null;
		else if (this.lazyDeletionList.get(index) == true && this.list.get(index).getKey().equals(key)) {
			return null;
		}
		// if they are the same key at this index, lazy delete
		else if (this.list.get(index).getKey().equals(key) && this.lazyDeletionList.get(index) == false) {
			this.lazyDeletionList.set(index, true);
			this.size--;
			return this.list.get(index).getValue();
		}
		// else if not at this index, probe
		else {
			int iterativeQuadratic = 1;
			// boolean flag to keep the loop going
			boolean isNotFound = true;
			while (isNotFound) {
				int quadraticPosition = ((index + ((int) Math.pow(iterativeQuadratic, 2))) % this.list.size());
				// if the position after the probe is null, return null
				if (this.list.get(quadraticPosition) == null) {
					isNotFound = false;
					return null;
				}
				else if (this.lazyDeletionList.get(quadraticPosition) == true
						&& this.list.get(quadraticPosition).getKey().equals(key)) {
					isNotFound = false;
					return null;
				}
				// else if the key was found, lazy delete
				else if (this.list.get(quadraticPosition).getKey().equals(key)
						&& this.lazyDeletionList.get(quadraticPosition) == false) {
					this.lazyDeletionList.set(quadraticPosition, true);
					this.size--;
					isNotFound = false;
					return this.list.get(quadraticPosition).getValue();
				}
				// not found, advance loop
				else {
					iterativeQuadratic++;
				}
			}
		}
		return null;
	}

	/**
	 * Determines the number of mappings in this map.
	 * 
	 * O(1) for quadratic probing or separate chaining
	 * 
	 * @return the number of mappings in this map
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * private rehash method
	 */
	private void rehash() {
		// capture the pairs in a list
		List<MapEntry<K, V>> entries = this.entries();
		// doubles the size
		int sizeArrayList = findLargerThanPrime(this.list.size() * 2);
		ArrayList<MapEntry<K, V>> newList = new ArrayList<MapEntry<K, V>>();
		for (int i = 0; i < sizeArrayList; i++) {
			newList.add(null);
		}
		this.list = newList;
		// grow and reset the lazy deletion list
		ArrayList<Boolean> newLazyList = new ArrayList<Boolean>();
		for (int i = 0; i < sizeArrayList; i++) {
			newLazyList.add(false);
		}
		this.lazyDeletionList = newLazyList;
		// puts the value in the new ArrayList
		for (MapEntry<K, V> entry : entries) {
			this.put(entry.getKey(), entry.getValue());
			this.size--;
		}
	}

	/**
	 * private method to find prime number larger than input
	 * 
	 * @param number
	 * @return number larger than input
	 */
	private static int findLargerThanPrime(int number) {
		int counter = number;
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 2; counter > i * i; i++)
				if (counter % i == 0) {
					flag = true;
					break;
				}

			counter++;
		}
		return --counter;
	}
}
