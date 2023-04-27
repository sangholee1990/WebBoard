package action;

import com.google.gson.Gson;
import controller.Action;
import controller.ActionForward;
import utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Future;

public class FileDownAction implements Action {

    private static final String UPLOAD_PATH = "upload";

//    public static void main(String[] args) {
//    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) throws Exception {

        Map<String, Object> mapData = new HashMap<>();
        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        String fileName = request.getParameter("fileName");
        System.out.println(String.format("[CHECK] fileName : %s", fileName));

        File fileInfo = new File(uploadPath, fileName);

        if (!fileInfo.exists())
            throw new Exception(String.format("[ERROR] 파일 정보 없음 : %s | %s", fileInfo.getPath(), fileInfo.getName()));

        byte[] fileContent = new byte[(int) fileInfo.length()];
        try (FileInputStream fis = new FileInputStream(fileInfo)) {
            fis.read(fileContent);
        }

        String base64EncodedFileContent = Base64.getEncoder().encodeToString(fileContent);

        mapData.put("fileName", fileInfo.getName());
        mapData.put("fileCont", base64EncodedFileContent);

        System.out.println(String.format("[CHECK] fileName : %s", fileInfo.getName()));
        WebUtils.writeJsonResponse(response, mapData);
    }
}