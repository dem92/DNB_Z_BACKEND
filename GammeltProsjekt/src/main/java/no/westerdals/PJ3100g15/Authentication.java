package no.westerdals.PJ3100g15;

public class Authentication {
    private final long birthNumber;
    private final String password;

    public Authentication(long birthNumber, String password) {
        this.birthNumber = birthNumber;
        this.password = password;
    }

    public long getBirthNumber() {
        return birthNumber;
    }

    public String getPassword() {
        return password;
    }
}
