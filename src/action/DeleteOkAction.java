package action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;
import org.json.simple.JSONObject;

import controller.Action;
import model.BoardVO;
import model.BoardDAO;
import service.BoardService;

public class DeleteOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action){
		
		BoardDAO dao = new BoardDAO();
		Map<String, Object> requestParameterMap = BoardService.requestParameterMap(request);
		JSONObject jsonObj = new JSONObject();

		int re = dao.boardDelete(requestParameterMap);
		jsonObj.put("deleteResult", re); 
		
		action.setReturnData(jsonObj);

	}
}
