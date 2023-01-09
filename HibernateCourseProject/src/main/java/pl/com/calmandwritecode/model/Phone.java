package pl.com.calmandwritecode.model;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;

@Embeddable
public class Phone {

    @NotNull
    protected String type;
    @NotNull
    protected String number;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
