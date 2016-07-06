import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: comment
 * @author madmax
 * @since 06.07.2016
 */
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		IpAddress fromIp = new IpAddress("192.168.1.253");
		IpAddress toIp = new IpAddress("192.168.2.7");
//		byte[] f = new byte[]{(byte) 127, (byte) 120, (byte) 1, (byte) 100};
//		byte[] t = new byte[]{(byte) 127, (byte) 120, (byte) 2, (byte) 14};

//		long start = (f[0] & 0xFF) * (256 << 3) + (f[1] & 0xFF) * (256 << 2) + (f[2] & 0xFF) * (256 << 1) + (f[3] & 0xFF);
//		long finish = (t[0] & 0xFF) * (256 << 3) + (t[1] & 0xFF) * (256 << 2) + (t[2] & 0xFF) * (256 << 1) + (t[3] & 0xFF);

//		long start = encode(f);
//		long finish = encode(t);

//		for (long i = start; i <= finish; i++) {
//			System.out.println(((i >> 12) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 4) & 0xFF) + "." + (i & 0xFF));
//			System.out.println(((i >> 24) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + (i & 0xFF));
//		}
		while (fromIp.compareTo(toIp) < 0) {
			System.out.println(fromIp.inc().toString());
		}
	}
}
