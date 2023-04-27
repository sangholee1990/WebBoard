package action;

import controller.Action;
import controller.ActionForward;
import utils.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class RemoteProcCallAction implements Action {

    private static final String UPLOAD_PATH = "upload";

    public static void main(String[] args) {
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
//                .usePlaintext()
//                .build();
//
//        MyServiceGrpc.MyServiceBlockingStub stub = MyServiceGrpc.newBlockingStub(channel);
//
//        MyServiceOuterClass.ScriptRequest request = MyServiceOuterClass.ScriptRequest.newBuilder()
//                .setScriptName("example_script.sh")
//                .build();
//
//        MyServiceOuterClass.ScriptResponse response = stub.executeShellScript(request);
//
//        System.out.println("Server responded with: " + response.getOutput());
//
//        channel.shutdown();
    }

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

        System.out.println(String.format("[CHECK] fileName %s", fileInfo.getName()));
        WebUtils.writeJsonResponse(response, mapData);
    }
}