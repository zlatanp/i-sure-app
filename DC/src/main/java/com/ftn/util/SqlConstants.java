package com.ftn.util;

/**
 * Created by Jasmina on 21/11/2017.
 */
public class SqlConstants {

    public static final String ACTIVE = "`active` = true";

    public static final String UPDATE = "UPDATE ";

    public static final String SOFT_DELETE = " SET `active` = false, updated = now() WHERE id = ?";
}
