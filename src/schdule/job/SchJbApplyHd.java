package schdule.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class SchJbApplyHd implements ScheduelJobHandler {

	@Override
	@RequestMapping("/schJbApply")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws SchJbException {
		// TODO Auto-generated method stub
		return null;
	}

}