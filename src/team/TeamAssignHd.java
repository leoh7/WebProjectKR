package team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class TeamAssignHd implements TeamHandler {

	@Override
	@RequestMapping("/teamAssign")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws TeamException {
		// TODO Auto-generated method stub
		return null;
	}

}