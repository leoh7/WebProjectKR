package iptcareer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class IptCrrModHd implements InputCareerHandler {

	@Override
	@RequestMapping("/iptCrrMod")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws IptCrrException {
		// TODO Auto-generated method stub
		return null;
	}

}