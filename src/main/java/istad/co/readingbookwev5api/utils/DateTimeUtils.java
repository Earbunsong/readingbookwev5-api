package istad.co.readingbookwev5api.utils;

import java.sql.Timestamp;

public class DateTimeUtils {

    public static Timestamp getTS(){
        return new Timestamp(System.currentTimeMillis());
    }
}
