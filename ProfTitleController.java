//49.234.209.55
package cn.edu.sdjzu.xg.bysj.controller.basic.profTitle;

import cn.edu.sdjzu.xg.bysj.domain.Degree;
import cn.edu.sdjzu.xg.bysj.domain.Department;
import cn.edu.sdjzu.xg.bysj.domain.ProfTitle;
import cn.edu.sdjzu.xg.bysj.service.DegreeService;
import cn.edu.sdjzu.xg.bysj.service.DepartmentService;
import cn.edu.sdjzu.xg.bysj.service.ProfTitleService;
import cn.edu.sdjzu.xg.bysj.service.SchoolService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import util.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/profTitle.ctl")
public class ProfTitleController extends HttpServlet {
    //49.234.209.55
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String profTitle_json = JsonUtil.getJson(request);
        ProfTitle profTitleToAdd = JSON.parseObject(profTitle_json,ProfTitle.class);
        JSONObject message = new JSONObject();
        try {
            ProfTitleService.getInstance().add(profTitleToAdd);
            message.put("message","增加成功");
            response.getWriter().println(message);
        } catch (SQLException e) {

            message.put("message","数据库操作异常");
            e.printStackTrace();
            response.getWriter().println(message);
        } catch (Exception e){
            message.put("message","网络异常");
            e.printStackTrace();
            response.getWriter().println(message);
        }

    }
    //49.234.209.55
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id_str = request.getParameter("id");
        JSONObject message = new JSONObject();
        try {
            //如果id = null, 表示响应所有学院对象，否则响应id指定的学院对象
            if (id_str == null) {
                responseProfTitles(response);
            } else {
                int id = Integer.parseInt(id_str);
                responseProfTitle(id, response);
            }
            message.put("message","查询成功");
            response.getWriter().println(message);
        }catch (SQLException e){
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            response.getWriter().println(message);
        }catch(Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            response.getWriter().println(message);
        }
    }
    //49.234.209.55
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id_str = request.getParameter("id");
        int id = Integer.parseInt(id_str);
        JSONObject message = new JSONObject();
        try {
            ProfTitleService.getInstance().delete(id);
            message.put("message","删除成功");
            response.getWriter().println(message);
        } catch (SQLException e) {

            message.put("message", "数据库操作异常");
            e.printStackTrace();
            response.getWriter().println(message);
        } catch (Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            response.getWriter().println(message);
        }

    }
    //49.234.209.55
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String profTitle_json = JsonUtil.getJson(request);
        ProfTitle profTitleToAdd = JSON.parseObject(profTitle_json, ProfTitle.class);
        JSONObject message = new JSONObject();
        try {
            ProfTitleService.getInstance().update(profTitleToAdd);
            message.put("message","修改成功");
            response.getWriter().println(message);
        } catch (SQLException e) {

            message.put("message", "数据库操作异常");
            e.printStackTrace();
            response.getWriter().println(message);
        } catch (Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            response.getWriter().println(message);
        }

    }

    private void responseProfTitle(int id, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        ProfTitle profTitle = ProfTitleService.getInstance().find(id);
        String profTitle_json = JSON.toJSONString(profTitle);
        response.getWriter().println(profTitle_json);
    }
    private void responseProfTitles(HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Collection<ProfTitle> profTitles = ProfTitleService.getInstance().getAll();
        String profTitles_json = JSON.toJSONString(profTitles);

        response.getWriter().println(profTitles_json);
    }
}
