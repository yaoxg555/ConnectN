package rmit.connectN.util.ioc;


public interface Delegate<T> {
	public T create(Object... params);
}