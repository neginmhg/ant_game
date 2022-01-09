package com.mycompany.a3;


import java.util.ArrayList;

public class GameObjectCollection implements ICollection {
	private ArrayList<GameObject> list;

	/**
	 * The collection that will store all game objects in the game world
	 */
	public GameObjectCollection() {
		list = new ArrayList<GameObject>();
	}

	@Override
	public void add(GameObject obj) {
		list.add(obj);
	}
	
	public void remove(GameObject obj) {
		list.remove(obj);
		
	}



	@Override
	// Returns the iterator associated with the collection to traverse through the
	// collection
	public IIterator getIterator() {
		return new GameObjectIterator();
	}

	@Override
	public int getSize() {
		return list.size();
	}
	
	
	public void removeAll() {
	
	for(int i=0;i<list.size();i++) 
		list.remove(i);
	
	}
	

	private class GameObjectIterator implements IIterator {
		private int index;
		/**
		 * The iterator to traverse through the collection
		 */
		public GameObjectIterator() {
			index = -1;
		
		}

		@Override
		public boolean hasNext() {
			if (list.size() <= 0)
				return false;
			if (index == list.size() - 1) {
				index = -1;
				
				return false;
			}
			return true;
		}

		@Override
		public GameObject getNext() {
			index++;
			return list.get(index);
		
		}

		@Override
		public GameObject getCurrent() {
			return list.get(index);
		}

		@Override
		public void remove() {
			list.remove(index);
			index--;
		}

		@Override
		public void remove(GameObject obj) {
			list.remove(obj);
			index--;
		}

	}

	

}