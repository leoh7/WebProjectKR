package member.jobseeker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class JbskDelHd implements JobSeekerHandler {

	@Override
	@RequestMapping("/jbskDel")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws JobSeekerException {
		// TODO : 구직자탈퇴 핸들러
		return null;
	}

}
