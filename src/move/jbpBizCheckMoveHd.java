package move;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class jbpBizCheckMoveHd implements MoveHandler {

	@Override
	@RequestMapping("/jbpBizCheckMove")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws MoveException {
		// TODO : 사업자등록번호 확인 페이지 오픈
		return new ModelAndView("Jbp/jbpBizCheck");
	}

}
