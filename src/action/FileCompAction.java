package action;

import com.google.gson.Gson;
import controller.Action;
import controller.ActionForward;
import utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.zip.*;

public class FileCompAction implements Action {

    private static final String UPLOAD_PATH = "upload";

    public static void main(String[] args) {
//        List<String> fileNames = Arrays.asList("file1.txt", "file2.txt", "file3.txt");

//        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        String uploadPath = "C:\\dev\\workspace\\01.CodeReview\\WebBoard\\classes\\artifacts\\WebBoard_war_exploded\\upload";
        String dtYmdHms =  LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        FileListAction flAction = new FileListAction();

        List<File> fileList = null;
        try {
            fileList = flAction.getFileList(uploadPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(fileList);
        compressFiles(fileList, String.format("%s/%s_%s", uploadPath, dtYmdHms, "combined.zip"));
    }

    private static void compressFiles(List<File> files, String outputZipFile) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        try (FileOutputStream fos = new FileOutputStream(outputZipFile);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ZipOutputStream zos = new ZipOutputStream(bos)) {

            // 최대 압축 효율
            zos.setLevel(9);

            List<Future<Void>> tasks = new ArrayList<>();
            for (File file : files) {
                tasks.add(executor.submit(() -> {
                    addToZipFile(file, zos);
                    return null;
                }));
            }

            for (Future<Void> task : tasks) {
                task.get();
            }

            System.out.println("All files compressed into " + outputZipFile);

        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

    private static synchronized void addToZipFile(File file, ZipOutputStream zos) throws IOException {
        try (FileInputStream fis = new FileInputStream(file);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            ZipEntry zipEntry = new ZipEntry(file.getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = bis.read(buffer)) != -1) {
                zos.write(buffer, 0, len);
            }

            zos.closeEntry();
            System.out.println("File added to zip: " + file.getName());
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