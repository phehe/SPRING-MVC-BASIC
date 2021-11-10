package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// HTTP 요청 데이터 - API 메시지 바디 출력
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();   // -> 메시지바디의 내용을 바이트 코드로 가져온다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);  // -> 바이트 코드 변환

        System.out.println("messageBody = " + messageBody);

        response.getWriter().write("ok");
    }
}
