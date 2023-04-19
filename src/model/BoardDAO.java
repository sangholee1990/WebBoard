package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.BoardVO;

public class BoardDAO extends BaseDAO<BoardVO> {

    @Override
    public BoardVO setVoResult(ResultSet rs) throws SQLException {

        int bno = rs.getInt("bno");
        String btitle = rs.getString("btitle");
        String bcont = rs.getString("bcont");
        int bhit = rs.getInt("bhit");
        String bdate = rs.getString("bdate");

        BoardVO boardVo = new BoardVO();

        boardVo.setBno(bno)
                .setBtitle(btitle)
                .setBcont(bcont)
                .setBhit(bhit)
                .setBdate(bdate);

        return boardVo;
    }


    /**
     * boardInsert (게시글 등록)
     *
     * @param requestParameterMap
     * @return result
     * @throws SQLException
     */
    public int boardInsert(Map<String, Object> requestParameterMap) {

//        오라클 버전
//        String query = "INSERT INTO BOARD(BNO, BTITLE, BCONT, BDATE) VALUES (BNOSEQ.NEXTVAL, ?, ?, SYSDATE)";
//        String query = "INSERT INTO BOARD(BNO, BTITLE, BCONT, BDATE) VALUES (COALESCE(MAX(BNO), 0) + 1 AS BNO FROM DMS04.BOARD, ?, ?, SYSDATE())";
        String query = "INSERT INTO DMS04.BOARD(BNO, BTITLE, BCONT, BDATE) SELECT COALESCE(MAX(BNO), 0) + 1, ?, ?, CURRENT_TIMESTAMP() FROM DMS04.BOARD";

        // SELECT NEXTVAL('BNOSEQ') AS BOARD FROM DUAL
        List<Object> params = new ArrayList<>();
        params.add(requestParameterMap.get("btitle"));
        params.add(requestParameterMap.get("bcont"));

        int result = insert(query, params);

        return result;
    }


    /**
     * 전체 글 목록을 조회합니다.
     *
     * @param requestParameterMap
     * @return
     * @throws SQLException
     */
    public List<BoardVO> boardSelect(Map<String, Object> requestParameterMap) {

        // 번호를 기준으로 내림차순 정렬
        String query = "SELECT * FROM BOARD ORDER BY BNO DESC";

        List<BoardVO> boardSelect = new ArrayList<>();
        List<Object> params = new ArrayList<>();
        params.add(requestParameterMap.get("bno"));
        params.add(requestParameterMap.get("btitle"));
        params.add(requestParameterMap.get("bhit"));
        params.add(requestParameterMap.get("bdate"));

        boardSelect = select(query, params);
        return boardSelect;
    }


    /**
     * listUpdateOneHit (게시글 1개에 대한 조회수 증가)
     *
     * @param requestParameterMap
     * @throws SQLException
     */
    public void boardUpdateHit(Map<String, Object> requestParameterMap) {

        String query = "UPDATE BOARD SET BHIT = BHIT + 1 WHERE BNO = ?";

        List<Object> params = new ArrayList<>();
        params.add(requestParameterMap.get("bno"));
        update(query, params);
    }


    /**
     * SelectOne (게시글 1개 조회)
     *
     * @param requestParameterMap
     * @return listSelectOne.get(0)
     * @throws SQLException
     */
    public BoardVO boardSelectOne(Map<String, Object> requestParameterMap) {

        String query = "SELECT * FROM BOARD WHERE BNO = ?";

        List<Object> params = new ArrayList<>();

        params.add(requestParameterMap.get("bno"));
        BoardVO boardSelectOne = selectOne(query, params);

        return boardSelectOne;
    }


    /**
     * 1개 목록을 수정합니다.
     *
     * @param requestParameterMap
     * @return result
     * @throws SQLException
     */
    public int boardUpdate(Map<String, Object> requestParameterMap) {

        String query = "UPDATE BOARD SET BTITLE = ?, BCONT = ? WHERE BNO = ?";

        List<Object> params = new ArrayList<>();
        params.add(requestParameterMap.get("btitle"));
        params.add(requestParameterMap.get("bcont"));
        params.add(requestParameterMap.get("bno"));

        int result = update(query, params);

        return result;
    }


    /**
     * listDeleteOne (게시글 1개 삭제)
     *
     * @param requestParameterMap
     * @return re
     * @throws SQLException
     */
    public int boardDelete(Map<String, Object> requestParameterMap) {

        String query = "DELETE FROM BOARD WHERE BNO = ?";

        List<Object> params = new ArrayList<>();
        params.add(requestParameterMap.get("bno"));

        int result = delete(query, params);

        return result;
    }
}





