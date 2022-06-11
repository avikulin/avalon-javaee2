package Common.Classes;

import javax.persistence.Embeddable;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

@Embeddable
public class IpAddress {
    private String ipAddressValue;

    protected IpAddress(){};

    public IpAddress(int first, int second, int third, int fourth) {
        checkOctet(first);
        checkOctet(second);
        checkOctet(third);
        checkOctet(fourth);
        this.ipAddressValue = Integer.toString(first) + '.' + second + '.' + third + '.' + fourth;
    }

    public IpAddress(String strIpAddr) {
        checkIpString(strIpAddr);
        this.ipAddressValue = strIpAddr;
    }

    public void checkIpString(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address string must be not null & non-empty");
        }

        try {
            Object res = InetAddress.getByName(address);
            if (!(res instanceof Inet4Address) && !(res instanceof Inet6Address)){
                throw new IllegalArgumentException("String is not represents a valid IPv4 or IPv6 network address");
            }
        } catch (final UnknownHostException exception) {
            throw new IllegalArgumentException("String is not represents a valid IPv4 or IPv6 network address");
        }
    }

    private void checkOctet(int octet){
        if(octet >= 0 && octet < 255)
            return;
        throw new IllegalArgumentException("Each octet value must be between 0 and 254.");
    }

    @Override
    public String toString() {
        return this.ipAddressValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpAddress otherIpAddress = (IpAddress) o;
        return this.ipAddressValue.equals(otherIpAddress.ipAddressValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.ipAddressValue);
    }
}
