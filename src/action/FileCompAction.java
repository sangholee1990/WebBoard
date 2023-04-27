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
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ActionForward action) throws Exception {

        Map<String, Object> resData = new HashMap<>();

        String uploadPath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        String dtYmdHms = LocalDateTime.now(ZoneId.of("Asia/Seoul")).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        FileListAction flAction = new FileListAction();

        List<File> fileList = flAction.getFileList(uploadPath);
        String comFileInfo = String.format("%s/%s_%s_%s.zip", uploadPath, dtYmdHms, "compression", fileList.size());

        compressFiles(fileList, comFileInfo);

        resData.put("size", fileList.size());
        resData.put("fileName", new File(comFileInfo).getName());

        System.out.println(String.format("[CHECK] resData %s", resData));
        WebUtils.writeJsonResponse(response, resData);
    }

    private static void compressFiles(List<File> files, String outputZipFile) throws Exception {
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

        } catch (IOException | InterruptedException | ExecutionException iie) {
            System.out.println(String.format("[ERROR] IOException | InterruptedException | ExecutionException : %s", iie.getMessage()));
            throw new Exception(String.format("[ERROR] IOException | InterruptedException | ExecutionException : %s", iie.getMessage()));
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
        }
    }
}