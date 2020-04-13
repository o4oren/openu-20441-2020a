/**
 * This class represents a date, and has methods to perform actions with date objects.
 */
public class Date
{
    public static final int LAST_MONTH = 12;
    public static final int FIRST_MONTH = 1;
    public static final int MIN_DAY = 1;
    public static final int MIN_YEAR = 0;

    public static final int LAST_DAY_OF_MONTH = 31;
    public static final int LAST_DAY_OF_FEBRUARY = 28;
    public static final int DEFAULT_YEAR = 2000;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int MARCH = 3;
    public static final int APRIL = 4;
    public static final int MAY = 5;
    public static final int JUNE = 6;
    public static final int JULY = 7;
    public static final int AUGUST = 8;
    public static final int SEPTEMBER = 9;
    public static final int OCTOBER = 10;
    public static final int NOVEMBER = 11;
    public static final int DECEMBER = 12;

    private int _day;
    private int _month;
    private int _year;

    /**
     * Creates a Date object with the specified day, month and year.
     *
     * @param day - represents the day in the month
     * @param month - represents the month in the year
     * @param year - represents the year
     */
    public Date(int day, int month, int year)
    {
        if (isValid(day, month, year)) {
            _day = day;
            _month = month;
            _year = year;
        } else {
            _day = MIN_DAY;
            _month = FIRST_MONTH;
            _year = DEFAULT_YEAR;
        }
    }

    /**
     * Creates a copy of the Date object passed in the constructor
     * @param other Date object
     */
    public Date(Date other)
    {
        this(other._day, other._month, other._year);
    }


    /**
     * Checks if given day/month/year represent a valid date
     *
     * @param day
     * @param month
     * @param year
     * @return true if given day/month/year are a valid date
     */
    private boolean isValid(int day, int month, int year)
    {
        if (year < MIN_YEAR) return false; // This is a store inventory. negative years do not make sense.
        if ((month < FIRST_MONTH) || (month > LAST_MONTH)) return false; // months are between 1 and 12.
        if ((day < MIN_DAY) || (day > LAST_DAY_OF_MONTH))
            return false; // days are between 1 and 31. We will check 30 day months, and February seperately.

        // In February we have max 28 days on most years, and 1 more on leap years.
        if (month == FEBRUARY)
            return (isLeap(year)) ? day <= ( LAST_DAY_OF_FEBRUARY + 1 ) : day <= LAST_DAY_OF_FEBRUARY;

        // In April, June, September and November there are 30 days only
        if (month == APRIL || month == JUNE || month == SEPTEMBER || month == NOVEMBER)
            return day <= LAST_DAY_OF_MONTH - 1;

        return true;
    }

    /**
     * Checks if the year is a leap year.
     * According to wikipedia a leap year is a year that is divided by 400 without a remainder
     * or if it can be divided by 4 but not by 100 without a remainder.
     * https://he.wikipedia.org/wiki/%D7%A9%D7%A0%D7%94_%D7%9E%D7%A2%D7%95%D7%91%D7%A8%D7%AA
     * @return true if leap year.
     */
    private boolean isLeap(int year)
    {
        if(year % 400 == 0)
            return true;
        else if(year % 100 == 0)
            return false;
        else if (year % 4 == 0)
            return true;
        return false;
    }

    /**
     * Returns true if the given date represents the same date as the current object.
     *
     * @param other
     * @return
     */
    public boolean equals(Date other)
    {
        if (other.getDay() == _day && other.getMonth() == _month && other.getYear() == _year)
            return true;
        return false;
    }

    /**
     * Returns true if this object's date is before the given object's date.
     * We are using the given formula to calculate days since the begining of Christian calendar
     * Because it will be a simpler calculation.
     *
     * @param other
     * @return true if this date if before the given date
     */
    public boolean before(Date other)
    {
        return calculateDaysSinceYearZero(_day, _month, _year) < calculateDaysSinceYearZero(other.getDay(), other.getMonth(), other.getYear());
    }

    /**
     * Returns true if this date is after this given date
     * We are using only the before method, as required.
     *
     * @param other
     * @return
     */
    public boolean after(Date other)
    {
        return other.before(this);
    }

    /**
     * Returns a new object representing the day after this date.
     * @return
     */
    public Date tomorrow()
    {
        //check validity if we only increase the day, else we try with day set to 1 and increase the month.
        if(isValid(_day+1, _month, _year))
        {
            return new Date(_day+1, _month, _year);
        }
        else if (isValid(1, _month +1, _year))  // check validity if we increate the month. If not, we will increase the year.
        {
            return new Date(1, _month+1, _year);
        }
        else
        {
            return new Date(1, 1, _year +1);
        }
    }

    /**
     * Returns the difference between the given date and the current date in days
     * @param other
     * @return
     */
    public int difference(Date other)
    {
        return Math.abs(calculateDaysSinceYearZero(_day, _month, _year) - calculateDaysSinceYearZero(other.getDay(), other.getMonth(), other.getYear()));
    }

    public int dayInWeek()
    {
        int day = (_day + (26 * (getM() + 1)) / 10 + getY() + getY() / 4
            + getC()/4 - 2* getC()) % 7;
        return Math.floorMod(day, 7);
    }

    /**
     * Returns "M" in the day of the week calculation - i.e. Jan and Feb are months 13, 14.
     * @return
     */
    private int getM()
    {
        if(_month == 1)
            return 13;
        if(_month == 2)
            return 14;
        return _month;
    }

    /**
     * Returns C in the day of the week formula.
     * To get the first two digits - i.e. hunderds - we divide the year by 100.
     * Due to the fact that Jan/Feb are treated as months 13,14 of the previous year, we check for that as well.
     * @return int
     */
    private int getC()
    {
        return (_month >= 3) ? _year / 100 :  (_year -1) / 100;
    }

    /**
     * Returns Y in the day of the week formula.
     * To get the last two digits - i.e. tens and single years - we check the remainder of the year by 100.
     * Due to the fact that Jan/Feb are treated as months 13,14 of the previous year, we check for that as well.
     * @return int
     */
    private int getY()
    {
        return (_month >= 3) ? _year % 100 :  (_year -1) % 100;
    }


    // calculates the day number since the begining of the christian counting of years
    private int calculateDaysSinceYearZero(int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }



    public String toString()
    {
        String dateString = "";
        if(_day <= 9)
            dateString+="0";
        dateString+=_day + "/";

        if(_month <= 9)
            dateString+="0";
        dateString+=_month + "/" + _year;
        return dateString;
    }

    // getters and setters
    public int getDay()
    {
        return _day;
    }

    public int getMonth()
    {
        return _month;
    }

    public int getYear()
    {
        return _year;
    }

    public void setDay(int dayToSet)
    {
        if (isValid(dayToSet, _month, _year))
            _day = dayToSet;
    }

    public void setMonth(int monthToSet)
    {
        if (isValid(_day, monthToSet, _year))
            _month = monthToSet;
    }

    public void setYear(int yearToSet)
    {
        if (isValid(_day, _month, yearToSet))
            _year = yearToSet;
    }
}
