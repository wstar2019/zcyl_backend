package utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("unchecked")
public class CurrentDateTime {
    
    private Calendar c;

    public CurrentDateTime() {
        c=Calendar.getInstance();
        c.setMinimalDaysInFirstWeek(7);
    }
    
    public CurrentDateTime( Date date ) {
        c=Calendar.getInstance();
        c.setTime(date);
    }
    
	public String getTotalDate() throws Exception
	{
	    return getYear() + getMonth() + getDate();
	}
    
    public Date getTotalDateTime() throws Exception
    {
    	return new Date();
    }
    
    public String getTotalDateByFirst(String format) throws Exception
    {
        return getYear()+format+getMonth()+format+"01";
    }
    
    public String getTotalDateByPrevMonth(String format) throws Exception
    {
    	if ( Integer.parseInt(getMonth()) > 1 )
    		return getYear()+format+(Integer.parseInt(getMonth())-1)+format+getDate();
    	else
    		return (Integer.parseInt(getYear())-1)+format+12+format+getDate();
    }

    public String getTotalTime() throws Exception
    {
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String time = "";

        if ( hour < 10 ) time = time + "0" + Integer.toString(hour);
        else time = time + Integer.toString(hour);
        
        if ( minute < 10 ) time = time + "0" + Integer.toString(minute);
        else time = time + Integer.toString(minute);

        if ( second < 10 ) time = time + "0" + Integer.toString(second);
        else time = time + Integer.toString(second);
        
        return time;
    }
    
    public String getTotalTime(String format) throws Exception
    {
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        int second = c.get(Calendar.SECOND);
        String time = "";

        if ( hour < 10 ) time = time + "0" + Integer.toString(hour);
        else time = time + Integer.toString(hour);
        
        if ( minute < 10 ) time = time + format + "0" + Integer.toString(minute);
        else time = time + format + Integer.toString(minute);

        if ( second < 10 ) time = time + format + "0" + Integer.toString(second);
        else time = time + format + Integer.toString(second);

        return time;
    }
    
    public String getDay() throws Exception
    {
        return Integer.toString(c.get(Calendar.DAY_OF_WEEK));
    }
    
    public String getYear() throws Exception
    {
        return Integer.toString(c.get(Calendar.YEAR));
    }
    
    public String getJucheYear() throws Exception
    {
        return Integer.toString(c.get(Calendar.YEAR) - 1911); 
    }
    
    public String getMonth() throws Exception
    {
        String month = Integer.toString(c.get(Calendar.MONTH) + 1);
        
        if ( Integer.parseInt(month) < 10 ) month = "0" + month;        
        return month;
    }
    
    public String getMonth(boolean zeroFlag) throws Exception
    {
        String month = Integer.toString(c.get(Calendar.MONTH) +1);
        
        if ( zeroFlag && Integer.parseInt(month) < 10 ) month = "0" + month;
        return month;
    }
    
    public String getWeekOfMonth() throws Exception
    {
        String week = Integer.toString(c.get(Calendar.WEEK_OF_MONTH));
        return week;
    }
    
    public String getSector() throws Exception
    {
        String month = getMonth();
        
        int sector = ( Integer.parseInt( month ) + 2 ) / 3;
        
        return Integer.toString(sector);
    }
    
    public String getTenDay() throws Exception
    {
        String date = getDate();
        
        int tenDay = (Integer.parseInt(date)-1) / 10;
        
        if ( tenDay > 2 ) tenDay = 2;
        
        return Integer.toString(tenDay);
    }
    
    public String startDateByTenDay(int tenDay)
    {
    	if ( tenDay < 1 || tenDay > 2 ) return "01";
    	
    	return ((tenDay * 10) + 1) + "";
    }
    
    public String endDateByTenDay(int year, int month, int tenDay)
    {
    	if ( tenDay < 0 || tenDay > 2 ) return "10";
    	
    	if ( tenDay == 0 || tenDay == 1 )
    		return ((tenDay + 1) * 10) + "";
    	else 
    		return (getLengthOfMonth(year, month)) + "";
    }
    
    public String getHour() throws Exception{
        
        return Integer.toString(c.get(Calendar.HOUR_OF_DAY));
    }
    
    public String getMinute() throws Exception{
        
        return Integer.toString(c.get(Calendar.MINUTE));
    }
    
    public String getSecond() throws Exception{
        
        return Integer.toString(c.get(Calendar.SECOND));
    }
    
    public String getMilliSecond() throws Exception{
        
        return Integer.toString(c.get(Calendar.MILLISECOND));
    }
    
	public List getHalfYearCodeList() throws Exception{
        List halfs = new ArrayList();
        
    	HashMap m = new HashMap();
        m.put("code", Integer.toString(1));
        m.put("value", "상반년");
        halfs.add(m);

        HashMap m1 = new HashMap();
        m1.put("code", Integer.toString(2));
        m1.put("value", "하반년");
        halfs.add(m1);

        return halfs;
    }

    public String getHalfYearStr() throws Exception
    {
    	if ( EncodeUtil.parseIntegerByZero(getMonth()).intValue() < 7 ) return "F";		//상반년
    	else return "L";			//하반년
    }
    
    public String getHalfYear() throws Exception{
        String month = getMonth();
        String halfYear = "1";
        
        if ( Integer.parseInt(month) < 7 ) halfYear = "1";
        else halfYear = "2";
        
        return halfYear;
    }
    
    public String getHalfYear( String code ) throws Exception{

    	 String halfYear="상반년";
    	 
         if ( code.equals("1") ) halfYear = "상반년";
         else if ( code.equals("2") ) halfYear = "하반년";
         
         return halfYear;
     }
     
    public String getDate() throws Exception{
        String date = Integer.toString(c.get(Calendar.DATE));
        
        if ( Integer.parseInt(date) < 10 ) date = "0" + date;        
        return date;
    }

    public String getDate(boolean zeroFlag) throws Exception
    {
        String date = Integer.toString(c.get(Calendar.DATE));
        
        if ( zeroFlag && Integer.parseInt(date) < 10 ) date = "0" + date;        
        return date;
    }

	public List getYearsCodeList() throws Exception{
    	
    	List years = new ArrayList();
        int year = Integer.parseInt(getYear());        

        for ( int i = year - 2; i < year + 2; i++ ) {
        	
        	HashMap y = new HashMap();
            y.put("code", Integer.toString(i));
            y.put("value", Integer.toString(i));
            
            years.add(y);
        }
        
        return years;        
    }

    public List getYearsCodeListAll() throws Exception{
    	
    	List years = new ArrayList();
        int year = Integer.parseInt(getYear());        

        for ( int i = 2012; i < year + 2; i++ ) {
        	
        	HashMap y = new HashMap();
            y.put("code", Integer.toString(i));
            y.put("value", Integer.toString(i));
            
            years.add(y);
        }
        
        return years;        
    }

    public List getSectorCodeList() throws Exception{
    	List sectors = new ArrayList();
        
        for ( int i = 1; i <= 4; i++ ) {
        	
        	HashMap s = new HashMap();
            s.put("code", Integer.toString(i));
            s.put("value", Integer.toString(i)+" - 4");
            
            sectors.add(s);
        }
        return sectors;
    }
    
    public Integer getSector( int month ) throws Exception{
    	
    	Integer sector = null;
    	
    	int s = ((month - 1) / 3) + 1;    	
    	sector = new Integer(s);
    	
    	return sector;
    }

    public List getMonthCodeList() throws Exception{
        List months = new ArrayList();
        
        for ( int i = 1; i <= 12; i++ ) {
        	
        	HashMap m = new HashMap();
            m.put("code", Integer.toString(i));
            m.put("value", Integer.toString(i));
            
            months.add(m);
        }
        return months;
    }

    public List getMonthCodeList(int sector) throws Exception{
        List months = new ArrayList();
        
        for ( int i = 1; i <= 3; i++ ) {
        	
        	HashMap m = new HashMap();
            m.put("code", Integer.toString( i + (sector-1)*3));
            m.put("value", Integer.toString( i + (sector-1)*3));
            
            months.add(m);
        }
        return months;
    }

    public String[] getMonthListBySector(int sector) throws Exception
    {
        String[] months = new String[3];
        
        for ( int i = 1; i <= 3; i++ ) 
        {
            months[i-1] = Integer.toString( i + (sector-1)*3);
        }
        
        return months;
    }

    @SuppressWarnings("deprecation")
	public long getTimeStampByHour(String dateHour) throws Exception {
    	
    	String[] hourArray = dateHour.split(":");
    	String[] dateArray = hourArray[0].split("-");
    	
		int hour = EncodeUtil.parseInteger(hourArray[1]).intValue();
		int year = EncodeUtil.parseInteger(dateArray[0]).intValue();
		int month = EncodeUtil.parseInteger(dateArray[1]).intValue() - 1;
		int date = EncodeUtil.parseInteger(dateArray[2]).intValue();
		int minute = 0;
		
		Date prevDate = new Date(year, month, date, hour, minute);
		return prevDate.getTime();
    }
    
    @SuppressWarnings("deprecation")
	public long getTimeStampByDate(String dateStr) throws Exception {
    	
    	String[] dateArray = dateStr.split("-");
    	
		int year = EncodeUtil.parseInteger(dateArray[0]).intValue();
		int month = EncodeUtil.parseInteger(dateArray[1]).intValue() - 1;
		int date = EncodeUtil.parseInteger(dateArray[2]).intValue();
		int hour = 0;
		int minute = 0;
		
		Date prevDate = new Date(year, month, date, hour, minute);
		return prevDate.getTime();
    }
    
    public int getNumberOfDaysByOne( Date startDate, Date endDate )
    {
    	int numberOfDays = 1;
    	
    	long startTimeStamp = startDate.getTime();
    	long endTimeStamp = endDate.getTime();
    	
    	long difference = Math.abs(endTimeStamp - startTimeStamp);
    	
    	numberOfDays += (int) difference / (24*60*60*1000);
    	
    	return numberOfDays;
    }
    
    public String getNextDate(String dateStr) throws Exception
    {
		String[] split = dateStr.split("-");
		Integer[] values = getNextDate(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
		int year = values[0];
		int month = values[1];
		int date = values[2];
		dateStr = year + "-" + (month > 9 ? month + "" : "0" + month) + "-" + (date > 9 ? +
				date + "" : "0" + date);
		return dateStr;
    }
    
    public String getNextMonth(String dateStr) throws Exception
    {
		String[] split = dateStr.split("-");
		Integer[] values = getNextMonth(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
		int year = values[0];
		int month = values[1];
		int date = values[2];
		dateStr = year + "-" + (month > 9 ? month + "" : "0" + month) + "-" + (date > 9 ? +
				date + "" : "0" + date);
		return dateStr;
    }
    
    public boolean CompareDate(String startDate, String endDate) throws Exception
    {
    	long startTimeStamp = getTimeStampByDate(startDate);
    	long endTimeStamp   = getTimeStampByDate(endDate);
    	
    	if ( startTimeStamp <= endTimeStamp ) return true;
    	return false;
    }
    
    @SuppressWarnings("deprecation")
	public String getDateHourByTimeStamp(long timestamp) throws Exception {
    	
    	String dateHour = "";
    	Date realDate = new Date(timestamp);
		
		int year = realDate.getYear();
		int month = realDate.getMonth() + 1;
		int date = realDate.getDate();
		int hour = realDate.getHours();
		
		dateHour = year + "-";
		
		if ( month < 10 ) dateHour += "0" + month + "-";
		else dateHour += month + "-";
		
		if ( date < 10 ) dateHour += "0" + date + ":";
		else dateHour += date + ":";

		if ( hour < 10 ) dateHour += "0" + hour;
		else dateHour += hour;
		
		return dateHour;

    }
    
    @SuppressWarnings("deprecation")
	public String getDateByTimeStamp(long timestamp) throws Exception {
    	
    	String dateHour = "";
		Date realDate = new Date(timestamp);
		
		int year = realDate.getYear();
		int month = realDate.getMonth() + 1;
		int date = realDate.getDate();
		
		dateHour = year + "-";
		
		if ( month < 10 ) dateHour += "0" + month + "-";
		else dateHour += month + "-";
		
		if ( date < 10 ) dateHour += "0" + date;
		else dateHour += date;

		return dateHour;

    }

    public boolean isLeapYear(int year){
        boolean is = false;
        if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) {
            is = true;
        }else{
            is = false;
        }
        return is;
    }
    
    public int getLengthOfYear(int year){
        if (isLeapYear(year)){
            return 366;
        }else{
            return 365;
        }
    }
    
    public int getLengthOfMonth(int year, int month){
        if (month <= 12 && month>=1){
            int month_length[] = new int[12];
              month_length[0] = 31;
              if (isLeapYear(year)){
                  month_length[1] = 29;    
              }else{
                  month_length[1] = 28;
              }
              month_length[2] = 31;
              month_length[3] = 30;
              month_length[4] = 31;
              month_length[5] = 30;
              month_length[6] = 31;
              month_length[7] = 31;
              month_length[8] = 30;
              month_length[9] = 31;
              month_length[10] = 30;
              month_length[11] = 31;
              
              int index = month-1;
              return month_length[index];
         
        }else{
            return 0;
        }
	}
    
    public Date getTotalDateByPrevDate() throws Exception
    {
    	Date today = getTotalDateTime();
    	
    	long yesterdayTime = today.getTime() - 86400000;
    	
    	return new Date(yesterdayTime);
    }
    
    public Date getTotalDateByNextDate() throws Exception
    {
    	Date today = getTotalDateTime();
    	
    	long tomorrowTime = today.getTime() + 86400000;
    	
    	return new Date(tomorrowTime);
    }
    
	public Integer[] getPrevDate(Integer year, Integer month, Integer date)
    {
    	CurrentDateTime cdt = new CurrentDateTime();
    	Integer[] prevDate = new Integer[3];
    	
    	if ( date > 1 )
    	{
    		prevDate[0] = year;
    		prevDate[1] = month;
    		prevDate[2] = date - 1;
    	}
    	else
    	{
    		if ( month > 1 )
    		{
    			prevDate[0] = year;
    			prevDate[1] = month - 1;
    			prevDate[2] = cdt.getLengthOfMonth(year.intValue(), month.intValue()-1);
    		}
    		else
    		{
    			prevDate[0] = year-1;
    			prevDate[1] = 12;
    			prevDate[2] = 31;
    		}
    	}
    		
    	return prevDate;
    }
    
    public Integer[] getNextDate(Integer year, Integer month, Integer date)
    {
    	CurrentDateTime cdt = new CurrentDateTime();
    	Integer[] nextDate = new Integer[3];
    	
    	if ( date < cdt.getLengthOfMonth(year.intValue(), month.intValue()) )
    	{
    		nextDate[0] = year;
    		nextDate[1] = month;
    		nextDate[2] = date + 1;
    	}
    	else
    	{
    		if ( month < 12 )
    		{
    			nextDate[0] = year;
    			nextDate[1] = month + 1;
    			nextDate[2] = 1;
    		}
    		else
    		{
    			nextDate[0] = year+1;
    			nextDate[1] = 1;
    			nextDate[2] = 1;
    		}
    	}
    		
    	return nextDate;
    }
    
    public Integer[] getNextMonth(Integer year, Integer month, Integer date)
    {
    	CurrentDateTime cdt = new CurrentDateTime();
    	Integer[] nextDate = new Integer[3];
    	
    	if ( month < 12 )
    	{
    		nextDate[0] = year;
    		nextDate[1] = month + 1;
    		nextDate[2] = date;
    	}
    	else
    	{
    		nextDate[0] = year + 1;
			nextDate[1] = 1;
			nextDate[2] = date;    		
    	}
    		
    	return nextDate;
    }
    
    public String getTotalDate(String format) throws Exception{
        
        return getYear()+format+getMonth()+format+getDate();
    }
    
    public Date[] getDatesByWeek(int year, int month, int week)
    {
    	Date[] dates = new Date[7];
    	
    	int d = 1;
    	int m = month;
    	
    	do
    	{
    		c.set(year, month, d);
    		m = c.get(Calendar.MONTH + 1);
    		
    		if ( c.get(Calendar.WEEK_OF_MONTH) == week ) break;
    		
    		d++;
    	}
    	while ( m == month );
    	
    	if ( m == month )
    	{
    		for ( int i = d; i < d + 7; i++ )
    		{
    			c.set(year, month, i);
    			dates[i-d] = c.getTime();
    		}
    	}
    	
    	return dates;
    }
    
    public String getPrevDate(String dateStr) throws Exception
    {
    	String[] split = dateStr.split("-");
		Integer[] values = getPrevDate(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
		int year = values[0];
		int month = values[1];
		int date = values[2];
		dateStr = year + "-" + (month > 9 ? month + "" : "0" + month) + "-" + (date > 9 ? +
				date + "" : "0" + date);
		return dateStr;
    }
    
    public int getDaysByMonth(int year, int month) {
    	int ret = 31;
    	switch(month){
    	case 1: case 3: case 5: case 7: case 8: case 10: case 12:
    		ret = 31; break;
    	case 4: case 6: case 9: case 11:
    		ret = 30; break;
    	case 2:
    		if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) ret = 29;
    		else ret = 28;
    		break;
    	}
    	return ret;
    }
}