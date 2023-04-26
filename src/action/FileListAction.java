package action;

import com.google.gson.Gson;
import controller.Action;
import controller.ActionForward;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.text.DecimalFormat;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileListAction implements Action {

    private static final String UPLOAD_PATH = "upload";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) throws Exception {

        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        List<File> fileList = getFileList(uploadPath);

        List<Map<String, Object>> resMapList = fileList.stream()
                .map(this::createFileDataMap)
                .collect(Collectors.toList());

        Map<String, Object> resData = createResponseData(fileList.size(), resMapList);
        WebUtils.writeJsonResponse(response, resData);
    }

    public List<File> getFileList(String uploadPath) throws IOException {
        return Files.walk(Paths.get(uploadPath))
                .parallel()
                .filter(Files::isRegularFile)
                .filter(path -> !isZipFile(path))
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    private static boolean isZipFile(Path path) {
        String fileName = path.getFileName().toString().toLowerCase();
        Pattern pattern = Pattern.compile("\\.(zip|rar)$");
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }

    public Map<String, Object> createFileDataMap(File file) {
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("name", file.getName());
        mapData.put("size", formatFileSize(file.length()));
        return mapData;
    }

    private String formatFileSize(long size) {
        return new DecimalFormat("#.##").format(size / 1024.0 / 1024.0) + " MB";
    }

    private Map<String, Object> createResponseData(int total, List<Map<String, Object>> resMapList) {
        Map<String, Object> resData = new HashMap<>();
        resData.put("total", total);
        resData.put("rows", resMapList);
        return resData;
    }
}