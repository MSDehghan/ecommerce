package com.bestretail.ecommerce.common;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {
    @Basic
    private String province;
    @Basic
    private String city;
    @Basic
    private String zipCode;
    @Basic
    private String phone;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(province, address.province) &&
                Objects.equals(city, address.city) &&
                Objects.equals(zipCode, address.zipCode) &&
                Objects.equals(phone, address.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(province, city, zipCode, phone);
    }
}
