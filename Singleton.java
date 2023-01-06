
import java.io.ObjectStreamException;
import java.io.Serializable;

public final class Singleton implements Cloneable, Serializable {
	private volatile static Singleton singleton = null;

	private Singleton() {
		if (singleton != null) {
			throw new InstantiationError("object creation is not allowed!");
		}
	}

	public static synchronized Singleton getInstance() {
		if (singleton == null)
			singleton = new Singleton();

		return singleton;
	}

	public Object clone() throws CloneNotSupportedException {
		// return Singleton.getInstance();
		throw new CloneNotSupportedException();
	}

	protected Object readResolve() throws ObjectStreamException {
		return singleton;
	}

	public static void main(String[] args) {

		try {
			Singleton ut = Singleton.getInstance();
			try {
				Singleton ut2 = (Singleton) ut.clone();
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Singleton dt = ut.getClass().newInstance();
			System.out.println(ut == dt);
			System.out.println(ut.hashCode() + "::" + dt.hashCode());

		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
