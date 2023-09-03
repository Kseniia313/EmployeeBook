package sky.pro.EmployeeBokVersion1.Util;

import org.apache.commons.lang3.StringUtils;
import sky.pro.EmployeeBokVersion1.Exception.IllegalSymbolException;

public class EmployeeNameValidator {
    public static void employeeNameIsAlpha(String...names) {
        for (String name : names) {
            if (!StringUtils.isAlpha(name)) {
                throw new IllegalSymbolException();
            }
        }
    }
}
