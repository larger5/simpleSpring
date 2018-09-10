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
@Table("t_department")
public class Department {

    @Id
    @Column("id")
    private Integer id;

    @Column("department_name")
    private String departmentName;

}
