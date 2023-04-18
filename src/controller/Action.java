//package action.board;
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ActionForward;

public interface Action {

    // public abstract이 생략된 추상 메서드
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action);


}
