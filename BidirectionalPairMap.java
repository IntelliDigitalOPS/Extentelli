package intelli.utils;

import java.util.HashMap;

import com.google.common.base.Preconditions;

public class BidirectionalPairMap<E> {
	private HashMap<E, E> a = new HashMap<>();
	private HashMap<E, E> b = new HashMap<>();
	
	public E getOther(E aKeyOrBKey) {
		Preconditions.checkArgument(a.containsKey(aKeyOrBKey)||b.containsKey(aKeyOrBKey), "NotFound");
		return a.containsKey(aKeyOrBKey)?a.get(aKeyOrBKey):b.get(aKeyOrBKey);
	}
	
	public void put(E a, E b) {
		this.a.put(a, b);
		this.b.put(b, a);
	}
	
	public void remove(E aKeyOrBKey) {
		Preconditions.checkArgument(a.containsKey(aKeyOrBKey)||b.containsKey(aKeyOrBKey), "NotFound");
		if(a.containsKey(aKeyOrBKey)) {
			b.remove(getOther(aKeyOrBKey));
			a.remove(aKeyOrBKey);
		}else {
			a.remove(getOther(aKeyOrBKey));
			b.remove(aKeyOrBKey);
		}
	}
	
	public boolean contains(E aKeyOrBKey) {
		return a.containsKey(aKeyOrBKey)||b.containsKey(aKeyOrBKey);
	}
	
	public void clear() {
		a.clear();
		b.clear();
	}
}
