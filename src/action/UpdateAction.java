package action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;
import model.BoardVO;
import model.BoardDAO;
import service.BoardService;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) {

		BoardDAO dao = new BoardDAO();
		Map<String, Object> requestParameterMap = BoardService.requestParameterMap(request);

		BoardVO boardAttr = dao.boardSelectOne(requestParameterMap);
		request.setAttribute("boardAttr", boardAttr);
		
	}
}
