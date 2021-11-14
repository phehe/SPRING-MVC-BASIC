package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        System.out.println("v3");
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("1");
        String requestURI = request.getRequestURI();

        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        System.out.println("2");
        Map<String, String> paramMap = createParamMap(request); // request getparameter
        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName();     // 논리이름 new-form
        System.out.println("4");
        MyView view = viewResolver(viewName);  // viewName(new-form) 가지고 -> viewResolver 호출
        view.render(mv.getModel(),request, response);         // mv 넣어 줘야함-> 모델에 담긴 정보
    }

    // viewResolver@@
    private MyView viewResolver(String viewName) {
        System.out.println("4-");
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    // paramMap   request에 있는 parameter 다 꺼내와
    private Map<String, String> createParamMap(HttpServletRequest request) {
        System.out.println("2-");
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
