package com.ftn.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.Date;


public class DateAdapter extends XmlAdapter<String, Date> {

    public Date unmarshal(String value) {
        return (MyDatatypeConverter.parseDate(value));
    }

    public String marshal(Date value) {
        return (MyDatatypeConverter.printDate(value));
    }

}
