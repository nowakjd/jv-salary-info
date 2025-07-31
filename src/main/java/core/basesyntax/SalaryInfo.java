package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        LocalDate startDate = LocalDate.parse(dateFrom, formatter);
        LocalDate endDate = LocalDate.parse(dateTo, formatter);
        int[] salaries = getSalaries(names, data, startDate, endDate);
        return getReport(names, startDate, endDate, salaries);

    }

    private int[] getSalaries(String[] names, String[] data,
                              LocalDate startDate, LocalDate endDate) {
        int[] salaries = new int[names.length];
        for (int i = 0; i < names.length; i++) {
            for (String userData : data) {
                String[] workday = userData.split(" ");
                LocalDate workDate = LocalDate.parse(workday[0], formatter);
                if (!workDate.isBefore(startDate) && !workDate.isAfter(endDate)) {
                    if (names[i].equals(workday[1])) {
                        salaries[i] += Integer.parseInt(workday[2]) * Integer.parseInt(workday[3]);
                    }
                }

            }

        }
        return salaries;
    }

    private String getReport(String[] names, LocalDate startDate,
                             LocalDate endDate, int[] salaries) {
        StringBuilder report = new StringBuilder("Report for period ")
                .append(startDate.format(formatter))
                .append(" - ")
                .append(endDate.format(formatter));
        for (int i = 0; i < names.length; i++) {
            report.append(System.lineSeparator())
                    .append(names[i])
                    .append(" - ")
                    .append(salaries[i]);
        }
        return report.toString();
    }

}
