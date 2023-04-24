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
import model.PageVO;
import service.BoardService;

import static service.BoardService.nullCheck;

public class ListAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) {

        BoardDAO dao = new BoardDAO();
        Map<String, Object> requestParameterMap = BoardService.requestParameterMap(request);
        List<BoardVO> boardList = dao.boardSelect(requestParameterMap);
        request.setAttribute("boardList", boardList);

        int pageNum = Integer.parseInt(nullCheck(request.getParameter("pageNum"), "1"));
        int pagePerNum = Integer.parseInt(nullCheck(request.getParameter("pagePerNum"), "10"));
        int totalCount = boardList.size();

        PageVO pageVO = new PageVO(pageNum, totalCount, pagePerNum); // 페이징 처리를 위한 객체 생성
        System.out.println(String.format("[CHECK] pageVO : %s", pageVO));

        request.setAttribute("pageVO", pageVO);
    }
}
