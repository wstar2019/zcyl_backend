package utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EncodeUtil {
	
	public static String toUTF8( String en ){
		
        if ( en == null ) {
            return "";
        }
        try {
            return new String(en.getBytes("8859_1"),"utf8");
        } catch(Exception e) {
            return en;
        }
    }
	
	public static String to8859_1( String en ){
		
        if ( en == null ) {
            return null;
        }
        try {
            return new String(en.getBytes("utf8"), "8859_1");
        } catch(Exception e) {
            return en;
        }
    }
	
	public static ArrayList<String> getPropertiesOfNData(String xml){
		ArrayList<String> array = new ArrayList<String>();
		try{
			xml = xml.replace("\n", "");
			int length = xml.length();
			int startInd = 0;
			int endInd = 0;
			String property = "";	
			while(true)
			{
				startInd = xml.indexOf("<", startInd);
				if(startInd == -1) 	break;
				endInd = xml.indexOf(">", startInd);
				if(endInd == -1) break;
				property = xml.substring(startInd + 1, endInd);
				startInd = xml.indexOf("<", endInd);
				if(startInd == -1) break;
				startInd = xml.indexOf(">", startInd);
				if(startInd == -1) break;
				array.add(property);
				if(startInd == (length - 1)) break;
			}
			
			if(startInd < (length - 1)) array = null;
		}
		catch(Exception e)
		{
		}
		return array;
	}
		
	public static Integer parseInteger(String en){
		
        if ( en == null ) return null;
        
        if ( en.trim().equals("") ) return null;
        
        return Integer.parseInt(en);
    }
	
	public static Integer parseIntegerByZero(String en){
		
        if ( en == null ) return new Integer(0);
        
        if ( en.trim().equals("") ) return new Integer(0);
        
        return Integer.parseInt(en);
    }
	
	public static Double parseDouble(String en){
		
        if ( en == null ) return null;
        
        if ( en.trim().equals("") ) return null;
        
        return Double.parseDouble(en);
    }
	
	public static Double parseDoubleByZero(String en){
		
        if ( en == null ) return 0.0;
        
        if ( en.trim().equals("") ) return new Double(0.0);
        
        return Double.parseDouble(en);
    }
	
	public static Double toZero(Double en) {
		
		if ( en == null || en.isNaN() ) return new Double(0.0);
		return en;
	}
	
	public static Integer toZero(Integer en) {
		
		if ( en == null ) return new Integer(0);
		return en;
	}
	
	public static String parseString(Double en, boolean isEmpty) {
		
		if ( en == null ) return "";
		
		if ( en.equals(0.0) ) {
			
			if ( isEmpty ) return "";
			else return "0";
		}
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		
		return nf.format(en);
	}
	
	public static String parseString(Double en, boolean isEmpty, int radio) {
		
		if ( en == null ) return "";
		
		if ( en.equals(0.0) ) 
		{
			if ( isEmpty ) return "";
			else return "0";
		}
		
		en = Math.round( en * new Double( Math.pow(10.0, radio) ) ) / Math.pow(10.0, radio);
		
		NumberFormat nf = NumberFormat.getInstance();
		nf.setGroupingUsed(false);
		
		return nf.format(en);
	}
	
	public static Double round(Double en, int radio) {
		
		if ( en == null ) return 0.0;
		
		en = Math.round( en * new Double( 10 * radio ) ) / new Double( 10 * radio );
		
		return en;
		
	}
	
	public static String roundHalf(Double en, int radio){
		String str;		
		if(radio == 3){
			en = (en >= 0 ? en + 0.0005 : en - 0.0005);
			str = en.toString() + "0000";
			en = new Double(str.substring(0, str.indexOf(".") + 4));
			return String.format("%.3f", en);
		}
		else if(radio == 2){
			en = (en >= 0 ? en + 0.005 : en - 0.005);
			str = en.toString() + "000";			
			en = new Double(str.substring(0, str.indexOf(".") + 3));
			return String.format("%.2f", en);
		}
		else{
			en = (en >= 0 ? en + 0.05 : en - 0.05);
			str = en.toString() + "00";
			en = new Double(str.substring(0, str.indexOf(".") + 2));
			return String.format("%.1f", en);
		}
	}
	
	public static String parseString(Integer en, boolean isEmpty) {
		
		if ( en == null ) return "";
		
		if ( en.equals(0) ) {
			
			if ( isEmpty ) return "";
			else return "0";
		}
		
		return en.toString();
		
	}
	
	public static String toEmpty( String en ) {
		
		if ( en == null ) return "";
		else return en;
	}
	
	public static String toNull( String en )
	{
		if ( en == null ) return "null";
		else return en;
	}
	
	public static String toSpace( String en ) {
		
		if ( en == null || en.trim().equals("") ) return "&nbsp;";
		else return en;
	}
	
	@SuppressWarnings("deprecation")
	public static int getDay(int year, int month, int date)
	{
		Date value = convertDate(year + "-" + month + "-" + date);
		
		return value.getDay();
	}
	
	public static Integer getPrevYearByMonth(Integer year, Integer month)
	{
		if ( month == 1 ) return year-1;
		else return year;
	}
	
	public static Integer getPrevMonthByMonth(Integer year, Integer month)
	{
		if ( month == 1 ) return 12;
		else return month-1;
	}
	
	public static boolean beforeDate(String currentDateStr, String compareDateStr)
	{
		if ( currentDateStr.equals("") || compareDateStr.equals("") ) return true;
		
		Date currentDate = convertDate(currentDateStr);
		Date compareDate = convertDate(compareDateStr);
		
		if ( compareDate.before(currentDate) ) return true;
			
		return false;
	}
	
	public static boolean afterDate(String currentDateStr, String compareDateStr)
	{
		if ( currentDateStr.equals("") || compareDateStr.equals("") ) return true;
		
		Date currentDate = convertDate(currentDateStr);
		Date compareDate = convertDate(compareDateStr);
		
		if ( compareDate.after(currentDate) ) return true;
			
		return false;
	}
	
	public static boolean beforeAndEqualsDate(String currentDateStr, String compareDateStr)
	{
		if ( currentDateStr.equals("") || compareDateStr.equals("") ) return true;
		
		Date currentDate = convertDate(currentDateStr);
		Date compareDate = convertDate(compareDateStr);
		
		if ( compareDate.before(currentDate) || currentDate.equals(compareDate) ) return true;
			
		return false;
	}
	
	public static boolean afterAndEqualsDate(String currentDateStr, String compareDateStr)
	{
		if ( currentDateStr.equals("") || compareDateStr.equals("") ) return true;
		
		Date currentDate = convertDate(currentDateStr);
		Date compareDate = convertDate(compareDateStr);
		
		if ( compareDate.after(currentDate) || currentDate.equals(compareDate) ) return true;
			
		return false;
	}
	
	public static Date convertDate( String str ) {
		
		if ( str == null || str.equals("") ) return null;
		
    	Calendar c = Calendar.getInstance();
    	Date date = null;
    	
    	try
    	{
	    	String[] dateArray = str.split("-");
	    	
	    	c.set( Integer.parseInt(dateArray[0])
	    		  ,Integer.parseInt(dateArray[1])-1
	    		  ,Integer.parseInt(dateArray[2])
	    		  ,0
	    		  ,0
	    		  ,0
	    		  );
	    	
	    	date = c.getTime();
    	
	    	return date;
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
		
	}
	
	public static Date convertDate( String str, Integer hour ) {
		
		if ( str == null || str.equals("") ) return null;
		
    	Calendar c = Calendar.getInstance();
    	Date date = null;
    	
    	try
    	{
	    	String[] dateArray = str.split("-");
	    	
	    	c.set( Integer.parseInt(dateArray[0])
	    		  ,Integer.parseInt(dateArray[1])-1
	    		  ,Integer.parseInt(dateArray[2])
	    		  ,hour.intValue()
	    		  ,0
	    		  ,0
	    		  );
	    	
	    	date = c.getTime();
    	
	    	return date;
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
		
	}
	
	public static Date convertDate( String str, Integer hour, Integer minute ) {
		
		if ( str == null || str.equals("") ) return null;
		
    	Calendar c = Calendar.getInstance();
    	Date date = null;
    	
    	try
    	{
	    	String[] dateArray = str.split("-");
	    	
	    	c.set( Integer.parseInt(dateArray[0])
	    		  ,Integer.parseInt(dateArray[1])-1
	    		  ,Integer.parseInt(dateArray[2])
	    		  ,hour.intValue()
	    		  ,minute.intValue()
	    		  ,0
	    		  );
	    	
	    	date = c.getTime();
    	
	    	return date;
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
		
	}
	
	public static Date convertTime( String str ) {
		
		if ( str == null || str.equals("") ) return null;
		
    	Calendar c = Calendar.getInstance();
    	Date date = null;
    	
    	try
    	{
	    	String[] dateArray = str.split(":");
	    	
	    	c.set( 0
	    		  ,0
	    		  ,0
	    		  ,Integer.parseInt(dateArray[0])
	    		  ,Integer.parseInt(dateArray[1])
	    		  ,Integer.parseInt(dateArray[2])
	    		  );
	    	
	    	date = c.getTime();
    	
	    	return date;
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
		
	}
	
	public static String convertStrByZero( Date date ) {
		
    	String dateStr = "";
    	
    	if ( date == null ) return "";
    	
    	CurrentDateTime cdt = new CurrentDateTime(date);
    	
    	try 
    	{
			dateStr = cdt.getTotalDate("-");
		} 
    	catch (Exception e) 
    	{
			return null;
		}
    	
    	return dateStr;
	}
	
	public static String convertStr( Date date ) {
		
    	String dateStr = "";
    	
    	if ( date == null ) return "";
    	
    	Calendar c = Calendar.getInstance();
    	
    	c.setTime(date);
    	
    	dateStr = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
    	
    	return dateStr;
		
	}
	
	public static String convertStr( Date date, String format ) {
		
    	String dateStr = "";
    	
    	if ( date == null ) return "";
    	
    	Calendar c = Calendar.getInstance();
    	
    	c.setTime(date);
    	
    	dateStr = c.get(Calendar.YEAR) + format + (c.get(Calendar.MONTH)+1) + format + c.get(Calendar.DATE);
    	
    	return dateStr;
		
	}
	
	public static String convertStr( Date date, String format, String timeFlag ) {
		
    	String dateStr = "";
    	
    	if ( date == null ) return "";
    	
    	Calendar c = Calendar.getInstance();
    	
    	c.setTime(date);
    	
    	dateStr = c.get(Calendar.YEAR) + format + (c.get(Calendar.MONTH)+1) + format + c.get(Calendar.DATE);
    	
    	if ( timeFlag == null || timeFlag.equals("") ) return dateStr;
    	
    	if ( timeFlag.equals("hour") ) dateStr += " " + c.get(Calendar.HOUR_OF_DAY);
    	
    	if ( timeFlag.equals("minute") ) dateStr += " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
    	
    	if ( timeFlag.equals("second") ) dateStr += " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    	
    	return dateStr;
		
	}
	
	public static String convertStrByTime( Date date, String format, String timeFlag ) {
		
    	String dateStr = "";
    	
    	if ( date == null ) return "";
    	
    	Calendar c = Calendar.getInstance();
    	
    	c.setTime(date);
    	
    	if ( timeFlag == null || timeFlag.equals("") ) dateStr = c.get(Calendar.HOUR_OF_DAY) + format + c.get(Calendar.MINUTE) + format + c.get(Calendar.SECOND);
    	else if ( timeFlag.equals("hour") ) dateStr = c.get(Calendar.HOUR_OF_DAY) + "";
    	else if ( timeFlag.equals("minute") ) dateStr = c.get(Calendar.HOUR_OF_DAY) + format + c.get(Calendar.MINUTE);
    	else if ( timeFlag.equals("second") ) dateStr = c.get(Calendar.HOUR_OF_DAY) + format + c.get(Calendar.MINUTE) + format + c.get(Calendar.SECOND);
    	else dateStr = "00" + format + "00";
    	
    	if ( dateStr.equals("0") || dateStr.equals("0:0") || dateStr.equals("0:0:0") ) dateStr = "";
    	
    	return dateStr;
		
	}
	
	@SuppressWarnings("unchecked")
	public static List getVacationKindList()
	{
		List returnList = new ArrayList();
		
		String[] vacation = {"67", "사결"};
		String[] personal = {"68", "휴가"};
		
		returnList.add(vacation);
		returnList.add(personal);
		
		return returnList;
	}

	@SuppressWarnings("unchecked")
	public static String getVacationKind( Integer kind )
	{
		List vacationKindList = getVacationKindList();
		
		for ( int i = 0; i < vacationKindList.size(); i++ )
		{
			String[] kinds = (String[]) vacationKindList.get(i);
			
			if ( parseIntegerByZero(kinds[0]).intValue() == kind.intValue() ) return kinds[1];
		}

		return "";
	}

	public static int getTotalNumberOfDates( Date startDate, Date endDate )
	{
		int totalNumber = 0;
		long startTime = startDate.getTime();
		long endTime = endDate.getTime();
		long differenceTime = 0;
		long oneDayTime = 86400000;
		
		if ( startTime > endTime ) differenceTime = startTime - endTime;
		else differenceTime = endTime - startTime;
		
		totalNumber = (int) (differenceTime / oneDayTime) + 1;
		
		return totalNumber;
	}
	
	public static int getRowsString(String en)
	{
		int line = 0;
		
		if ( en == null || en.equals("") ) return 1;
		
		for ( int i = 0; i < en.length(); i++ )
		{
			if ( en.charAt(i) == '\n' ) line++;
		}
		
		return (int) Math.round(line * 1.3) + 1;
	}
	
	@SuppressWarnings("unchecked")
	public static int getListSize(List source)
	{
		if ( source == null || source.size() < 1 ) return 0;
		
		return source.size();
	}
	
	public static String getItemFromXML(){
		String result = "";
		return result;
	}
	
	public static String getElement(String[] elements, String defaultValue){		
		if(elements == null || elements.length != 2) return defaultValue;
		return elements[1];
	}
	
	public static Integer convert2Int(Object obj)
	{
		Integer ret = -1;
		try{
			ret = Integer.parseInt(obj.toString());
		}
		catch(Exception e){
			ret = 0;
		}
		
		return ret;
	}
	
	public static Double convert2Double(Object obj)
	{
		Double ret = 0.0;
		try{
			ret = Double.parseDouble(obj.toString());
		}
		catch(Exception e){
			ret = 0.0;
		}
		
		return ret;
	}
	
	public static String convert2Str(Object obj)
	{
		String ret = "";
		try{
			ret = obj.toString();
		}
		catch(Exception e){
			ret = "";
		}
		
		return ret;
	}
	
}