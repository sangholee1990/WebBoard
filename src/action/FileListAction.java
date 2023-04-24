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

public class FileListAction implements Action {

    private static final String UPLOAD_PATH = "upload";

    public static void main(String[] args) {
        String ranFileName = UUID.randomUUID().toString();
        System.out.println(ranFileName);
//        String timestamp = String.valueOf(System.currentTimeMillis());
//        LocalDateTime now = LocalDateTime.now();
//        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
//        String timestamp = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
//        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
//        String timestamp = now.format(formatter);
        String dtYmdHms = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        System.out.println(dtYmdHms);

        String uploadPath = "C:\\dev\\workspace\\01.CodeReview\\WebBoard\\classes\\artifacts\\WebBoard_war_exploded";

        // JSON 객체 생성
        List<Map<String, Object>> resMapList = new ArrayList<>();
        Map<String, Object> resData = new HashMap<>();
        Gson gson = new Gson();

        try {
            // 디렉토리에서 파일 목록을 가져옵니다.
//            List<File> filesInDirectory = Files.walk(Paths.get(uploadPath))
//                    .filter(Files::isRegularFile)
//                    .map(Path::toFile)
//                    .collect(Collectors.toList());

            List<File> fileList = Files.walk(Paths.get(uploadPath))
                    .parallel()
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList());

            // 결과 출력을 위한 포맷 설정
            DecimalFormat df = new DecimalFormat("#.##");


            // 각 파일에 대해 이름과 크기를 리스트에 추가합니다.
            for (File fileInfo : fileList) {
                Map<String, Object> mapData = new HashMap<>();
                mapData.put("name", fileInfo.getName());
                mapData.put("size", df.format(fileInfo.length() / 1024.0 / 1024.0) + " MB");
                resMapList.add(mapData);
            }

            resData.put("total", fileList.size());
            resData.put("rows", resMapList);

            System.out.println(gson.toJson(resData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) throws Exception {

        List<Map<String, Object>> resMapList = new ArrayList<>();
        Map<String, Object> resData = new HashMap<>();

        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        List<File> fileList = Files.walk(Paths.get(uploadPath))
                .parallel()
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        // 각 파일에 대해 이름과 크기를 리스트에 추가합니다.
        for (File fileInfo : fileList) {
            Map<String, Object> mapData = new HashMap<>();
            mapData.put("name", fileInfo.getName());
            mapData.put("size", new DecimalFormat("#.##").format(fileInfo.length() / 1024.0 / 1024.0) + " MB");
            resMapList.add(mapData);
        }

        resData.put("total", fileList.size());
        resData.put("rows", resMapList);

        WebUtils.writeJsonResponse(response, resData);
    }
}