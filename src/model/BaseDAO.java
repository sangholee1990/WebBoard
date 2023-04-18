package model;

import oracle.net.aso.f;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

public abstract class BaseDAO<T> {

    // 커넥션 풀 관리 참조변수
    private static DataSource ds = null;

    public abstract T setVoResult(ResultSet rs) throws SQLException;

    private static String ERROR_MSG = "DB 오류";

    /**
     * 접속 정보를 조회한다.
     *
     * @return ds.getConnection()
     * @throws SQLException
     */
    private Connection getConnection() {

        // 커넥션 풀 관리 ds 생성
        if (ds == null) {
            try {
                Context init = new InitialContext();
//                ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
                ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
            } catch (Exception e) {
                System.out.println(String.format("[ERROR] Exception: 메시지 %s", e.getMessage()));
            }
        }

        System.out.println(String.format("[CHECK] ds : %s", ds));

        try {
            // DB 연결 con 생성
            return ds.getConnection();
        } catch (SQLException e) {
            System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
            throw new RuntimeException(ERROR_MSG);
        }
    }

    /**
     * 글 등록합니다.
     *
     * @param query
     * @param params
     * @return result
     * @throws SQLException
     */
    public int insert(String query, List<Object> params) {
        return update(query, params);
    }

    /**
     * 글 삭제합니다.
     *
     * @param query
     * @param params
     * @return result
     * @throws SQLException
     */
    public int delete(String query, List<Object> params) {
        return update(query, params);
    }

    /**
     * 글 수정합니다.
     *
     * @param query
     * @param params
     * @return result
     * @throws SQLException
     */
    public int update(String query, List<Object> params) {
        // or public static int update(String query, Object ... arg )

        Connection con = null; // DB 연결 참조변수
        PreparedStatement pt = null; // 쿼리문 실행 참조변수

        int result = -1;

        con = getConnection();

        try {
            // 자동 커밋 비활성화
            con.setAutoCommit(false);

            pt = con.prepareStatement(query);

            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    pt.setObject(i + 1, params.get(i)); // 쿼리문에서 첫번째 물음표에 정수로 번호값을 저장
                }
            }

            // 수정 쿼리문 실행
            result = pt.executeUpdate();

//          커밋
            con.commit();

        } catch (SQLException e) {
            System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
            rollback(con);
//            throw new SQLException(ERROR_MSG);
        } catch (Exception e) {
            rollback(con);
            System.out.println(String.format("[ERROR] Exception: 메시지 %s", e.getMessage()));
//            throw new Exception(ERROR_MSG);
        } finally {
            close(con, pt);
        }

        return result;
    }

    /**
     * 글 목록을 보여줍니다.
     *
     * @param query
     * @param params
     * @return select
     * @throws SQLException
     */
    public List<T> select(String query, List<Object> params) {

        // DB 연결 참조변수
        Connection con = null;

        // 쿼리문 실행 참조변수
        PreparedStatement pt = null;

        // 검색 결과 레코드를 저장할 참조변수
        ResultSet rs = null;

        List<T> select = new ArrayList<>();

        try {
            con = getConnection();
            pt = con.prepareStatement(query);

            // 검색 쿼리문 실행해서 결과 레코드를 rs에 저장
            rs = pt.executeQuery();

            while (rs.next()) {
                T vo = setVoResult(rs);
                select.add(vo);
            }
        } catch (SQLException e) {
            System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
            throw new RuntimeException(ERROR_MSG);
        } finally {
            close(con, pt, rs);
        }
        return select;
    }


    /**
     * 1개 목록을 보여줍니다.
     *
     * @param query
     * @param params
     * @return
     * @throws SQLException
     */
    public T selectOne(String query, List<Object> params) {

        Connection con = null; // DB 연결 참조변수
        PreparedStatement pt = null; // 쿼리문 실행 참조변수
        ResultSet rs = null; // 검색 결과 레코드를 저장할 참조변수

        T vo = null;

        try {
            con = getConnection();
            pt = con.prepareStatement(query);

            // 쿼리문에서 첫번째 물음표에 정수로 번호값을 저장
            if (params != null && params.size() > 0) {
                for (int i = 0; i < params.size(); i++) {
                    pt.setObject(i + 1, params.get(i));
                }
            }

            // 검색 쿼리문 실행해서 결과 레코드를 rs에 저장
            rs = pt.executeQuery();

            while (rs.next()) {
                vo = setVoResult(rs);
            }
        } catch (SQLException e) {
            System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
            throw new RuntimeException(ERROR_MSG);
        } finally {
            close(con, pt, rs);
        }
        return vo;
    }

    public void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException e) {
                System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
            }
        }
    }

    public void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
            }
        }
    }

    public void close(PreparedStatement pt) {
        if (pt != null) {
            try {
                pt.close();
            } catch (SQLException e) {
                System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
            }
        }
    }


    public void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println(String.format("[ERROR] SQLException: 코드 %s : 상태 %s : 메시지 %s", e.getErrorCode(), e.getSQLState(), e.getMessage()));
            }
        }
    }

    /**
     * Connection와 PreparedStatement의 자원을 해제합니다.
     *
     * @param con
     * @param pt
     */
    public void close(Connection con, PreparedStatement pt) {
        close(con);
        close(pt);
    }

    /**
     * Connection와 PreparedStatement 및 ResultSet의 자원을 해제합니다.
     *
     * @param con
     * @param pt
     * @param rs
     */
    public void close(Connection con, PreparedStatement pt, ResultSet rs) {
        close(rs);
        close(con, pt);
    }

    /**
     * Database 자원을 객체로서 해제합니다.
     *
     * @param objects
     */
    /*
     * public static void close(Object ... objects ) {
     *
     * for(Object obj:objects) {
     *
     * if(obj instanceof ResultSet) { if(obj != null) try {((ResultSet)obj).close();
     * } catch (SQLException e) {} } if(obj instanceof PreparedStatement) { if(obj
     * != null) try {((PreparedStatement)obj).close(); } catch (SQLException e) {} }
     * if(obj instanceof Connection) { if(obj != null) try
     * {((Connection)obj).close(); } catch (SQLException e) {} } } }
     */

}
