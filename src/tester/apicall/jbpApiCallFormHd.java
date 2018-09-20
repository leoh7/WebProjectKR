package tester.apicall;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import member.jobprov.JbpException;
import member.jobprov.JobProvHandler;

@Controller
public class jbpApiCallFormHd implements JobProvHandler {

	@Override
	@RequestMapping("/openAPIForm")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws JbpException {
		// FIXME : Dispatch 경로 바꿔야함
		return new ModelAndView("APItest/openAPIForm");
	}

}
