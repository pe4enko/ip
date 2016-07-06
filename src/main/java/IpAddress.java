import com.google.common.primitives.UnsignedBytes;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: comment
 * @author madmax
 * @since 22.06.2016
 */
public class IpAddress implements Comparable<IpAddress> {
	private static final Logger log = LoggerFactory.getLogger(IpAddress.class);
	private long ip;

	public IpAddress(long ip) {
		this.ip = ip;
	}

	public IpAddress(byte[] address) {
		ip = makeMagic(address);
	}

	public IpAddress(byte firstOctet, byte secondOctet, byte thirdOctet, byte fourthOctet) {
		ip = makeMagic(new byte[]{firstOctet, secondOctet, thirdOctet, fourthOctet});
	}

	public IpAddress(String address) {
		ip = makeMagic(parseIpAddressToByteArray(address));
	}

	public byte[] getAddress() {
		byte[] addr = new byte[4];

		addr[0] = (byte) ((ip >>> 24) & 0xFF);
		addr[1] = (byte) ((ip >>> 16) & 0xFF);
		addr[2] = (byte) ((ip >>> 8) & 0xFF);
		addr[3] = (byte) (ip & 0xFF);
		return addr;
	}

	/**
	 * Возвращает следующий Ip
	 * @return следующий IP адресс
	 */
	public IpAddress inc() {
		ip++;
		return this;
	}

	public IpAddress dec() {
		ip--;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		IpAddress ipAddress = (IpAddress) o;

		return ip == ipAddress.ip;

	}

	@Override
	public int hashCode() {
		return Long.hashCode(ip);
	}

	@Override
	public int compareTo(IpAddress anotherIpAddress) {
		return Long.compare(this.ip, anotherIpAddress.ip);
	}

	@Override
	public String toString() {
		return UnsignedBytes.join(".", getAddress());
	}

	private byte[] parseIpAddressToByteArray(String address) {
		Validate.notBlank(address, "Переданная строка [%s] не является валидным ip адрессом", address);

		byte[] octets = new byte[4];
		try {
			String[] split = StringUtils.split(address, '.');
			for (int i = 0; i < split.length; i++) {
				String s = split[i];
				byte octet = UnsignedBytes.parseUnsignedByte(s);
				octets[i] = octet;
			}
		} catch (Exception e) {
			throw new IllegalArgumentException(String.format("Переданная строка [%s] не является валидным ip адрессом", address), e);
		}
		return octets;
	}

	private long makeMagic(byte[] bytes) {
		long address = ((bytes[0] << 24) & 0xFF000000);
		address |= ((bytes[1] << 16) & 0xFF0000);
		address |= ((bytes[2] << 8) & 0xFF00);
		address |= bytes[3] & 0xFF;
		return address;
/*		long result = 0;
		byte shift = 0;
		for (int i = bytes.length - 1; i >= 0; i--) {
			result += (long) (bytes[i] & 0xFF) << shift;
			shift += 4;
		}
		return result;*/
	}


}
