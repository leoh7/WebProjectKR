package board;




import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.ibatis.session.SqlSession;

import mybatis.SqlMapClient;

public class BoardDBBean {
	private SqlSession session = SqlMapClient.getSession();
	
	public int notWrt(BoardDataBean boardDto) {
	
		String boardId = boardDto.getBoardId();
		
		int st3 =0;
		boardId = boardDto.getBoardId();
		//System.out.println(boardId + "-");
				
		List<String>rst1 = session.selectList("Board.selId");
		
		if(rst1.size()==1){
			String rst11 = session.selectOne("Board.selectId");
			st3 = Integer.parseInt(rst11.toString().split("_")[1]);
		}else {					
			for(int i=0; i< rst1.size() ; i++) {								
				 if(Integer.parseInt(rst1.get(i).toString().split("_")[0])==0){
					st3 = Integer.parseInt(rst1.get(i).toString().split("_")[1]);
				}else {
					rst1.remove(rst1.get(i));
				}
				
			}//int i			

		}
		st3=st3+1;
		boardId = 0 + "_" + st3;
		boardDto.setBoardId(boardId);
		//boardDto.setBoardParentId(boardParentId);
		rst1.removeAll(rst1);
		boardDto.setBoardParentId(0);		
	
		return session.insert("Board.notWrt", boardDto);	
	}
	
	public int boardWrt(BoardDataBean boardDto) { // 임시매개변수. 필요시 변경할것.
	   // List<String> rst = new ArrayList<String>();
	   // String rst[]= null;
	    int result = 0;
		String boardId = boardDto.getBoardId();
		
		int boardParentId = (boardDto.getBoardParentId());
		int count =0;		
		int st1 =0;
		int st3 =0;
		
		//boardId = st1 + "_" + st3;
		//System.out.println(st1);
		//boardParentId ==0
		//boardId == 0 + "_" + 1
		if(boardParentId ==0) {
			// 제목글			
				List<String> rst = session.selectList("Board.selectId");	
				//System.out.println(rst + "*");
				if(rst.size()>1){
					for(int i=0; i< rst.size()-1 ; i++) {	
						int a =Integer.parseInt(rst.get(i).toString().split("_")[0]);
						for(int j=1; j<rst.size(); j++) {
							int b=Integer.parseInt(new StringTokenizer(rst.get(j).toString(), "_").nextToken());
							if( a < b) {
								result = b;
							}else{
								result = a;
							}
						}				
					}
				
					st1 = result +1;
					
					boardDto.setBoardId(st1+"_"+1);						
			}else {
				try {
					String rst11 = session.selectOne("Board.selectId");
					result = Integer.parseInt(rst11.toString().split("_")[0]);				
					}catch (NullPointerException e) {
						String rst11 = null;
						result =0;
					}

					st1 = result+1;
					boardDto.setBoardId(st1+"_"+1);	
			}
				try {
					count = session.selectOne("Board.selectMax");
				}catch (NullPointerException e) {
					count = 0;
				}		
			
			boardParentId = count +1;
			
			
		} else {
			// 답변글		
			boardId = boardDto.getBoardId();
			
			StringTokenizer st = new StringTokenizer(boardId, "_"); //0_1
			st1 = Integer.parseInt(st.nextToken());
			List<String>rst1 = session.selectList("Board.selectId");
			
				if(rst1.size()==1){
					String rst11 = session.selectOne("Board.selectId");
					st1 = Integer.parseInt(rst11.toString().split("_")[0]);
					st3 = Integer.parseInt(rst11.toString().split("_")[1]);
					//System.out.println(st3);
				}else {							
					for(int i=0; i< rst1.size() ; i++) {								
						 if(Integer.parseInt(rst1.get(i).toString().split("_")[0])==st1){
							st3 = Integer.parseInt(rst1.get(i).toString().split("_")[1]);
						}
					}//int i		
				}
			
			st3=st3+1;
			boardId = st1 + "_" + st3;
			boardDto.setBoardId(boardId);
			rst1.removeAll(rst1);
			boardParentId = st1;
			String bId = st1+"_"+1;
			session.update("Board.num", bId);
		}
			
		boardDto.setBoardParentId(boardParentId);		
		
		return session.insert("Board.boardWrt", boardDto);  // 에러막기위한 임시 리턴. 필요없으면 지울것.
	}
	
	public void num(String boardId) {			
		session.update("Board.num", boardId);
	}
	
	public int boardMod(BoardDataBean boardDto) {// 임시매개변수. 필요시 변경할것.
		return session.update("Board.boardMod", boardDto); // 에러막기위한 임시 리턴. 필요없으면 지울것.
	}
	
	public int boardDel(String boardId) {// 임시매개변수. 필요시 변경할것.
		return session.delete("Board.boardDel", boardId); // 에러막기위한 임시 리턴. 필요없으면 지울것.
	}
	
	public BoardDataBean boardGet(String boardId) {// 임시매개변수. 필요시 변경할것.
		return session.selectOne("Board.boardGet", boardId);// 에러막기위한 임시 리턴. 필요없으면 지울것.
	}
	
	public List<BoardDataBean> boardList(Map<String, Object> map) {// 임시매개변수. 필요시 변경할것.
		return session.selectList("Board.boardList", map); // 에러막기위한 임시 리턴. 필요없으면 지울것.
	}
	public int boardCnt() {
		return session.selectOne("Board.boardCnt");
	}


	public int userSelCnt(String boarduserId) {
		return session.selectOne("Board.userSelCnt", boarduserId);
	}

	public List<BoardDataBean> userSel(Map<String, Object> map) {
		return session.selectList("Board.userSel", map);
	}

	public List<BoardDataBean> Sel(String userId) {
		return session.selectList("Board.Sel", userId);
	}

	
	public List<BoardDataBean> getAdm() { // 공지 리스트 가져오기
		return session.selectList("Board.getAdm"); 
	}
	
	public List<BoardDataBean> getAdm2() { //신고 리스트 가져오기
		return session.selectList("Board.getAdm2"); 
	}

	public List<BoardDataBean> boardGetFin(Map<String, Object> map) {
		return session.selectList("Board.boardGetFin", map);
	}

	public List<BoardDataBean> notGet() {
		return session.selectList("Board.notGet");
	}

	public int Mod(BoardDataBean boardD) {
		return session.update("Board.Mod", boardD);
	}

	public BoardDataBean boardNotGet(String boardId) {
		return session.selectOne("Board.boardNotGet", boardId);
	}
	public void number(BoardDataBean bDto) {			
		session.update("Board.number", bDto);
	}
	
}
