package rmit.connectN.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import rmit.connectN.util.ioc.Bindable;
import rmit.connectN.util.ioc.Delegate;
import rmit.connectN.util.ioc.IoC;

public class IoCTest {
	IoC ioc;
	
	class Binder1 {}
	class Binder2 {}
	interface Bindee {}
	class BindeeNoParam implements Bindee {}
	class BindeeToBinder1 implements Bindee {}
	class BindeeToBinder1And2 implements Bindee {}
	
	class BindableImpl implements Bindable
	{

		@Override
		public void bindRelations(IoC ioc) throws Throwable {
			ioc.bind(Bindee.class, new Delegate() {
				@Override
				public Object create(Object... params) {
					return new BindeeNoParam();
				}
			});
			ioc.bind(Bindee.class, new Delegate() {
				@Override
				public Object create(Object... params) {
					return new BindeeToBinder1();
				}
			}, Binder1.class);
			ioc.bind(Bindee.class, new Delegate() {
				@Override
				public Object create(Object... params) {
					return new BindeeToBinder1And2();
				}
			}, Binder1.class, Binder2.class);		
		}
	}

	@SuppressWarnings("rawtypes")
	@Test
	public final void testBindObjectDelegate() {
		ioc = new IoC();
		try {
			ioc.bind(Bindee.class, new Delegate() {
				@Override
				public Object create(Object... params) {
					return new BindeeNoParam();
				}
			});
			ioc.bind(Bindee.class, new Delegate() {
				@Override
				public Object create(Object... params) {
					return new BindeeToBinder1();
				}
			}, Binder1.class);
			ioc.bind(Bindee.class, new Delegate() {
				@Override
				public Object create(Object... params) {
					return new BindeeToBinder1And2();
				}
			}, Binder1.class, Binder2.class);
	
		} catch (Throwable e) {
			fail("BindFailed");
		}
		
		Bindee b;
		try {
			b = (Bindee) ioc.resolve(Bindee.class);
		} catch (Throwable e) {
			b = null;
		}
		assertSame(b.getClass(), new BindeeNoParam().getClass());		
		
		try {
			b = (Bindee) ioc.resolve(Bindee.class, new Binder1());
		} catch (Throwable e) {
			b = null;
		}
		assertSame(b.getClass(), new BindeeToBinder1().getClass());

		try {
			b = (Bindee) ioc.resolve(Bindee.class, new Binder1(), new Binder2());
		} catch (Throwable e) {
			b = null;
		}
		assertSame(b.getClass(), new BindeeToBinder1And2().getClass());
		
		try {
			b = (Bindee) ioc.resolve(Bindee.class, new Binder1(), new Binder2(), new Binder2());
		} catch (Throwable e) {
			b = null;
		}
		assertNull(b);	
	}

	@Test
	public final void testBindBindable() {
		ioc = new rmit.connectN.util.ioc.IoC();
		try {
			ioc.bind(new BindableImpl());
		} catch (Throwable e) {
			fail("BindFailed");
		}
		Bindee b;
		try {
			b = (Bindee) ioc.resolve(Bindee.class);
		} catch (Throwable e) {
			b = null;
		}
		assertSame(b.getClass(), new BindeeNoParam().getClass());		
		
		try {
			b = (Bindee) ioc.resolve(Bindee.class, new Binder1());
		} catch (Throwable e) {
			b = null;
		}
		assertSame(b.getClass(), new BindeeToBinder1().getClass());

		try {
			b = (Bindee) ioc.resolve(Bindee.class, new Binder1(), new Binder2());
		} catch (Throwable e) {
			b = null;
		}
		assertSame(b.getClass(), new BindeeToBinder1And2().getClass());
		
		try {
			b = (Bindee) ioc.resolve(Bindee.class, new Binder1(), new Binder2(), new Binder2());
		} catch (Throwable e) {
			b = null;
		}
		assertNull(b);			
	}
}
