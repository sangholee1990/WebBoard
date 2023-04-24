package action;

import controller.Action;
import controller.ActionForward;
import model.FileVO;

import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import utils.WebUtils;


public class FileUploadAction implements Action {

    private static final String UPLOAD_PATH = "upload";

    // 1,000 MB
    private static final int MEMORY_THRESHOLD = 1024 * 1024 * 1000;
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 1000;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 1000;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) throws Exception {

        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        File uploadFileInfo = new File(uploadPath);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        if (!uploadFileInfo.exists() && !uploadFileInfo.mkdirs()) throw new Exception("디렉터리 생성 실패 : " + uploadPath);

        List<FileItem> items = upload.parseRequest(request);
        for (FileItem item : items) {
            if (item.isFormField()) throw new Exception(String.format("폼 태그 실패 : %s", item.getName()));

            String fileName = item.getName();
            File saveFile = new File(uploadPath, fileName);
            if (saveFile.exists()) throw new Exception(String.format("파일 중복 : %s | %s", saveFile.getPath(), saveFile.getName()));

            item.write(saveFile);

            System.out.println(String.format("[CHECK] saveFile %s", saveFile));
        }

        response.getWriter().println("File uploaded successfully.");
    }
}