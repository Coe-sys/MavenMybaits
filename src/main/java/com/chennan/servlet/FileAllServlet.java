package com.chennan.servlet;

import com.chennan.study.database.SessionFactory;
import com.chennan.study.database.data.TDemoFile;
import com.chennan.study.mapper.ITDemoFileMapper;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 全部文件的控制器
 * @author Teacher
 * @version 1.0.0
 * @createTime 2019年10月18日 11:05:20
 */
@WebServlet("/servlet/fileall")
@MultipartConfig(location = "d:/myfile", maxFileSize = 1073741824)
public class FileAllServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获得请求的方法
        String method = request.getParameter("method");
        //判断选择处理请求的方法
        if ("upload".equals(method)) {
            upload(request, response);
        } else if("updateView".equals(method)) {
            updateView(request, response);
        } else if("update".equals(method)) {
            update(request, response);
        } else if("updateAjax".equals(method)) {
            updateAjax(request, response);
        } else if ("deleting".equals(method)) {
            deleting(request, response);
        } else {
            query(request, response);
        }

    }

    /**
     * 修改文件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SqlSession session = null;
        try {
            //获得请求的参数
            int fileId = Integer.parseInt(request.getParameter("fileId"));
            String fileName = request.getParameter("fileName");
            //获得会话
            session = SessionFactory.getSession();
            //获得访问数据的映射接口
            ITDemoFileMapper fileMapper = session.getMapper(ITDemoFileMapper.class);
            //获得修改的文件对象
            TDemoFile file = fileMapper.getById(fileId);
            //修改文件信息
            file.setFileName(fileName);
            file.setOperTime(new Timestamp(System.currentTimeMillis()));
            //访问数据修改文件信息
            fileMapper.update(file);
            //提交事务
            session.commit();
            //查询文件列表数据
            List<TDemoFile> list = fileMapper.selectAll(null);
            //转发数据
            request.setAttribute("list", list);
            //跳转到列表界面
            request.getRequestDispatcher("/admin/files/fileall.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 修改文件的视图页面
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //声明会话变量
        SqlSession session = null;
        try {
            //获得文件ID
            String fileId = request.getParameter("id");
            //获得会话
            session = SessionFactory.getSession();
            //获得访问数据的映射接口
            ITDemoFileMapper fileMapper = session.getMapper(ITDemoFileMapper.class);
            //访问数据库，查询文件数据
            TDemoFile file = fileMapper.getById(Integer.parseInt(fileId));
            //文件对象添加给请求，转发数据
            request.setAttribute("file", file);
            //跳转到修改视图页面
            request.getRequestDispatcher("/admin/files/fileallupdate.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /**
     * 修改文件(Ajax)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateAjax(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SqlSession session = null;
        //设置响应内容的类型
        response.setContentType("text/html;charset=UTF-8");
        //获得响应内容输出流
        Writer out = response.getWriter();
        try {
            //获得请求的参数
            int fileId = Integer.parseInt(request.getParameter("fileId"));
            String fileName = request.getParameter("fileName");
            //获得会话
            session = SessionFactory.getSession();
            //获得访问数据的映射接口
            ITDemoFileMapper fileMapper = session.getMapper(ITDemoFileMapper.class);
            //获得修改的文件对象
            TDemoFile file = fileMapper.getById(fileId);
            //修改文件信息
            file.setFileName(fileName);
            file.setOperTime(new Timestamp(System.currentTimeMillis()));
            //访问数据修改文件信息
            fileMapper.update(file);
            //提交事务
            session.commit();
            //响应客户端
            out.write("{\"success\": true}");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"success\": false}");
        } finally {
            session.close();
            out.close();
        }
    }

    /**
     * 查询文件看数据
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SqlSession session = null;
        try {
            //获得搜索文件名
            String fileName = request.getParameter("sfileName");
            //获得会话（服务器与数据库）
            session = SessionFactory.getSession();
            //获得数据访问映射接口对象
            ITDemoFileMapper fileMapper = session.getMapper(ITDemoFileMapper.class);
            //查询返回数据列表
            List<TDemoFile> list = fileMapper.selectAll(fileName);
            //转发数据
            request.setAttribute("list", list);
            //跳转到下一个页面
            request.getRequestDispatcher("/admin/files/fileall.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /**
     * 上传文件
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void upload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //定义Session变量
        SqlSession session =null;
        //设置响应内容的类型
        request.setCharacterEncoding("UTF-8");
        //获得响应内容输出流
        Writer out = response.getWriter();
        try {
            //获得文件组件对象
            Part part = request.getPart("file");
            //获得文件相关信息
            String fileName = part.getSubmittedFileName();
            System.out.println(fileName);
            //获得文件类型
            String fileType = part.getContentType();
            //获得文件的后缀
            String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
            //获得文件大小
            int fileSize = (int) part.getSize();
            //生成随机字符串作为文件物理名称，形成相对路径
            String fileUrl = UUID.randomUUID().toString().replace("-", "");
            //复制文件到磁盘中
            part.write(fileUrl);

            //添加文件数据到数据库
            //创建文件持久化对象
            TDemoFile file = new TDemoFile();
            //封装文件数据
            file.setFileName(fileName);
            file.setFileUrl(fileUrl);
            file.setFileType(fileType);
            file.setFileSuffix(fileSuffix);
            file.setFileSize(fileSize);
            file.setOperTime(new Timestamp(System.currentTimeMillis()));
            //获得会话
            session = SessionFactory.getSession();
            //获得访问数据的映射接口
            ITDemoFileMapper fileMapper = session.getMapper(ITDemoFileMapper.class);
            //访问数据库，插入文件数据
            fileMapper.insert(file);
            //提交事务
            session.commit();
            //查询返回数据列表
            List<TDemoFile> list = fileMapper.selectAll(null);
            //封装数据
            Map<String, Object> temp = new HashMap<>();
            temp.put("success", true);
            temp.put("list", list);
            //生成JSON格式的数据
            JSONObject jsonData = new JSONObject(temp);
            //System.out.println(jsonData.toString());
            //响应客户数据
            out.write(jsonData.toString());
        } catch (Exception e) {
            e.printStackTrace();
            out.write("{'success':false}");
        }finally {
            //关闭session
            session.close();
        }
    }

    public void deleting(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SqlSession session = null;
        try {
            //获得选择的id
            String[] ids = request.getParameterValues("id");
            //获得会话
            session = SessionFactory.getSession();
            //获得访问数据的映射接口
            ITDemoFileMapper fileMapper = session.getMapper(ITDemoFileMapper.class);
            //访问数据库，删除文件数据
            fileMapper.deleteBatch(ids);
            //提交时事务
            session.commit();
            //查询返回数据列表
            List<TDemoFile> list = fileMapper.selectAll(null);
            //转发数据
            request.setAttribute("list", list);
            //跳转到下一个页面
            request.getRequestDispatcher("/admin/files/fileall.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭session
            session.close();
        }
    }
}
