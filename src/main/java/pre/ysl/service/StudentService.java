package pre.ysl.service;

import pre.ysl.pojo.Student;

import java.util.List;

public interface StudentService {
    /*添加学生信息未认证*/
    int insertNewStudent(Student student);
    /*根据sid查询学生*/
    Student selectBySid(Integer sid);
    /*学生认证|修改信息*/
    int updateStudentinfo(Student student);
    /*根据微信号查询学生*/
    Student selectByWechar(String swechar);
    /*查询没有通过验证的学生*/
    List<Student> selectByState(Integer state,Integer start,Integer size,String cName);
    /*删除学生身份*/
    int deleteStudent(Integer sid);
}
