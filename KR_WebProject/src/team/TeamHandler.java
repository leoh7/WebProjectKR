package team;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface TeamHandler {
	public ModelAndView process( HttpServletRequest request, HttpServletResponse response )	
			throws TeamException;
}
