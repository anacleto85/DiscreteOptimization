package org.coursera.dopt.knapsack;

import java.util.Arrays;
import java.util.List;


public class KnapsackProblem 
{
	private KnapsackItem[] items;
	private int knapsackCapacity;
	private static int ITEM_ID_COUNTER = 1;
	
	/**
	 * 
	 * @param desc
	 */
	public KnapsackProblem(List<String> desc) {
		// parse the data in the file
        String[] firstLine = desc.get(0).split("\\s+");
        int size = Integer.parseInt(firstLine[0]);
        this.knapsackCapacity = Integer.parseInt(firstLine[1]);
        
        this.items = new KnapsackItem[size];
        for(int index=1; index <= size; index++){
          String line = desc.get(index);
          String[] parts = line.split("\\s+");
          
          // new item
          KnapsackItem item = new KnapsackItem(ITEM_ID_COUNTER++, Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
          // add item to problem
          this.items[index-1] = item;
        }
	}
	
	public KnapsackItem[] getItems() {
//		KnapsackItem[] copy = new KnapsackItem[this.items.length];
//		System.arraycopy(this.items, 0, copy, 0, this.items.length);
//		return copy;
		return Arrays.copyOf(this.items, this.items.length);
	}
	
	public KnapsackItem getItem(int itemId) {
		return this.items[itemId-1];
	}
	
	public int getKnapsackCapacity() {
		return this.knapsackCapacity;
	}
	
	public class KnapsackItem implements Comparable<KnapsackItem> 
	{
		protected int id;
		protected int value;
		protected int weight;
		
		protected KnapsackItem(int id, int value, int weight) {
			this.id = id;
			this.value = value;
			this.weight = weight;
		}
		
		public int getId() {
			return this.id;
		}
		
		public int getValue() {
			return this.value;
		}
		
		public int getWeight() {
			return this.weight;
		}
		
		@Override
		public int compareTo(KnapsackItem o) {
			return (this.value <= o.value) ? -1 : +1;
		}
		
		@Override
		public String toString() {
			return "[item id=" + this.id + ", value=" + this.value + ", weight=" + this.weight + "]";
		}
	}
}
