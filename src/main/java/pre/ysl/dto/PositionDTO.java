package pre.ysl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pre.ysl.pojo.Position;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO extends Position {
    public PositionDTO(Position position){
        this.setPname(position.getPname());
        this.setPaddress(position.getPaddress());
        this.setPcompensation(position.getPcompensation());
        this.setPdescribe(position.getPdescribe());
        this.setPid(position.getPid());
        this.setPrequirements(position.getPrequirements());
        this.setPtype(position.getPtype());
        this.setPwelfare(position.getPwelfare());
        this.setUpdateTime(position.getUpdateTime());
    }
    public void PositionDTO(Position position){
        this.setPname(position.getPname());
        this.setPaddress(position.getPaddress());
        this.setPcompensation(position.getPcompensation());
        this.setPdescribe(position.getPdescribe());
        this.setPid(position.getPid());
        this.setPrequirements(position.getPrequirements());
        this.setPtype(position.getPtype());
        this.setPwelfare(position.getPwelfare());
        this.setUpdateTime(position.getUpdateTime());
    }
    /*发布者信息*/
    private Object userObject;
    /*发布者身份*/
    private Integer sepctype;
    /*兼职记录id*/
    private Integer spid;
    /*招聘状态*/
    private Integer spState;

}
