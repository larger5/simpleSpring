package framekworkTest.daoTest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用于接受自定义查询的字段，名称必须保持和数据库返回的字段一致
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDepartment {

    private Integer id;

    private String user_ame;

    private String password;

    private Integer department_id;

    private String department_name;

}
