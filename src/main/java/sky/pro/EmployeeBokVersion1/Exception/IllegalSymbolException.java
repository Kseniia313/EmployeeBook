package sky.pro.EmployeeBokVersion1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "В имени и фамилии можно использовать только буквы")
public class IllegalSymbolException extends IllegalArgumentException {

}
