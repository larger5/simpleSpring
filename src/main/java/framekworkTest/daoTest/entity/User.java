package framekworkTest.daoTest.entity;

import core.Dao.tableAnnotation.Column;
import core.Dao.tableAnnotation.Id;
import core.Dao.tableAnnotation.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter、setter
@AllArgsConstructor // 有参构造函数
@NoArgsConstructor // 无参构造函数
@Table("t_user")
public class User {

    @Id
    @Column("id")
    private Integer id;

    @Column("user_name") // sql中属性名
    private String userName; // Java中属性名

    @Column("password")
    private String password;

    @Column("department_id")
    private Integer departmentId;

}
