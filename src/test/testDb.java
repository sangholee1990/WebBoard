package test;

import model.BaseDAO;
import model.BoardDAO;
import model.BoardVO;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.jnlp.ServiceManager;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class testDb extends BaseDAO<BoardVO> {

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


//    public int insertUser(User user) {
//        String query = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
//        List<Object> params = new ArrayList<>();
//        params.add(user.getName());
//        params.add(user.getEmail());
//        params.add(user.getPassword());
//        return insert(query, params);
//    }


//    private static String ERROR_MSG = "DB 오류";
//
//    private static DataSource ds = null;

//    @Before
//    private Connection getConnection() {
//
//        // 커넥션 풀 관리 ds 생성
//        if (ds == null) {
//            try {
//                Context init = new InitialContext();
////                ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
//                ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
//            } catch (Exception e) {
//                System.out.println(String.format("[ERROR] Exception: 메시지 %s", e.getMessage()));
//            }
//        }
//
//        System.out.println(String.format("[CHECK] ds : %s", ds));
//
//        try {
//            // DB 연결 con 생성
//            return ds.getConnection();
//        } catch (SQLException e) {
//            System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
//            throw new RuntimeException(ERROR_MSG);
//        }
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//
//    }
//
//    @After
//    public void close(Connection con) {
//        if (con != null) {
//            try {
//                con.close();
//            } catch (SQLException e) {
//                System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
//            }
//        }
//    }
//
//    public void close(PreparedStatement pt) {
//        if (pt != null) {
//            try {
//                pt.close();
//            } catch (SQLException e) {
//                System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
//            }
//        }
//    }
//
//
//    public void close(ResultSet rs) {
//        if (rs != null) {
//            try {
//                rs.close();
//            } catch (SQLException e) {
//                System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
//            }
//        }
//    }
//
//    /**
//     * Connection와 PreparedStatement의 자원을 해제합니다.
//     *
//     * @param con
//     * @param pt
//     */
//    public void close(Connection con, PreparedStatement pt) {
//        close(con);
//        close(pt);
//    }
//
//    /**
//     * Connection와 PreparedStatement 및 ResultSet의 자원을 해제합니다.
//     *
//     * @param con
//     * @param pt
//     * @param rs
//     */
//    public void close(Connection con, PreparedStatement pt, ResultSet rs) {
//        close(rs);
//        close(con, pt);
//    }


}
