package schedule;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class SchNextHd implements ScheduleHandler {

	@Override
	@RequestMapping("/schNext")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws SchException {
		// TODO : 스케쥴 다음 핸들러
		return null; 
	}

}
