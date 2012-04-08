package rmit.connectN.util.ioc;

import java.util.*;

@SuppressWarnings("rawtypes")
public class IoC {
	Map<IoCType, Delegate> map = new HashMap<IoCType, Delegate>();
	
	class IoCType
	{
		Class toBeResolved;
		Class[] params;
		
		void init(Class toBeResolved, Class[] params)
		{
			this.toBeResolved = toBeResolved;
			this.params = params;
		}
		
		public IoCType(Class toBeResolved, Class[] params) {
			init(toBeResolved, params);
		}
		
		public IoCType(Class toBeResolved, Object[] _params) {
			Class[] params = new Class[_params.length];
			
			int i = 0;
			for(Object _p:_params)
				params[i++] = _p.getClass();
			
			init(toBeResolved, params);
		}
			
		public boolean equals(Object obj) {
	        if (obj == null)
	        	return false;
	        if (obj == this)
	            return true;
	        if (obj.getClass() != getClass())
	            return false;

	        IoCType foo = (IoCType)obj;
	        return foo.toBeResolved == toBeResolved && Arrays.equals(foo.params, params);
	    }
		
	    public int hashCode() {
	        return (toBeResolved.hashCode() << 16 + Arrays.hashCode(params));
	    }	
	}
	
	public IoC bind(Class toBeResolved, Delegate delegate, Class... params) throws Throwable
	{
		IoCType type = new IoCType(toBeResolved, params);
		if (map.containsKey(type))
			throw new Exception("Multiple Binding");
		map.put(type, delegate);
		return this;
	}
	
	public IoC bind(Bindable b) throws Throwable
	{
		b.bindRelations(this);
		return this;
	}

	
	public Object resolve(Class toBeResolved, Object... params) throws Throwable
	{
		IoCType type = new IoCType(toBeResolved, params);
		
		if (!map.containsKey(type))
			throw new Exception("No Available Binding");
		
		return map.get(type).create(params);
	}
}
