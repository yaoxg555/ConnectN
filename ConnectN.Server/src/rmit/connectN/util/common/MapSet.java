package rmit.connectN.util.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class MapSet<TKey, TValue> {
	Map<TKey, Set<TValue>> map = Collections.synchronizedMap(new HashMap<TKey, Set<TValue>>());
	
	public MapSet put(TKey key, TValue value)
	{
		if (!map.keySet().contains(key))
		{
			Set set = Collections.synchronizedSet(new LinkedHashSet<Object>()); //should be weak reference
			set.add(value);
			map.put(key, set);
		}
		map.get(key).add(value);
		return this;
	}
	
	public MapSet remove(TKey key, TValue value)
	{
		if (!map.keySet().contains(key))
			return this;
		Set set = map.get(key);
		set.remove(value);
		if (set.size() == 0)
			map.remove(key);
		return this;
	}
	
	public TValue[] get(TKey key)
	{
		if (!map.keySet().contains(key))
			return null;
		return (TValue[])map.get(key).toArray();
	}
}
