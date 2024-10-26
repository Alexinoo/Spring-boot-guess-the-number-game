package academy.learnprogramming.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
public class RequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //return HandlerInterceptor.super.preHandle(request, response, handler);
        log.debug("preHandle() called. handler = {}",handler);
        log.debug("URL = {}",request.getRequestURL());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        //HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        log.debug("postHandle() called. handler = {}",handler);
        log.debug("URL = {}",request.getRequestURL());
        log.debug("model = {}",modelAndView.getModel());
        log.debug("view = {}",modelAndView.getViewName());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        //HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        log.debug("afterCompletion() called. handler = {}",handler);
        log.debug("URL = {}",request.getRequestURL());
    }
}
