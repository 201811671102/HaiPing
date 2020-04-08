package pre.ysl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pre.ysl.pojo.Student;

/**
 * @ClassName StudentDTO
 * @Description TODO
 * @Author QQ163
 * @Date 2020/3/31 22:21
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO extends Student {
    public void StudentDTO(Student student){
        this.setSid(student.getSid());
        this.setSname(student.getSname());
        this.setSsex(student.getSsex());
        this.setSeducation(student.getSeducation());
        this.setSworkexperience(student.getSworkexperience());
        this.setSwechar(student.getSwechar());
        this.setSstate(student.getSstate());
        this.setScollege(student.getScollege());
        this.setSstudentnumber(student.getSstudentnumber());
        this.setSgrade(student.getSgrade());
        this.setScollegeaddress(student.getScollegeaddress());
        this.setSresume(student.getSresume());
    }
    /**
     * 应聘状态
     */
    private Integer spState;
}
