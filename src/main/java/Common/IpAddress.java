package Common;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class IpAddress {
    int first;
    int second;
    int third;
    int fourth;

    protected IpAddress(){};

    public IpAddress(int first, int second, int third, int fourth) {
        setFirst(first);
        setSecond(second);
        setThird(third);
        setFourth(fourth);
    }

    private void checkOctet(int octet){
        if(octet >= 0 && octet < 255)
            return;
        throw new IllegalArgumentException("Each octet value must be between 0 and 254.");
    }

    private void setFirst(int fist) {
        checkOctet(fist);
        this.first = fist;
    }

    private void setSecond(int second) {
        checkOctet(second);
        this.second = second;
    }

    private void setThird(int third) {
        checkOctet(third);
        this.third = third;
    }

    private void setFourth(int fourth) {
        checkOctet(fourth);
        this.fourth = fourth;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public int getThird() {
        return third;
    }

    public int getFourth() {
        return fourth;
    }

    @Override
    public String toString() {
        return String.valueOf(first) +
                '.' +
                second +
                '.' +
                third +
                '.' +
                fourth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IpAddress ipAddress = (IpAddress) o;
        return first == ipAddress.first &&
                second == ipAddress.second &&
                third == ipAddress.third &&
                fourth == ipAddress.fourth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, third, fourth);
    }
}
