package action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.Action;
import controller.ActionForward;
import model.BoardDAO;
import service.BoardService;
import utils.WebUtils;

public class UpdateOkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) {

		BoardDAO dao = new BoardDAO();
		Map<String, Object> requestParameterMap = BoardService.requestParameterMap(request);

		int re = dao.boardUpdate(requestParameterMap);
		WebUtils.writeResponse(request, response);
	}
}
