package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import model.BoardVO;
import model.BoardDAO;
import service.BoardService;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) {

		BoardDAO dao = new BoardDAO();
		// asdasdaas?key=1212&key2:val2
		Map<String, Object> requestParameterMap = BoardService.requestParameterMap(request);

		List<BoardVO> boardList = dao.boardSelect(requestParameterMap);
		request.setAttribute("boardList", boardList);
	}
}
