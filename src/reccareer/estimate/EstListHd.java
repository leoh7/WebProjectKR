package reccareer.estimate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class EstListHd implements EstimateHandler {

	@Override
	@RequestMapping("/estList")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws EstException {
		// TODO : 사후평가 리스트 핸들러
		return null;
	}

}
