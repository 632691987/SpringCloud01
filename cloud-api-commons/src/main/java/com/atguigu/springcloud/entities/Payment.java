package com.atguigu.springcloud.entities;

import java.io.Serializable;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Payment implements Serializable {

    private Long id;

    private String serial;

    public Payment() {
    }

    public Payment(final Long id, final String serial) {
        this.id = id;
        this.serial = serial;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(final String serial) {
        this.serial = serial;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Payment payment = (Payment) o;
        return Objects.equal(id, payment.id) && Objects.equal(serial, payment.serial);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, serial);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("id", id)
                          .add("serial", serial)
                          .toString();
    }
}
