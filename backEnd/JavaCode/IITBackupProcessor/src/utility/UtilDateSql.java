package utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UtilDateSql {
	private static final DateFormat utilDateFormatter = new SimpleDateFormat("dd-MM-yyyy");
	private static final DateFormat sqlDateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat timeDateFormatter = new SimpleDateFormat("HH:mm:ss");

	public static java.sql.Date utilDateToSqlDate(java.util.Date uDate) throws ParseException
	{
		return java.sql.Date.valueOf(sqlDateFormatter.format(uDate));
	}

	public static java.util.Date sqlDateToutilDate(java.sql.Date sDate) throws ParseException
	{
		return (java.util.Date)utilDateFormatter.parse(utilDateFormatter.format(sDate));
	}
	public static java.sql.Time utilTimeToSqlTime(String str) throws ParseException
	{
		return (java.sql.Time.valueOf(str));
	}
}
