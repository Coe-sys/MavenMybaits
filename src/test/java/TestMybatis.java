import com.chennan.study.database.SessionFactory;
import com.chennan.study.database.data.TDemoFile;
import com.chennan.study.mapper.ITDemoFileMapper;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
import java.util.List;

/**
 * demo class
 * Mybatis测试类
 * @author 陈楠
 * @date 2019/11/27 16:24
 * To change this template use File | Settings | File Templates.
 */
public class TestMybatis {
    public static void main(String[] args) {
        SqlSession session = SessionFactory.getSession();
        //插入
//        TDemoFile tDemoFile = new TDemoFile("bg.jpg","15648456fef",
//                "image/jpg",10240,new Timestamp(System.currentTimeMillis()));
//        session.insert("fileMapper.insert",tDemoFile);

        //修改
//        TDemoFile tDemoFile = session.selectOne("fileMapper.getById",6);
//        tDemoFile.setFileName("菜单.jpg");
//        tDemoFile.setFileSize(50231);
//        tDemoFile.setOperTime(new Timestamp(System.currentTimeMillis()));
//        session.update("fileMapper.update",tDemoFile);

        //删除
//        session.delete("fileMapper.delete","8,9,10");

        //动态sql语句进行删除操作
//        session.delete("deleteBatch",new String[]{"15","16","17","18"});
//        session.commit();

        //查询
//        List<TDemoFile> list = session.selectList("fileMapper.selectAll");
//        for (TDemoFile file:list){
//            System.out.println(file.getFileName()+", "+file.getFileSize()+", "+file.getOperTime());
//        }
//        session.close();

//        使用映射接口查询数据
        //获得映射结构对象

//        try {
//            ITDemoFileMapper itDemoFileMapper = session.getMapper(ITDemoFileMapper.class);
//            List<TDemoFile> list = itDemoFileMapper.selectAll();
//
//            for (TDemoFile file:list){
//                System.out.println(file.getFileId()+", "+file.getFileName()+", "+
//                        file.getFileType()+", "+file.getFileSize());
//            }
//            session.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}