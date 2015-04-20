/*
 * Copyright 2015 marlonlom
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.malm.mglam_reads.backend.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Weeks;
import org.joda.time.Years;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Logger;

import co.malm.mglam_reads.backend.configs.DateDiffLabelConfig;

/**
 * <p>Utility class for calculating the minor date difference using two dates: the
 * input date, provided by user, and the current datetime. <br/>
 * This class is a singleton, and its main method can be used for calculating
 * datediff's from an list, and inside loop, it must be used like this:<br/>
 * </p>
 * <pre>
 * DateDiffUtil.getInstance().processDiff(dateText, now);
 * </pre>
 *
 * @author marlonlom
 */
public final class DateDiffUtil {
    /**
     * Simple class name, for logging purposes only
     */
    private static final String TAG = DateDiffUtil.class.getSimpleName();
    /**
     * Logger utility for the class
     */
    private static final Logger LOGGER = Logger.getLogger(TAG);
    /**
     * Date pattern for parsing
     */
    private static final String DATE_PATTERN = "EEE, dd MMM yyyy HH:mm:ss Z";

    /**
     * Simple date format instance for converting date strings
     *
     * @see SimpleDateFormat
     */
    private static final SimpleDateFormat SDF = new SimpleDateFormat(
            DATE_PATTERN, Locale.ENGLISH);

    /**
     * Singleton instance for {@linkplain DateDiffUtil}
     */
    private static final DateDiffUtil INSTANCE = new DateDiffUtil();

    /**
     * Private constructor
     */
    private DateDiffUtil() {
        super();
    }

    /**
     * Returns singleton instance for {@linkplain DateDiffUtil}
     *
     * @return instance
     */
    public static DateDiffUtil getInstance() {
        return INSTANCE;
    }

    /**
     * Calculates time differences using two dates, and stores it into a
     * {@linkplain java.util.Map} object.
     *
     * @param map calculations data
     * @param dt1 first date for diff
     * @param dt2 second date for diff
     * @see org.joda.time.DateTime
     * @see org.joda.time.Years
     * @see org.joda.time.Months
     * @see org.joda.time.Weeks
     * @see org.joda.time.Days
     * @see org.joda.time.Hours
     * @see org.joda.time.Minutes
     * @see org.joda.time.Seconds
     */
    private void calculate(HashMap<String, Integer> map,
                           DateTime dt1, DateTime dt2) {

        final int SECONDS_IN_MINUTE = 60;
        final int MINUTES_IN_HOUR = 60;
        final int HOURS_IN_DAY = 24;
        final int DAYS_IN_MONTH = 31;
        final int MONTHS_IN_YEAR = 12;
        final int WEEKS_IN_DAY = 7;

        int diffYears = Years.yearsBetween(dt1, dt2).getYears();
        if (diffYears > 0) {
            map.put("years", diffYears);
        }

        int diffMonths = Months.monthsBetween(dt1, dt2).getMonths();
        if (diffMonths >= 1 && diffMonths < MONTHS_IN_YEAR) {
            map.put("months", diffMonths);
        }

        int diffWeeks = Weeks.weeksBetween(dt1, dt2).getWeeks();
        if (diffWeeks >= 1 && diffWeeks < WEEKS_IN_DAY) {
            map.put("weeks", diffWeeks);
        }

        int diffDays = Days.daysBetween(dt1, dt2).getDays();
        if (diffDays >= 1 && diffDays < DAYS_IN_MONTH) {
            map.put("days", diffDays);
        }

        int diffHours = Hours.hoursBetween(dt1, dt2).getHours();
        if (diffHours >= 1 && diffHours < HOURS_IN_DAY) {
            map.put("hours", diffHours);
        }

        int diffMinutes = Minutes.minutesBetween(dt1, dt2).getMinutes();
        if (diffMinutes >= 1 && diffMinutes < MINUTES_IN_HOUR) {
            map.put("minutes", diffMinutes);
        }

        int diffSeconds = Seconds.secondsBetween(dt1, dt2).getSeconds();
        if (diffSeconds >= 1 && diffSeconds < SECONDS_IN_MINUTE) {
            map.put("seconds", diffSeconds);
        }
    }

    /**
     * Returns minor value for calculated datetime differences
     *
     * @param map stored datetime difference calculations
     * @return minor key=value for time calculation
     */
    private String chooseMinorDiff(HashMap<String, Integer> map) {
        Set<String> keySet = map.keySet();
        String minorKey = "";
        Integer minorValue = Integer.MAX_VALUE;
        for (String diffKey : keySet) {
            Integer diffValue = map.get(diffKey);
            int comparison = Math.min(minorValue, diffValue);
            if (comparison == diffValue) {
                minorKey = diffKey;
                minorValue = diffValue;
            }
        }
        String minValueText = String.valueOf(minorValue.intValue());
        return minorKey.concat("=").concat(minValueText);
    }

    /**
     * Prepares date difference calculations, and returns the minor value
     * calculated in time terms
     *
     * @param textdate text date for compare
     * @return minor date difference calculated
     */
    public String diff(String textdate) {
        StringBuilder resultDiff = new StringBuilder();
        try {

            Date date0 = parseDate(textdate);

            resultDiff.append(perform(date0, new Date()));

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return resultDiff.toString();
    }

    /**
     * Prepares date difference calculations, and returns the minor value
     * calculated in time terms
     *
     * @param textdate text date for compare
     * @param current  current date
     * @return minor date difference calculated
     */
    public String diff(String textdate, Date current) {
        StringBuilder resultDiff = new StringBuilder();
        try {

            Date date0 = parseDate(textdate);

            resultDiff.append(perform(date0, current));

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return resultDiff.toString();
    }

    /**
     * Prepares date difference calculations, and returns the minor value
     * calculated in time terms
     *
     * @param textdate0 text date for compare
     * @param textdate1 text date for compare
     * @return minor date difference calculated
     */
    public String diff(String textdate0, String textdate1) {
        StringBuilder resultDiff = new StringBuilder();
        try {

            Date date0 = parseDate(textdate0);
            Date date1 = parseDate(textdate1);

            resultDiff.append(perform(date0, date1));

        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
        }
        return resultDiff.toString();
    }

    /**
     * Parses date string using defined pattern
     *
     * @param textdate date string
     * @return parsed {@linkplain Date} instance
     * @throws Exception
     */
    private Date parseDate(String textdate) throws Exception {
        return SDF.parse(textdate);
    }

    /**
     * Performs date difference using selected dates
     *
     * @param date0 input date for diff
     * @param date1 input date for diff
     */
    private String perform(Date date0, Date date1) {
        StringBuilder resultDiff = new StringBuilder();
        DateTime dt1 = new DateTime(date0);
        DateTime dt2 = new DateTime(date1);

        if (dt1.isBefore(dt2)) {
            HashMap<String, Integer> map = new HashMap<String, Integer>();
            calculate(map, dt1, dt2);
            resultDiff.append(chooseMinorDiff(map));
        }

        return applyPattern(resultDiff.toString());
    }

    /**
     * Applies Date Diff pattern using Diff Labels configurations
     *
     * @param resultDiff diff result in time=value format
     * @return applied text, empty otherwise
     * @see co.malm.mglam_reads.backend.configs.DateDiffLabelConfig
     */
    private String applyPattern(String resultDiff) {
        String appliedLabel = "";

        /*Separate daytime and diff number*/
        final String[] diff_details = resultDiff.split("=");

        DateDiffLabelConfig[] configs = DateDiffLabelConfig.values();
        for (DateDiffLabelConfig config : configs) {
            final String[] diff_label_parts = config.toString().toLowerCase().split("_");
            final String label_part = diff_label_parts[diff_label_parts.length - 1];
            if (diff_details[0].equalsIgnoreCase(label_part)) {
                appliedLabel = config.getLabelPattern();
                break;
            }
        }

        if (!appliedLabel.isEmpty()) {
            /*if found pattern applies to months, add 'es' to pluralize text, so appears 'meses' */
            String[] label_months = DateDiffLabelConfig.DIFF_LABEL_MONTHS.name().split("_");
            final String label_month = label_months[label_months.length - 1];
            if (diff_details[0].equalsIgnoreCase(label_month)) {
                appliedLabel = appliedLabel.concat("es");
            } else if (Integer.valueOf(diff_details[1]).intValue() > 1) {
                appliedLabel = appliedLabel.concat("s");
            }

        }

        /*Add diff value to pattern and returns it*/
        Integer diffVal = Integer.parseInt(diff_details[1]);
        return String.format(appliedLabel, diffVal);
    }
}
