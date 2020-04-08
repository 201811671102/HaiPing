package pre.ysl.service.impl;


import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pre.ysl.mapper.StudentMapper;
import pre.ysl.pojo.Student;
import pre.ysl.pojo.StudentExample;
import pre.ysl.service.StudentService;

import java.util.List;

@Service
@Log4j2
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public int insertNewStudent(Student student) {
        try {
            return studentMapper.insertSelective(student);
        }catch (Exception e) {
           throw e;
        }
    }

    @Override
    public Student selectBySid(Integer sid) {
        try {
            return studentMapper.selectByPrimaryKey(sid);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e) {
           throw e;
        }

    }
    @Override
    public int updateStudentinfo(Student student) {
        try{
            return studentMapper.updateByPrimaryKeySelective(student);
        }catch (Exception e) {
           throw e;
        }
    }

    @Override
    public Student selectByWechar(String swechar) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSwecharEqualTo(swechar);
        try {
            List<Student> studentList = studentMapper.selectByExample(studentExample);
            if (studentList.size() > 1){
                log.info("StudentServiceImpl:selectByWechar 出现学生同微信");
            }else if (studentList.size() == 0){
                return null;
            }
            return studentList.get(0);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e){
            throw  e;
        }
    }

    @Override
    public List<Student> selectByState(Integer state,Integer start,Integer size,String cName) {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSstateEqualTo(state);
        studentExample.createCriteria().andScollegeEqualTo(cName);
        try{
            if ( size != 0 ){
                RowBounds rowBounds = new RowBounds(start,size);
                return studentMapper.selectByExampleWithRowbounds(studentExample,rowBounds);
            }
            return studentMapper.selectByExample(studentExample);
        }catch (NullPointerException e){
            return null;
        }catch (Exception e) {
           throw e;
        }
    }

    @Override
    public int deleteStudent(Integer sid) {
        try{
            return studentMapper.deleteByPrimaryKey(sid);
        }catch (Exception e) {
            throw e;
        }
    }

}
