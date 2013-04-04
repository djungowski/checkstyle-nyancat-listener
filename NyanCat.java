import com.djungowski.listeners.NyanCatListener;

public class NyanCat
{
	public static void main(final String[] args)
	{
		final NyanCatListener cat = new NyanCatListener();
		while (true) {
			cat.fileStarted(null);
		}
	}
}