package team;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
public class TeamListHd implements TeamHandler {
	@Resource
	private TeamDBBean teamDao;
	@Override
	@RequestMapping("/teamList")
	public ModelAndView process(HttpServletRequest request, HttpServletResponse response) throws TeamException {
		int teamId = (int) request.getSession().getAttribute("teamId");	//	jsp세션에 저장된 팀 id받아오기.
		List<TeamDataBean> teamlist = teamDao.teamList(teamId);
		
		ObjectMapper mapper = new ObjectMapper(); 
		
		String tl=""; 
		try { 
			tl = mapper.writeValueAsString(teamlist);
			
		} catch (IOException e) { 
			e.printStackTrace(); 
		}
		
		request.setAttribute("teamlist", tl);
		return new ModelAndView("team/teamView");
	}

}
